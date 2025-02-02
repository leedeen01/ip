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
     * Executes the add command which adds a task to the task list, saves the updated task list to storage,
     * and returns a response message indicating that the task has been added.
     *
     * @param taskList The list of tasks to which the new task will be added.
     * @param ui The UI component that handles user interactions and displays messages.
     * @param storage The storage component that handles saving and loading tasks from persistent storage.
     * @return A response message indicating that the task has been added.
     * @throws MavisException If there is an error while saving the tasks to storage.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws MavisException {
        taskList.addTask(task);
        storage.saveTasks(taskList);
        String response = ui.showTaskAdded(task);
        return response;
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
