package mavis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import mavis.command.AddCommand;
import mavis.command.Command;
import mavis.command.DeleteCommand;
import mavis.command.ExitCommand;
import mavis.command.FindCommand;
import mavis.command.ListCommand;
import mavis.command.MarkCommand;
import mavis.command.UnmarkCommand;
import mavis.task.Deadline;
import mavis.task.Event;
import mavis.task.ToDo;

/**
 * The Parser class is responsible for parsing user input commands and
 * converting them into
 * corresponding {@link Command} objects that can be executed.
 * It supports commands to add tasks (ToDo, Deadline, Event), delete tasks, mark
 * or unmark tasks,
 * exit the application, and list tasks.
 */
public class Parser {

    /**
     * Parses the user input string and returns a corresponding Command object.
     * This method identifies the type of command (e.g., "todo", "deadline", "event", etc.)
     * and creates the appropriate Command object based on the input.
     * @param inputs The user input as a string array.
     * @return A Command object representing the parsed input.
     * @throws MavisException If the input is invalid or the format is incorrect for a task.
     */
    public static Command parse(String... inputs) throws MavisException {
        assert inputs != null : "Inputs should not be null";
        if (inputs.length == 0 || inputs[0].isEmpty()) {
            throw new MavisException("Input cannot be empty.");
        }
        String input = String.join(" ", inputs).trim();
        if (input.startsWith("todo")) {
            String name = input.substring(4).trim();
            if (name.isEmpty()) {
                throw new MavisException("The description of a ToDo cannot be empty.");
            }
            ToDo todo = new ToDo(name);
            return new AddCommand(todo);
        } else if (input.startsWith("deadline")) {
            String[] parts = input.substring(8).trim().split("/", 3);
            if (parts.length < 2) {
                throw new MavisException("A deadline task must be in this format: task /by due date.");
            }
            String desc = parts[0].trim();
            String byPart = parts[1].trim();
            if (!byPart.toLowerCase().startsWith("by")) {
                throw new MavisException("The deadline must start with 'by'. Example: task /by yyyy-MM-dd HHmm");
            }
            String by = byPart.split("by", 2)[1].trim();
            Deadline deadline = new Deadline(desc, by);
            return new AddCommand(deadline);
        } else if (input.startsWith("event")) {
            String[] parts = input.substring(5).trim().split("/", 3);
            if (parts.length < 3) {
                throw new MavisException("An event task must be in this format: task /start start time /end end time.");
            }
            String desc = parts[0].trim();
            String startPart = parts[1].trim();
            if (!startPart.toLowerCase().startsWith("start")) {
                throw new MavisException("The event must start with 'start'."
                    + " Example: task /start yyyy-MM-dd HHmm /end yyyy-MM-dd HHmm");
            }
            String start = startPart.split("start", 2)[1].trim();
            String endPart = parts[2].trim();
            if (!endPart.toLowerCase().startsWith("end")) {
                throw new MavisException("The event must start with 'end'."
                    + " Example: task /start yyyy-MM-dd HHmm /end yyyy-MM-dd HHmm");
            }
            String end = endPart.split("end", 2)[1].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime startDate;
            LocalDateTime endDate;
            try {
                startDate = LocalDateTime.parse(start, formatter);
                endDate = LocalDateTime.parse(end, formatter);
            } catch (DateTimeParseException e) {
                throw new MavisException("Invalid date format. Please use yyyy-MM-dd HHmm. "
                + "Example: task /from 2025-02-10 0900 to 2025-02-10 1700");
            }

            if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
                throw new MavisException("Start date must be earlier than end date.");
            }

            Event event = new Event(desc, startDate, endDate);
            return new AddCommand(event);
        } else if (input.startsWith("delete")) {
            String[] parts = input.split(" ");
            if (parts.length < 2) {
                throw new MavisException("Delete command requires an index.");
            }
            int taskIndex;
            try {
                taskIndex = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                throw new MavisException("Invalid index for delete command.");
            }
            return new DeleteCommand(taskIndex);
        } else if (input.startsWith("mark")) {
            String[] parts = input.split(" ");
            if (parts.length < 2) {
                throw new MavisException("Mark command requires an index.");
            }
            int num;
            try {
                num = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                throw new MavisException("Invalid index for mark command.");
            }
            return new MarkCommand(num);
        } else if (input.startsWith("unmark")) {
            String[] parts = input.split(" ");
            if (parts.length < 2) {
                throw new MavisException("Unmark command requires an index.");
            }
            int num;
            try {
                num = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                throw new MavisException("Invalid index for unmark command.");
            }
            return new UnmarkCommand(num);
        } else if (input.startsWith("find")) {
            String toFind = input.substring(4).trim();
            if (toFind.isEmpty()) {
                throw new MavisException("Find command requires a search term.");
            }
            return new FindCommand(toFind);
        } else if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else {
            throw new MavisException("I'm sorry, but I don't know what that means.");
        }
    }
}
