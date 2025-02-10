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
        return "Nice catch! I've added this task to your journey:\r\n" + task.report();
    }

    /**
     * Displays a message confirming the deletion of a task.
     *
     * @param task The task that was removed.
     */
    public String showDeleteTask(Task task) {
        return "Task released! It's no longer part of your adventure:\r\\n" + task.report();
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public String showMarkTask(Task task) {
        return "Great work, Trainer! This task is now completed:\r\n" + task.report();
    }

    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public String showUnmarkTask(Task task) {
        return "Oops! This task isn't done yet. Keep training!\r\n" + task.report();
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
            return "No tasks matched your search! Keep exploring and discovering new challenges.";
        }
        for (int i = 0; i < tasksList.size() - 1; i++) {
            list.append(i + 1).append(". ").append(tasksList.get(i).report()).append("\n");
        }
        list.append(tasksList.size()).append(". ").append(tasksList.get(tasksList.size() - 1).report());
        return "Here are the tasks that match your search:\n" + list;
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String showGoodbyeMessage() {
        return "Farewell, Trainer! May your Pokémon grow strong and your journey be legendary!";
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
            return "No tasks matched your search! Keep exploring and discovering new challenges.";
        }
        for (int i = 0; i < matchingTasks.size() - 1; i++) {
            list.append(i + 1).append(". ").append(matchingTasks.get(i).report()).append("\n");
        }
        list.append(matchingTasks.size()).append(". ").append(matchingTasks.get(matchingTasks.size() - 1).report());
        return "Here are the tasks that match your search:\n" + list;
    }
}
