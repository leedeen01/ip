package mavis;
import mavis.command.Command;

/**
 * Mavis is a simple task management application that interacts with
 * the user via the command line to add, delete, mark, and unmark tasks.
 * It supports ToDo, Deadline, and Event tasks.
 * Invalid input is handled with error messages.
 */
public class Mavis {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a Mavis object with the specified file path.
     * Initializes the UI, storage, and task list.
     *
     * @param filePath The file path to load and save tasks.
     */
    public Mavis(final String filePath) {
        ui = new Ui();
        assert ui != null : "Ui should not be null";
        storage = new Storage(filePath);
        assert storage != null : "Storage should not be null";
        try {
            this.taskList = new TaskList(storage.loadTasks());
            assert taskList != null : "TaskList should not be null";
        } catch (MavisException e) {
            this.taskList = new TaskList();
            storage.saveTasks(taskList);
        }
    }

    /**
     * Processes the user input, executes the corresponding command,
     * and returns the response as a string.
     *
     * This method parses the input into a {@link Command}, executes it
     * using the current task list, UI, and storage, and returns the
     * resulting response. If an exception occurs, an error message is returned.
     *
     * @param input The user input command as a string.
     * @return The response message after executing the command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(taskList, ui, storage);
            return response;
        } catch (MavisException e) {
            return "An error occurred: " + e.getMessage();
        }
    }
}
