package mavis.command;
import mavis.Storage;
import mavis.TaskList;
import mavis.Ui;


public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
    
}
