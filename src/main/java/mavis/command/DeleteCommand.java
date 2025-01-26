package mavis.command;
import mavis.MavisException;
import mavis.Storage;
import mavis.TaskList;
import mavis.Ui;
import mavis.task.Task;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MavisException {
        Task task = taskList.deleteTask(index);
        storage.saveTasks(taskList);
        ui.showDeleteTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
