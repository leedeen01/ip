import java.util.ArrayList;

public class TaskManager {
    static Integer taskCount = 0;
    ArrayList<Task> tasksList;

    public TaskManager() {
        tasksList = new ArrayList<>();
    }

    public String addTask(Task task) {
        tasksList.add(task);
        taskCount++;
        return "Task added: " + task;
    }

    public String deleteTask(Integer taskNumber) {
        Task task = tasksList.remove(taskNumber - 1);
        taskCount--;

        return "Task deleted: " + task.report() + "\nHere's the current list \n" + listTasks();
    }

    public String setDone(Integer taskNumber, Boolean done) {
        if (taskNumber < 1 || taskNumber > tasksList.size()) {
            return "Task number " + taskNumber + " does not exist.";
        }
        
        Task task = tasksList.get(taskNumber - 1);
        task.setDone(done);
        return task.report();
    }

    public String listTasks() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasksList.size(); i++) {
            list.append(i + 1).append(". ").append(tasksList.get(i).report()).append("\n");
        }
        return list.toString();
    }
}
