import java.util.ArrayList;

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
public class TaskManager {

    /**
     * A static variable that keeps track of the total number of tasks.
     */
    static Integer taskCount = 0;

    /**
     * A list that stores all the tasks managed by the TaskManager.
     */
    ArrayList<Task> tasksList;

    /**
     * Constructor that initializes the TaskManager with an empty list of tasks.
     */
    public TaskManager() {
        tasksList = new ArrayList<>();
    }

    /**
     * Adds a new task to the list.
     * 
     * @param task The task to be added.
     * @return A message confirming the task was added.
     */
    public String addTask(Task task) {
        tasksList.add(task);
        taskCount++;
        return "Task added: " + task;
    }

    /**
     * Deletes a task from the list based on the given task number.
     * 
     * @param taskNumber The 1-based index of the task to be deleted.
     * @return A message confirming the task was deleted along with the current list of tasks.
     */
    public String deleteTask(Integer taskNumber) {
        Task task = tasksList.remove(taskNumber - 1);
        taskCount--;

        return "Task deleted: " + task.report() + "\nHere's the current list \n" + listTasks();
    }

    /**
     * Marks a task as done or undone based on the given task number and status.
     * 
     * @param taskNumber The 1-based index of the task to be updated.
     * @param done       A boolean indicating whether the task is done (true) or not (false).
     * @return A message indicating the updated status of the task.
     */
    public String setDone(Integer taskNumber, Boolean done) {
        if (taskNumber < 1 || taskNumber > tasksList.size()) {
            return "Task number " + taskNumber + " does not exist.";
        }
        
        Task task = tasksList.get(taskNumber - 1);
        task.setDone(done);
        return task.report();
    }

    /**
     * Lists all the tasks currently in the TaskManager.
     * 
     * @return A formatted string listing all tasks with their status and details.
     */
    public String listTasks() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasksList.size(); i++) {
            list.append(i + 1).append(". ").append(tasksList.get(i).report()).append("\n");
        }
        return list.toString();
    }
}
