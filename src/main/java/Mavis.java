import java.util.Scanner;

public class Mavis {
    public static void main(String[] args){   
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        System.out.println("Hello! I'm Mavis\nWhat can I do for you?\n");

        while(true) {
            String input = sc.next();          
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else if (input.equals("list")){
                String list = taskManager.listTasks();
                System.out.println("Here are the tasks in your list:");
                System.out.println(list + "\n");
            } else if (input.equals("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                String task = taskManager.setDone(sc.nextInt(), true);
                System.out.println(task + "\n");
            } else if (input.equals("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                String task = taskManager.setDone(sc.nextInt(), false);
                System.out.println(task + "\n");
            } else if (input.equals("todo")) {
                String name = sc.nextLine().trim();          
                ToDo todo = new ToDo(name);
                taskManager.addTask(todo);
                System.out.println("Got it. I've added this task:\r\n" + todo.report());
                System.out.println("Now you have " + TaskManager.taskCount + " tasks in the list.\n");
            } else if (input.equals("deadline")) {
                String[] name = sc.nextLine().trim().split("/");          
                Deadline deadline = new Deadline(name[0], name[1]);
                taskManager.addTask(deadline);
                System.out.println("Got it. I've added this task:\r\n" + deadline.report());
                System.out.println("Now you have " + TaskManager.taskCount + " tasks in the list.\n");
            } else if (input.equals("event")) {
                String[] name = sc.nextLine().trim().split("/");          
                Event event = new Event(name[0], name[1], name[2]);
                taskManager.addTask(event);
                System.out.println("Got it. I've added this task:\r\n" + event.report());
                System.out.println("Now you have " + TaskManager.taskCount + " tasks in the list.\n");
            } else {
                System.out.println("Invalid command\n");
            }
        }
        sc.close();
    }
}
