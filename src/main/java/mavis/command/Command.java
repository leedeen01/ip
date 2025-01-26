package mavis.command;
import mavis.MavisException;
import mavis.Storage;
import mavis.TaskList;
import mavis.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MavisException;
    public abstract boolean isExit();
}
