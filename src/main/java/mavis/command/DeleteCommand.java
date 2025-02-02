package mavis.command;

import mavis.MavisException;
import mavis.Storage;
import mavis.TaskList;
import mavis.Ui;
import mavis.task.Task;

/**
 * The DeleteCommand class represents a command to delete a task from the task list.
 * It extends the Command class and implements the logic for removing a task by its index.
 * The task is then deleted from the list, the task list is saved to storage,
 * and the user is informed of the deletion.
 */
public class DeleteCommand extends Command {
    /**
     * The index of the task to be deleted in the task list.
     */
    private int index;

    /**
     * Constructs a DeleteCommand with the specified index of the task to delete.
     *
     * @param index The index of the task to delete in the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task at the specified index from the task list.
     * The task list is then saved to storage, and the user is notified about the task deletion.
     *
     * @param taskList The task list that holds all tasks.
     * @param ui The user interface used to show feedback to the user.
     * @param storage The storage to save the updated task list.
     * @throws MavisException If the index is invalid or the task list is empty.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws MavisException {
        Task task = taskList.deleteTask(index);
        storage.saveTasks(taskList);
        String response = ui.showDeleteTask(task);
        return response;
    }

    /**
     * Determines whether this command results in an exit action.
     * Since deleting a task does not cause an exit, this method returns false.
     *
     * @return false as the delete command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
