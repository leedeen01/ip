package mavis;

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

    /**
     * Displays a message confirming the addition of a task.
     *
     * @param task The task that was added.
     */
    public String showTaskAdded(Task task) {
        return "Got it. I've added this task:\r\n" + task.report();
    }

    /**
     * Displays a message confirming the deletion of a task.
     *
     * @param task The task that was removed.
     */
    public String showDeleteTask(Task task) {
        return "Noted. I've removed this task:\r\n" + task.report();
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public String showMarkTask(Task task) {
        return "Nice! I've marked this task as done:\r\n" + task.report();
    }

    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public String showUnmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:\r\n" + task.report();
    }

    /**
     * Displays a list of all tasks currently stored in the TaskManager.
     *
     * @param taskList The TaskList object that holds all tasks.
     */
    public String printTasks(TaskList taskList) {
        StringBuilder list = new StringBuilder();
        ArrayList<Task> tasksList = taskList.getTasksList();
        if (tasksList.isEmpty()) {
            return "There are no tasks in your list.";
        }
        for (int i = 0; i < tasksList.size() - 1; i++) {
            list.append(i + 1).append(". ").append(tasksList.get(i).report()).append("\n");
        }
        list.append(tasksList.size()).append(". ").append(tasksList.get(tasksList.size() - 1).report());
        return "Here are the tasks in your list:\n" + list;
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays the tasks that match the search keyword.
     * If no tasks match, it prints a message indicating no matches were found.
     *
     * @param matchingTasks The list of tasks that match the search criteria.
     */
    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        StringBuilder list = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            return "There are no matching tasks in your list.";
        }
        for (int i = 0; i < matchingTasks.size() - 1; i++) {
            list.append(i + 1).append(". ").append(matchingTasks.get(i).report()).append("\n");
        }
        list.append(matchingTasks.size()).append(". ").append(matchingTasks.get(matchingTasks.size() - 1).report());
        return "Here are the matching tasks in your list:\n" + list;
    }
}
