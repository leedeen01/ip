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

    public Mavis(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.taskList = new TaskList(storage.loadTasks());
    }

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

    public static void main(String[] args) {
        new Mavis("src/main/data/Mavis.txt").run();
    }
}
