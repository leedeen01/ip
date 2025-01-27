package mavis;
import mavis.command.*;

/**
 * Mavis is a simple task management application that interacts with the user via
 * the command line to add, delete, mark, and unmark tasks.
 * It supports ToDo, Deadline, and Event tasks.
 * Invalid input is handled with error messages.
 */
public class Mavis {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a Mavis application with the specified file path for storing tasks.
     * 
     * @param filePath The path to the file where tasks are stored.
     */
    public Mavis(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.taskList = new TaskList(storage.loadTasks());
    }

    /**
     * Runs the Mavis application, allowing the user to interact with it by entering commands.
     * It continuously processes commands until the exit condition is met.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
                ui.showLine();
            } catch (MavisException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main entry point of the Mavis application.
     * Initializes the Mavis application with a default file path and runs it.
     * 
     * @param args Command-line arguments (not used in this case).
     */
    public static void main(String[] args) {
        new Mavis("src/main/data/Mavis.txt").run();
    }
}
