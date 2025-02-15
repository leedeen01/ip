package mavis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import mavis.task.Deadline;
import mavis.task.Event;
import mavis.task.Task;
import mavis.task.ToDo;

/**
 * The Storage class is responsible for loading and saving tasks to and from a file.
 * It handles task persistence, reading tasks from a specified file path, and saving
 * tasks back to the file when updated.
 */
public class Storage {

    /**
     * The file path to the Mavis data file for this task manager.
     */
    private String filePath;

    /**
     * Constructs a new Storage object with the specified file path.
     * @param filePath The path to the file where tasks are saved.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by {@code filePath} into a list of {@link Task} objects.
     * If the file is empty or does not exist, an empty list is returned.
     * @return A list of tasks loaded from the file.
     * @throws IOException If an error occurs while reading from the file.
     */
    public ArrayList<Task> loadTasks() throws MavisException {
        File file = new File(filePath);
        ArrayList<Task> tasksList = new ArrayList<>();
        if (!file.exists() || file.length() == 0) {
            return tasksList;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseFileTask(line);
                assert task != null : "Parsed task should never be null";
                tasksList.add(task);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from the file.");
        }
        return tasksList;
    }

    /**
     * Parses a line of text into a {@link Task} object based on the task type.
     * @param line The line of text to parse.
     * @return A {@link Task} object if parsing is successful; {@code null} otherwise.
     */
    public Task parseFileTask(String line) throws MavisException {
        String[] parts = line.split("\\|");
        String taskType = parts[0];
        Boolean isDone = parts[1].equals("1");
        String name = parts[2];
        Task task = null;

        switch (taskType) {
        case "T":
            task = new ToDo(name, isDone);
            break;
        case "D":
            assert parts.length == 4 : "Deadline task must have 4 parts";
            String by = parts[3];
            task = new Deadline(name, by, isDone);
            break;
        case "E":
            assert parts.length == 5 : "Event task must have 5 parts";
            String from = parts[3];
            String to = parts[4];
            task = new Event(name, from, to, isDone);
            break;
        default:
            throw new MavisException("Invalid task type found in file.");
        }
        return task;
    }

    /**
     * Saves all tasks in the specified {@link TaskList} to the file at {@code filePath}.
     * If the list is not empty, each task is written to the file, one per line.
     * @param taskList The task list to save.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void saveTasks(TaskList taskList) {
        ArrayList<Task> tasksList = taskList.getTasksList();
        try (FileWriter fw = new FileWriter(filePath)) {
            if (!tasksList.isEmpty()) {
                for (Task task : tasksList) {
                    assert task != null : "Task in list should not be null";
                    fw.write(task.saveTask() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("An error occurred while saving tasks to the file.");
        }
    }
}
