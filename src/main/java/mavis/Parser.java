package mavis;
import mavis.command.*;
import mavis.task.Deadline;
import mavis.task.Event;
import mavis.task.ToDo;

public class Parser {
    public static Command parse(String input) throws MavisException {
        if (input.startsWith("todo")) {
            String name = input.substring(4).trim();
            if (name.isEmpty()) {
                throw new MavisException("The description of a ToDo cannot be empty.");
            }
            ToDo todo = new ToDo(name);
            return new AddCommand(todo);
        } else if (input.startsWith("deadline")) {
            String[] name = input.substring(8).trim().split("/", 3);
            if (name.length < 2) {
                throw new MavisException("A deadline task must be in this format (e.g., task /due date).");
            }
            String desc = name[0].trim();
            String byPart = name[1].trim();
            if (!byPart.toLowerCase().startsWith("by")) {
                throw new MavisException("The deadline must start with 'by'. Example: task /by yyyy-MM-dd HHmm");
            }
            String by = byPart.split("by")[1].trim();
            Deadline deadline = new Deadline(desc, by);
            return new AddCommand(deadline);

        } else if (input.startsWith("event")) {
            String[] name = input.substring(5).trim().split("/", 3);
            if (name.length < 3) {
                throw new MavisException(
                        "An event task must be in this format (e.g., task /start /end).");
            }
            String desc = name[0].trim();
            String startPart = name[1].trim();
            if (!startPart.toLowerCase().startsWith("start")) {
                throw new MavisException("The event must start with 'start'. Example: task /start yyyy-MM-dd HHmm /end yyyy-MM-dd HHmm");
            }
            String start = startPart.split("start")[1].trim();
            String endPart = name[2].trim();
            if (!endPart.toLowerCase().startsWith("end")) {
                throw new MavisException("The event must start with 'end'. Example: task /start yyyy-MM-dd HHmm /end yyyy-MM-dd HHmm");
            }
            String end = endPart.split("end")[1].trim();
            Event event = new Event(desc, start, end);
            return new AddCommand(event);

        } else if(input.startsWith("delete")) {
            String[] parts = input.split(" ");
            int taskIndex = Integer.parseInt(parts[1]);
            return new DeleteCommand(taskIndex);

        } else if(input.startsWith("mark")) {
            int num = Integer.parseInt(input.split(" ")[1]);
            return new MarkCommand(num);

        } else if(input.startsWith("unmark")) {
            int num = Integer.parseInt(input.split(" ")[1]);
            return new UnmarkCommand(num);

        }else if(input.startsWith("find")) {
            String toFind = input.split("find")[1].trim();
            return new FindCommand(toFind);

        } else if(input.equals("bye")) {
            return new ExitCommand();

        } else if(input.equals("list")) {
            return new ListCommand();
        } else {
            throw new MavisException("I'm sorry, but I don't know what that means.");
        }
    }
}
