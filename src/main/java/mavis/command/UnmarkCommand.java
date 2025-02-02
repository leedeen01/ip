package mavis.command;

import mavis.MavisException;
import mavis.Storage;
import mavis.TaskList;
import mavis.Ui;
import mavis.task.Task;

/**
 * The UnmarkCommand class represents a command to unmark a task as completed (not done).
 * It extends the Command class and implements the logic to update the task's completion status to incomplete.
 */
public class UnmarkCommand extends Command {
    private Integer taskNumber;

    /**
     * Constructs an UnmarkCommand with the specified task number.
     * This number will be used to find and unmark the corresponding task as not done.
     * @param taskNumber The number of the task to be unmarked.
     */
    public UnmarkCommand(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the unmark command by updating the task at the specified index to be unmarked (not done).
     * It then saves the updated task list and shows feedback to the user.
     *
     * @param taskList The task list containing all the tasks.
     * @param ui The user interface used to display feedback to the user.
     * @param storage The storage for saving the updated task list.
     * @throws MavisException If an error occurs while unmarking the task (e.g., invalid index).
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws MavisException {
        Task task = taskList.unmarkDone(taskNumber);
        storage.saveTasks(taskList);
        String response = ui.showUnmarkTask(task);
        return response;
    }

    /**
     * Determines whether this command results in an exit action.
     * Since this command is for unmarking a task as not done, it returns false,
     * indicating that the application will not exit.
     *
     * @return false, indicating that the unmark command does not result in exiting the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
