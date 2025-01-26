import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
     * A Integer variable that keeps track of the total number of tasks.
     */
    Integer taskCount = 0;

    /**
     * A Integer variable that keeps track of the total number of tasks completed.
     */
    Integer doneCount = 0;

    /**
     * A list that stores all the tasks managed by the TaskManager.
     */
    ArrayList<Task> tasksList;

    /**
    * The file path to the Mavis data file for this taskManager.  
    */
    private String FILE_PATH;


    /**
     * Constructor that initializes the TaskManager with an empty list of tasks.
     */
    public TaskManager(String FILE_PATH) {
        tasksList = new ArrayList<>();
        this.FILE_PATH = FILE_PATH;
        this.loadTasks();
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
        this.saveTasks();
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
        this.saveTasks();
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
        if (taskCount == 0) {
            return "You have no tasks in the list.\\n";
        }
        list.append("You have ").append(doneCount).append(" tasks done and ").append(taskCount - doneCount).append(" tasks to do.\n");
        for (int i = 0; i < tasksList.size(); i++) {
            list.append(i + 1).append(". ").append(tasksList.get(i).report()).append("\n");
        }
        return list.toString();
    }

    /**
     * Loads tasks from the file specified by {@code FILE_PATH} to taskList array.
     *      
     * @throws IOException If error occured when retrieving tasks from the file.
     */
    public void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    if (task.done) {
                        doneCount++;
                    }
                    taskCount++;
                    tasksList.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from the file.");
        }
    }
    /**
    * Parses a line of text into a {@link Task} object.
    *
    * @param line The line of text to parse.
    * @return A {@link Task} object if parsing is successful; {@code null} otherwise.
    */
    public Task parseTask(String line) {
        String[] parts = line.split(" ");
        String taskType = parts[0];
        Boolean isDone = parts[1].equals("1");
        String name = parts[2];
        Task task = null;
        switch (taskType) {
            case "T":
                task = new ToDo(name, isDone);
                break;
            case "D":
                String by = parts[3];
                task = new Deadline(name, by, isDone);
                break;
            case "E":
                String from = parts[3];
                String to = parts[4];
                task = new Event(name, from, to, isDone);
                break;
        }
        return task;
    }

    /**
    * Saves all tasks in {@code tasksList} to the file specified by {@code FILE_PATH}.
    *
    * @throws IOException If error occured when saving tasks to the file.
    */
    public void saveTasks() {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            if (!tasksList.isEmpty()) {
                for (Task task : tasksList) {
                    fw.write(task.saveTask() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to the file.");
        }
    }
}
