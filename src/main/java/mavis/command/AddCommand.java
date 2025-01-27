package mavis.command;

import mavis.MavisException;
import mavis.Storage;
import mavis.TaskList;
import mavis.Ui;
import mavis.task.Task;

/**
 * The AddCommand class is responsible for adding a task to the task list.
 * It extends the abstract Command class and implements the logic for adding a task to the TaskList,
 * saving the updated task list to storage, and displaying a confirmation message via the UI.
 */
public class AddCommand extends Command {

    /**
     * The task to be added to the task list.
     */
    private Task task;

    /**
     * Constructs an AddCommand to add the specified task to the task list.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the AddCommand by adding the task to the task list,
     * saving the updated task list to storage, and showing a confirmation message.
     *
     * @param taskList The task list to which the task is added.
     * @param ui The UI instance used to show the task added confirmation.
     * @param storage The storage instance used to save the updated task list.
     * @throws MavisException If an error occurs while adding the task or saving to storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MavisException {
        taskList.addTask(task);
        storage.saveTasks(taskList);
        ui.showTaskAdded(task);
    }

    /**
     * Checks if this command is an exit command.
     *
     * @return false, since the AddCommand does not result in an exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
