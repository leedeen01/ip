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
     * Parses the user input string and returns a corresponding {@link Command} object.
     * This method identifies the type of command (e.g., "todo", "deadline", "event", etc.)
     * and creates the appropriate {@link Command} object based on the input.
     *
     * @param inputs The user input as a string array.
     * @return A {@link Command} object representing the parsed input.
     * @throws MavisException If the input is invalid or the format is incorrect for a task.
     */
    public static Command parse(String... inputs) throws MavisException {
        assert inputs != null : "Inputs should not be null";
        if (inputs.length == 0 || inputs[0].isEmpty()) {
            throw new MavisException("Input cannot be empty.");
        }
        String input = String.join(" ", inputs).trim();
        String[] words = input.split(" ", 2);
        String commandType = words[0].toLowerCase();
        String args = words.length > 1 ? words[1].trim() : "";
        switch (commandType) {
        case "todo":
            return parseToDo(args);
        case "deadline":
            return parseDeadline(args);
        case "event":
            return parseEvent(args);
        case "delete":
            return parseDelete(args);
        case "mark":
            return parseMark(args);
        case "unmark":
            return parseUnmark(args);
        case "find":
            return parseFind(args);
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        default:
            throw new MavisException("I'm sorry, but I don't know what that means.");
        }
    }

    /**
     * Parses and creates a ToDo task.
     *
     * @param args The description of the ToDo task.
     * @return A {@link Command} to add a ToDo task.
     * @throws MavisException If the description is empty.
     */
    private static Command parseToDo(String args) throws MavisException {
        if (args.isEmpty()) {
            throw new MavisException("The description of a ToDo cannot be empty.");
        }
        return new AddCommand(new ToDo(args));
    }

    /**
     * Parses and creates a Deadline task.
     *
     * @param args The description and due date of the Deadline task.
     * @return A {@link Command} to add a Deadline task.
     * @throws MavisException If the format is incorrect.
     */
    private static Command parseDeadline(String args) throws MavisException {
        String[] parts = args.split("/", 2);
        if (parts.length < 2 || !parts[1].trim().toLowerCase().startsWith("by ")) {
            throw new MavisException("A deadline task must be in this format: task /by yyyy-MM-dd HHmm.");
        }
        String desc = parts[0].trim();
        String by = parts[1].trim().substring(3).trim();
        return new AddCommand(new Deadline(desc, by));
    }

    /**
     * Parses and creates an Event task.
     *
     * @param args The description, start, and end time of the Event task.
     * @return A {@link Command} to add an Event task.
     * @throws MavisException If the format is incorrect or the start time is not before the end time.
     */
    private static Command parseEvent(String args) throws MavisException {
        String[] parts = args.split("/", 3);
        if (parts.length < 3) {
            throw new MavisException("An event task must be in this format: "
                + "task /start yyyy-MM-dd HHmm /end yyyy-MM-dd HHmm.");
        }
        String desc = parts[0].trim();
        String start = extractArg(parts[1], "start");
        String end = extractArg(parts[2], "end");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            startDate = LocalDateTime.parse(start, formatter);
            endDate = LocalDateTime.parse(end, formatter);
        } catch (DateTimeParseException e) {
            throw new MavisException("Invalid date format. Use yyyy-MM-dd HHmm.");
        }
        if (!startDate.isBefore(endDate)) {
            throw new MavisException("Start time must be before end time.");
        }
        return new AddCommand(new Event(desc, startDate, endDate));
    }

    private static Command parseDelete(String args) throws MavisException {
        int index = parseIndex(args, "Delete");
        return new DeleteCommand(index);
    }

    private static Command parseMark(String args) throws MavisException {
        int index = parseIndex(args, "Mark");
        return new MarkCommand(index);
    }

    private static Command parseUnmark(String args) throws MavisException {
        int index = parseIndex(args, "Unmark");
        return new UnmarkCommand(index);
    }

    private static Command parseFind(String args) throws MavisException {
        if (args.isEmpty()) {
            throw new MavisException("Find command requires a search term.");
        }
        return new FindCommand(args);
    }

    private static int parseIndex(String args, String command) throws MavisException {
        if (args.isEmpty()) {
            throw new MavisException(command + " command requires an index.");
        }
        try {
            return Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new MavisException("Invalid index for " + command + " command.");
        }
    }

    private static String extractArg(String input, String expected) throws MavisException {
        if (!input.trim().toLowerCase().startsWith(expected + " ")) {
            throw new MavisException("Expected '/" + expected + " yyyy-MM-dd HHmm'.");
        }
        return input.trim().substring(expected.length()).trim();
    }
}
