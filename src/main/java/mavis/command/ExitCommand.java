package mavis.command;
import mavis.Storage;
import mavis.TaskList;
import mavis.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveTasks(taskList);
        ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
