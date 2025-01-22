import java.util.Scanner;

public class Mavis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        System.out.println("Hello! I'm Mavis\nWhat can I do for you?\n");

        while (true) {
            try {
                String input = sc.next();
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!\n");
                    break;
                } else if (input.equals("list")) {
                    String list = taskManager.listTasks();
                    System.out.println("Here are the tasks in your list:");
                    System.out.println(list + "\n");
                } else if (input.equals("mark")) {
                    if (!sc.hasNextInt()) {
                        throw new IllegalArgumentException("Please provide a valid task number to mark.");
                    }
                    int taskIndex = sc.nextInt();
                    System.out.println("Nice! I've marked this task as done:");
                    String task = taskManager.setDone(taskIndex, true);
                    System.out.println(task + "\n");
                } else if (input.equals("unmark")) {
                    if (!sc.hasNextInt()) {
                        throw new IllegalArgumentException("Please provide a valid task number to unmark.");
                    }
                    int taskIndex = sc.nextInt();
                    System.out.println("OK, I've marked this task as not done yet:");
                    String task = taskManager.setDone(taskIndex, false);
                    System.out.println(task + "\n");
                } else if (input.equals("todo")) {
                    String name = sc.nextLine().trim();
                    if (name.isEmpty()) {
                        throw new IllegalArgumentException("The description of a ToDo cannot be empty.");
                    }
                    ToDo todo = new ToDo(name);
                    taskManager.addTask(todo);
                    System.out.println("Got it. I've added this task:\r\n" + todo.report());
                    System.out.println("Now you have " + TaskManager.taskCount + " tasks in the list.\n");
                } else if (input.equals("deadline")) {
                    String[] name = sc.nextLine().trim().split("/", 2);
                    if (name.length < 2) {
                        throw new IllegalArgumentException(
                                "A deadline task must be in this format(e.g., task /due date).");
                    }
                    Deadline deadline = new Deadline(name[0], name[1]);
                    taskManager.addTask(deadline);
                    System.out.println("Got it. I've added this task:\r\n" + deadline.report());
                    System.out.println("Now you have " + TaskManager.taskCount + " tasks in the list.\n");
                } else if (input.equals("event")) {
                    String[] name = sc.nextLine().trim().split("/", 3);
                    if (name.length < 3) {
                        throw new IllegalArgumentException(
                                "An event task must be in this format (e.g., task /start /end).");
                    }
                    Event event = new Event(name[0], name[1], name[2]);
                    taskManager.addTask(event);
                    System.out.println("Got it. I've added this task:\r\n" + event.report());
                    System.out.println("Now you have " + TaskManager.taskCount + " tasks in the list.\n");
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
