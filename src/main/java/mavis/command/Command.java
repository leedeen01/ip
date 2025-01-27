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
     * Executes the command with the given task list, user interface, and storage.
     * Concrete subclasses should implement the specific logic for executing the command.
     *
     * @param tasks The task list to interact with during command execution.
     * @param ui The user interface used to interact with the user.
     * @param storage The storage used to save data during command execution.
     * @throws MavisException If an error occurs while executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MavisException;

    /**
     * Determines whether this command results in an exit action.
     * Concrete subclasses should return true if the command leads to the application exiting.
     *
     * @return true if the command results in an exit; false otherwise.
     */
    public abstract boolean isExit();
}
