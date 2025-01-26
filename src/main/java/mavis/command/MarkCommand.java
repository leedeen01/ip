package mavis.command;
import mavis.MavisException;
import mavis.Storage;
import mavis.TaskList;
import mavis.Ui;
import mavis.task.Task;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MavisException{
        Task task = taskList.markDone(taskIndex);
        storage.saveTasks(taskList);
        ui.showMarkTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
