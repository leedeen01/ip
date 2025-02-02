package mavis.command;

import mavis.MavisException;
import mavis.Storage;
import mavis.TaskList;
import mavis.Ui;
import mavis.task.Task;

/**
 * The MarkCommand class represents a command to mark a task as completed (done).
 * It extends the Command class and implements the logic to update the task's completion status.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index.
     * This index will be used to find and mark the corresponding task as done.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command by updating the task at the specified index to be marked as done.
     * It then saves the updated task list and shows feedback to the user.
     *
     * @param taskList The task list containing all the tasks.
     * @param ui The user interface used to display feedback to the user.
     * @param storage The storage for saving the updated task list.
     * @throws MavisException If an error occurs while marking the task (e.g., invalid index).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MavisException {
        Task task = taskList.markDone(taskIndex);
        storage.saveTasks(taskList);
        ui.showMarkTask(task);
    }

    /**
     * Determines whether this command results in an exit action.
     * Since this command is to mark task as done, it returns false, indicating that the application will not exit.
     *
     * @return false, indicating that the mark command does not result in exiting the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
