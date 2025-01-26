package mavis;
import java.util.ArrayList;

import mavis.task.Task;

/**
 * The TaskManager class provides functionality to manage a collection of tasks.
 * It allows you to perform various operations on tasks, including:
 * 
 * - Adding new tasks to the list
 * - Deleting tasks by their identifier
 * - Marking tasks as completed or incomplete
 * - Listing all tasks with their current status
 * 
 * This class is designed to efficiently handle task management, providing 
 * an organized way to interact with a list of tasks.
 */
public class TaskList {

    /**
     * A Integer variable that keeps track of the total number of tasks completed.
     */
    Integer taskCount;

    /**
     * A list that stores all the tasks managed by the TaskManager.
     */
    ArrayList<Task> tasksList;


    /**
     * Constructor that initializes the TaskManager with an empty list of tasks.
     */
    public TaskList() {
        this.tasksList = new ArrayList<>();
        taskCount = 0;
    }

    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
        taskCount = tasksList.size();
    }

    /**
     * Adds a new task to the list.
     * 
     * @param task The task to be added.
     * @return A message confirming the task was added.
     */
    public void addTask(Task task) {
        tasksList.add(task);
        taskCount++;
    }

    /**
     * Deletes a task from the list based on the given task number.
     * 
     * @param taskNumber The 1-based index of the task to be deleted.
     * @return A message confirming the task was deleted along with the current list of tasks.
     */
    public Task deleteTask(int taskNumber) throws MavisException  {
        if (taskNumber < 1 || taskNumber > tasksList.size()) {
            throw new MavisException("Invalid task number. Please enter a valid task number.");
        }
        Task task = tasksList.remove(taskNumber - 1);
        taskCount--;
        return task;
    }

    /**
     * Marks a task as done or undone based on the given task number and status.
     * 
     * @param taskNumber The 1-based index of the task to be updated.
     * @param done       A boolean indicating whether the task is done (true) or not (false).
     * @return A message indicating the updated status of the task.
     */
    public Task markDone(int taskNumber) throws MavisException {
        if (taskNumber < 1 || taskNumber > tasksList.size()) {
            throw new MavisException("Invalid task number. Please enter a valid task number.");
        }
        Task task = tasksList.get(taskNumber - 1);
        task.setDone(true);
        return task;
    }

    public Task unmarkDone(int taskNumber) throws MavisException  {
        if (taskNumber < 1 || taskNumber > tasksList.size()) {
            throw new MavisException("Invalid task number. Please enter a valid task number.");
        }
        Task task = tasksList.get(taskNumber - 1);
        task.setDone(false);
        return task;
    }

    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

}
