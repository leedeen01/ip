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

public class Storage {
    /**
    * The file path to the Mavis data file for this taskManager.  
    */
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Loads tasks from the file specified by {@code FILE_PATH} to taskList array.
     *      
     * @throws IOException If error occured when retrieving tasks from the file.
     */
    public ArrayList<Task> loadTasks() {
        File file = new File(filePath);
        ArrayList<Task> tasksList = new ArrayList<>();
        if (!file.exists() || file.length() == 0) {
            return tasksList;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseFileTask(line);
                if (task != null) {
                    tasksList.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from the file.");
        }
        return tasksList;
    }

    /**
    * Parses a line of text into a {@link Task} object.
    *
    * @param line The line of text to parse.
    * @return A {@link Task} object if parsing is successful; {@code null} otherwise.
    */
    public Task parseFileTask(String line) {
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
                String by = parts[3];
                System.out.println(by);
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
    public void saveTasks(TaskList taskList) {
        ArrayList<Task> tasksList = taskList.getTasksList();
        try (FileWriter fw = new FileWriter(filePath)) {
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
