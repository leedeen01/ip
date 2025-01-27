package mavis;

import java.util.Scanner;
import java.util.ArrayList;
import mavis.task.Task;

/**
 * The Ui class is responsible for handling user interface interactions, 
 * including reading commands and displaying messages to the user.
 * 
 * It provides methods for showing task-related messages, such as adding, deleting, 
 * marking, and unmarking tasks, as well as displaying the task list.
 */
public class Ui {
    
    private Scanner sc;

    /**
     * Constructs a new Ui object with an initialized Scanner for user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads a command input by the user from the console.
     * 
     * @return The command entered by the user as a trimmed string.
     */
    public String readCommand() {
        String input = sc.nextLine().trim();
        return input;
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Mavis\nHow can i help you?");
        showLine();
    }

    /**
     * Displays a separator line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message confirming the addition of a task.
     * 
     * @param task The task that was added.
     */
    public void showTaskAdded(Task task) {
        System.out.println("Got it. I've added this task:\r\n" + task.report());
    }

    /**
     * Displays a message confirming the deletion of a task.
     * 
     * @param task The task that was removed.
     */
    public void showDeleteTask(Task task) {
        System.out.println("Noted. I've removed this task:\r\n" + task.report());
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     * 
     * @param task The task that was marked as done.
     */
    public void showMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\r\n" + task.report());
    }

    /**
     * Displays a message confirming that a task has been marked as not done.
     * 
     * @param task The task that was marked as not done.
     */
    public void showUnmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\r\n" + task.report());
    }

    /**
     * Displays a list of all tasks currently stored in the TaskManager.
     * 
     * @param taskList The TaskList object that holds all tasks.
     */
    public void printTasks(TaskList taskList) {
        StringBuilder list = new StringBuilder();
        ArrayList<Task> tasksList = taskList.getTasksList();
        if (tasksList.isEmpty()) {
            System.out.println("There are no tasks in your list.");
            return;
        }
        for (int i = 0; i < tasksList.size() - 1; i++) {
            list.append(i + 1).append(". ").append(tasksList.get(i).report()).append("\n");
        }
        list.append(tasksList.size()).append(". ").append(tasksList.get(tasksList.size()-1).report());
        System.out.println("Here are the tasks in your list:\n" + list);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message to the user.
     * 
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
        showLine();
    }

    /**
     * Displays the tasks that match the search keyword.
     * If no tasks match, it prints a message indicating no matches were found.
     * 
     * @param matchingTasks The list of tasks that match the search criteria.
     */
    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        StringBuilder list = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            System.out.println("There are no matching tasks in your list.");
            return;
        }
        for (int i = 0; i < matchingTasks.size() - 1; i++) {
            list.append(i + 1).append(". ").append(matchingTasks.get(i).report()).append("\n");
        }
        list.append(matchingTasks.size()).append(". ").append(matchingTasks.get(matchingTasks.size()-1).report());
        System.out.println("Here are the matching tasks in your list:\n" + list);
    }

}
