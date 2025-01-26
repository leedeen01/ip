package mavis;
import java.util.Scanner;

import mavis.task.Task;

import java.util.ArrayList;

public class Ui {
    Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        String input = sc.nextLine().trim();
        return input;
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Mavis\nHow can i help you?");
        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showTaskAdded(Task task) {
        System.out.println("Got it. I've added this task:\r\n" + task.report());
    }

    public void showDeleteTask(Task task) {
        System.out.println("Noted. I've removed this task:\r\n" + task.report());
    }

    public void showMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\r\n" + task.report());
    }

    public void showUnmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\r\n" + task.report());
    }

    /**
     * Lists all the tasks currently in the TaskManager.
     * 
     * @return A formatted string listing all tasks with their status and details.
     */
    public void printTasks(TaskList taskList) {
        StringBuilder list = new StringBuilder();
        ArrayList<Task> tasksList = taskList.getTasksList();
        for (int i = 0; i < tasksList.size() - 1; i++) {
            list.append(i + 1).append(". ").append(tasksList.get(i).report()).append("\n");
        }
        list.append(tasksList.size()).append(". ").append(tasksList.get(tasksList.size()-1).report());
        System.out.println("Here are the tasks in your list:\n" + list);
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println(message);
        showLine();
    }
}
