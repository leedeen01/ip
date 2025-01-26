package mavis.command;
import mavis.MavisException;
import mavis.Storage;
import mavis.TaskList;
import mavis.Ui;
import mavis.task.Task;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MavisException{
        taskList.addTask(task);
        storage.saveTasks(taskList);
        ui.showTaskAdded(task);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
