import java.util.HashMap;

public class TaskManager {
    static Integer taskCount = 0;
    HashMap<Integer, Task> tasksMap;
    public TaskManager() {
        tasksMap = new HashMap<>();
    }

    public String addTask(Task task) {
        taskCount++;
        tasksMap.put(taskCount, task);
        return "Task added: " + task;
    }

    public String deleteTask(Integer taskNumber) {
        if (!tasksMap.containsKey(taskNumber)) {
            return "Task number " + taskNumber + " does not exist.";
        }
        Task task = tasksMap.get(taskNumber);
        tasksMap.remove(taskNumber);

        for (int i = taskNumber; i < taskCount; i++) {
            tasksMap.put(i, tasksMap.get(i + 1));
        }
        tasksMap.remove(taskCount); 
        taskCount--;

        return "Task deleted: " + task.report() + "\nHeres the current list \n" + listTasks();
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
