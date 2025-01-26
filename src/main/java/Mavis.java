import java.util.Scanner;


/**
 * Mavis is a simple task management application that interacts with the user via
 * the command line to add, delete, mark, and unmark tasks.
 * It supports ToDo, Deadline, and Event tasks.
 * Invalid input is handled with error messages.
 */
public class Mavis {

    /**
     * Main method that runs the task manager application.
     * It reads user input, processes commands, and interacts with the TaskManager
     * to manage tasks.
     * 
     * @param args Command-line arguments (not used in this application).
     */
    
    /**
    * The file path to the Mavis data file.
    *     
    */
    private static final String FILE_PATH = "src/main/data/Mavis.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager(FILE_PATH);
        System.out.println("Hello! I'm Mavis\nYou have " + (taskManager.taskCount-taskManager.doneCount)+" tasks\n");
        
        // Continuously read user input and process commands
        while (true) {
            try {
                String input = sc.nextLine().trim();

                if (input.equals("bye")) {
                    taskManager.saveTasks();
                    System.out.println("Bye. Hope to see you again soon!\n");
                    break;

                } else if (input.equals("list")) {
                    String list = taskManager.listTasks();
                    System.out.println(list);

                } else if (input.startsWith("mark")) {
                    String[] parts = input.split(" ");
                    if (parts.length != 2 || !parts[1].matches("\\d+")) {
                        throw new IllegalArgumentException("Please provide a valid task number to mark.");
                    }
                    int taskIndex = Integer.parseInt(parts[1]);
                    if (taskIndex > taskManager.taskCount || taskIndex < 1) {
                        throw new IllegalArgumentException("Task number " + taskIndex + " does not exist.");
                    }
                    taskManager.saveTasks();
                    System.out.println("Nice! I've marked this task as done:");
                    String task = taskManager.setDone(taskIndex, true);
                    System.out.println(task + "\n");

                } else if (input.startsWith("unmark")) {
                    String[] parts = input.split(" ");
                    if (parts.length != 2 || !parts[1].matches("\\d+")) {
                        throw new IllegalArgumentException("Please provide a valid task number to unmark.");
                    }
                    int taskIndex = Integer.parseInt(parts[1]);
                    if (taskIndex > taskManager.taskCount || taskIndex < 1) {
                        throw new IllegalArgumentException("Task number " + taskIndex + " does not exist.");
                    }
                    taskManager.saveTasks();
                    System.out.println("OK, I've marked this task as not done yet:");
                    String task = taskManager.setDone(taskIndex, false);
                    System.out.println(task + "\n");

                } else if (input.startsWith("todo")) {
                    String name = input.substring(4).trim();
                    if (name.isEmpty()) {
                        throw new IllegalArgumentException("The description of a ToDo cannot be empty.");
                    }
                    ToDo todo = new ToDo(name);
                    taskManager.addTask(todo);
                    System.out.println("Got it. I've added this task:\r\n" + todo.report());
                    System.out.println("Now you have " + taskManager.taskCount + " tasks in the list.\n");

                } else if (input.startsWith("deadline")) {
                    String[] name = input.substring(8).trim().split("/", 3);
                    if (name.length < 2) {
                        throw new IllegalArgumentException("A deadline task must be in this format (e.g., task /due date).");
                    }
                    String desc = name[0].trim();
                    String byPart = name[1].trim();
                    if (!byPart.toLowerCase().startsWith("by")) {
                        throw new IllegalArgumentException("The deadline must start with 'by'. Example: task /by yyyy-MM-dd HHmm");
                    }
                    String by = byPart.split("by")[1].trim();
                    Deadline deadline = new Deadline(desc, by);
                    taskManager.addTask(deadline);
                    System.out.println("Got it. I've added this task:\r\n" + deadline.report());
                    System.out.println("Now you have " + taskManager.taskCount + " tasks in the list.\n");

                } else if (input.startsWith("event")) {
                    String[] name = input.substring(5).trim().split("/", 3);
                    if (name.length < 3) {
                        throw new IllegalArgumentException(
                                "An event task must be in this format (e.g., task /start /end).");
                    }
                    String desc = name[0].trim();
                    String startPart = name[1].trim();
                    if (!startPart.toLowerCase().startsWith("start")) {
                        throw new IllegalArgumentException("The event must start with 'start'. Example: task /start yyyy-MM-dd HHmm /end yyyy-MM-dd HHmm");
                    }
                    String start = startPart.split("start")[1].trim();
                    String endPart = name[2].trim();
                    if (!endPart.toLowerCase().startsWith("end")) {
                        throw new IllegalArgumentException("The event must start with 'end'. Example: task /start yyyy-MM-dd HHmm /end yyyy-MM-dd HHmm");
                    }
                    String end = endPart.split("end")[1].trim();
                    Event event = new Event(desc, start, end);
                    taskManager.addTask(event);
                    System.out.println("Got it. I've added this task:\r\n" + event.report());
                    System.out.println("Now you have " + taskManager.taskCount + " tasks in the list.\n");

                } else if (input.startsWith("delete")) {
                    String[] parts = input.split(" ");
                    if (parts.length != 2 || !parts[1].matches("\\d+")) {
                        throw new IllegalArgumentException("Please provide a valid task number to delete.");
                    }
                    int taskIndex = Integer.parseInt(parts[1]);
                    if (taskIndex > taskManager.taskCount || taskIndex < 1) {
                        throw new IllegalArgumentException("Task number " + taskIndex + " does not exist.");
                    }
                    String task = taskManager.deleteTask(taskIndex);
                    System.out.println(task);

                } else {
                    throw new IllegalArgumentException("I'm sorry, but I don't know what that means.");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + "\n");

            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage() + "\n");
            }
        }
        sc.close();
    }
}
