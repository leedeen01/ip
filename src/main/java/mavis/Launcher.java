
package mavis;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * The entry point of the application. It launches the {@link Main} JavaFX application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
