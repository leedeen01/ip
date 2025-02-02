package mavis.command;

import java.util.ArrayList;

import mavis.Storage;
import mavis.TaskList;
import mavis.Ui;
import mavis.task.Task;

/**
 * Represents a command that searches for tasks in the task list based on a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, searching for tasks that match the given keyword.
     * The matching tasks are then displayed to the user.
     *
     * @param taskList The list of tasks to search through.
     * @param ui The user interface that displays the matching tasks.
     * @param storage The storage system used to save and retrieve tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = taskList.findTasks(keyword);
        String response = ui.showMatchingTasks(matchingTasks);
        return response;
    }

    /**
     * Indicates whether this command will terminate the program.
     *
     * @return false, as the FindCommand does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
