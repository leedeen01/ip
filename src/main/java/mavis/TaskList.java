package mavis;

import java.util.ArrayList;

import mavis.task.Task;

/**
 * The TaskList class provides functionality to manage a collection of tasks.
 * It allows performing various operations on tasks, such as:
 *
 * - Adding new tasks
 * - Deleting tasks by their identifier
 * - Marking tasks as completed or incomplete
 * - Listing all tasks with their current status
 *
 * This class is designed to efficiently handle task management, providing
 * an organized way to interact with a list of tasks.
 */
public class TaskList {

    /**
     * An integer variable that keeps track of the total number of tasks in the list.
     */
    private Integer taskCount;

    /**
     * A list that stores all the tasks managed by the TaskManager.
     */
    private ArrayList<Task> tasksList;

    /**
     * Constructs a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        this.tasksList = new ArrayList<>();
        taskCount = 0;
    }

    /**
     * Constructs a new TaskList with an existing list of tasks.
     *
     * @param tasksList A list of existing tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
        taskCount = tasksList.size();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasksList.add(task);
        taskCount++;
    }

    /**
     * Deletes a task from the list based on the given task number.
     *
     * @param taskNumber The 1-based index of the task to be deleted.
     * @return The task that was deleted.
     * @throws MavisException If the task number is invalid.
     */
    public Task deleteTask(int taskNumber) throws MavisException {
        if (taskNumber < 1 || taskNumber > tasksList.size()) {
            throw new MavisException("Invalid task number. Please enter a valid task number.");
        }
        Task task = tasksList.remove(taskNumber - 1);
        taskCount--;
        return task;
    }

    /**
     * Marks a task as done based on the given task number.
     *
     * @param taskNumber The 1-based index of the task to be updated.
     * @return The task that was marked as done.
     * @throws MavisException If the task number is invalid.
     */
    public Task markDone(int taskNumber) throws MavisException {
        if (taskNumber < 1 || taskNumber > tasksList.size()) {
            throw new MavisException("Invalid task number. Please enter a valid task number.");
        }
        Task task = tasksList.get(taskNumber - 1);
        task.setDone(true);
        return task;
    }

    /**
     * Marks a task as undone based on the given task number.
     *
     * @param taskNumber The 1-based index of the task to be updated.
     * @return The task that was marked as undone.
     * @throws MavisException If the task number is invalid.
     */
    public Task unmarkDone(int taskNumber) throws MavisException {
        if (taskNumber < 1 || taskNumber > tasksList.size()) {
            throw new MavisException("Invalid task number. Please enter a valid task number.");
        }
        Task task = tasksList.get(taskNumber - 1);
        task.setDone(false);
        return task;
    }

    /**
     * Returns the list of all tasks in the TaskList.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    /**
     * Finds tasks in the task list that contain the specified keyword in their name.
     *
     * @param keyword The keyword to search for in the task names.
     * @return A list of tasks whose names contain the given keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasksList) {
            if (task.getName().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

}
