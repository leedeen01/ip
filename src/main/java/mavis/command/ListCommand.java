package mavis.command;

import mavis.Storage;
import mavis.TaskList;
import mavis.Ui;

/**
 * The ListCommand class represents a command to list all tasks in the task list.
 * It extends the Command class and implements the logic to display all tasks to the user.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all the tasks in the current task list.
     * It retrieves all tasks and passes them to the user interface to be printed.
     *
     * @param taskList The task list that holds all the tasks to be displayed.
     * @param ui The user interface used to show feedback to the user.
     * @param storage The storage for saving the current state of tasks (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTasks(taskList);
    }

    /**
     * Determines whether this command results in an exit action.
     * Since this command is for listing tasks, it returns false, indicating that the application will not exit.
     *
     * @return false, indicating that the list command does not result in exiting the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
