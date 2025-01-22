import java.util.HashMap;

public class TaskManager {
    static Integer taskCount = 0;
    static HashMap<Integer, Task> tasksMap = new HashMap<>();
    public TaskManager() {
        
    }

    public String addTask(Task task) {
        taskCount++;
        tasksMap.put(taskCount, task);
        return "Task added: " + task;
    }

    public String setDone(Integer taskNumber, Boolean done) {
        Task task = tasksMap.get(taskNumber);
        task.setDone(done);
        return task.report();
    }

    public String listTasks() {
        String list = "";
        for (int i = 1; i <= taskCount; i++) {
            list += i + ". " + tasksMap.get(i).report() + "\n";
        }
        return list;
    }

}
