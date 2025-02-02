package mavis.command;

import mavis.Storage;
import mavis.TaskList;
import mavis.Ui;

/**
 * The ExitCommand class represents a command to exit the application.
 * It extends the Command class and implements the logic for saving the current task list
 * to storage and displaying a goodbye message before exiting the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by saving the current task list to storage
     * and displaying a goodbye message to the user.
     *
     * @param taskList The task list that holds all tasks.
     * @param ui The user interface used to show feedback to the user.
     * @param storage The storage to save the current task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveTasks(taskList);
        String response = ui.showGoodbyeMessage();
        return response;
    }

    /**
     * Determines whether this command results in an exit action.
     * Since this command is meant to exit the application, it returns true.
     *
     * @return true, indicating that the exit command results in exiting the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
