package mavis.command;

import mavis.MavisException;
import mavis.Storage;
import mavis.TaskList;
import mavis.Ui;

/**
 * The Command class is an abstract base class for all commands in the application.
 * It defines the structure for commands that can interact with the task list,
 * user interface, and storage. Concrete command classes should extend this class
 * and implement the specific execution logic.
 */
public abstract class Command {


    /**
     * Executes the command and returns the result as a string.
     *
     * @param tasks The list of tasks to be manipulated by the command.
     * @param ui The user interface that interacts with the user.
     * @param storage The storage that handles saving and loading of tasks.
     * @return The result of the command execution as a string.
     * @throws MavisException If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws MavisException;

    /**
     * Determines whether this command results in an exit action.
     * Concrete subclasses should return true if the command leads to the application exiting.
     *
     * @return true if the command results in an exit; false otherwise.
     */
    public abstract boolean isExit();
}
