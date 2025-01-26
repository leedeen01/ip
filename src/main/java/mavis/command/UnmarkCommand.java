package mavis.command;
import mavis.MavisException;
import mavis.Storage;
import mavis.TaskList;
import mavis.Ui;
import mavis.task.Task;

public class UnmarkCommand extends Command {
    Integer taskNumber;
    public UnmarkCommand(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MavisException{
        Task task = taskList.unmarkDone(taskNumber);
        storage.saveTasks(taskList);
        ui.showUnmarkTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
    
}
