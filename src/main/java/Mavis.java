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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        System.out.println("Hello! I'm Mavis\nWhat can I do for you?\n");

        // Continuously read user input and process commands
        while (true) {
            try {
                String input = sc.nextLine().trim();
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!\n");
                    break;
                } else if (input.equals("list")) {
                    String list = taskManager.listTasks();
                    if (list.equals("")) {
                        System.out.println("You have no tasks in the list.\n");
                        continue;
                    }
                    System.out.println("Here are the tasks in your list:");
                    System.out.println(list);
                } else if (input.startsWith("mark")) {
                    String[] parts = input.split(" ");
                    if (parts.length != 2 || !parts[1].matches("\\d+")) {
                        throw new IllegalArgumentException("Please provide a valid task number to mark.");
                    }
                    int taskIndex = Integer.parseInt(parts[1]);
                    if (taskIndex > TaskManager.taskCount || taskIndex < 1) {
                        throw new IllegalArgumentException("Task number " + taskIndex + " does not exist.");
                    }
                    System.out.println("Nice! I've marked this task as done:");
                    String task = taskManager.setDone(taskIndex, true);
                    System.out.println(task + "\n");
                } else if (input.startsWith("unmark")) {
                    String[] parts = input.split(" ");
                    if (parts.length != 2 || !parts[1].matches("\\d+")) {
                        throw new IllegalArgumentException("Please provide a valid task number to unmark.");
                    }
                    int taskIndex = Integer.parseInt(parts[1]);
                    if (taskIndex > TaskManager.taskCount || taskIndex < 1) {
                        throw new IllegalArgumentException("Task number " + taskIndex + " does not exist.");
                    }
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
                    System.out.println("Now you have " + TaskManager.taskCount + " tasks in the list.\n");
                } else if (input.startsWith("deadline")) {
                    String[] name = input.substring(8).trim().split("/", 2);
                    if (name.length < 2) {
                        throw new IllegalArgumentException(
                                "A deadline task must be in this format (e.g., task /due date).");
                    }
                    Deadline deadline = new Deadline(name[0].trim(), name[1].trim());
                    taskManager.addTask(deadline);
                    System.out.println("Got it. I've added this task:\r\n" + deadline.report());
                    System.out.println("Now you have " + TaskManager.taskCount + " tasks in the list.\n");
                } else if (input.startsWith("event")) {
                    String[] name = input.substring(5).trim().split("/", 3);
                    if (name.length < 3) {
                        throw new IllegalArgumentException(
                                "An event task must be in this format (e.g., task /start /end).");
                    }
                    Event event = new Event(name[0].trim(), name[1].trim(), name[2].trim());
                    taskManager.addTask(event);
                    System.out.println("Got it. I've added this task:\r\n" + event.report());
                    System.out.println("Now you have " + TaskManager.taskCount + " tasks in the list.\n");
                } else if (input.startsWith("delete")) {
                    String[] parts = input.split(" ");
                    if (parts.length != 2 || !parts[1].matches("\\d+")) {
                        throw new IllegalArgumentException("Please provide a valid task number to delete.");
                    }
                    int taskIndex = Integer.parseInt(parts[1]);
                    if (taskIndex > TaskManager.taskCount || taskIndex < 1) {
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
