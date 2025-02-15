package mavis;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Mavis using FXML.
 */
public class Main extends Application {
    /**
     * The Mavis instance used to interact with the task management system.
     * This object is responsible for handling of UI, storage of tasks
     * and handling user input.
     */
    private Mavis mavisBot;

    /**
     * Initializes and starts the JavaFX application by loading the main window's FXML file,
     * configuring the stage, and displaying the window.
     * This method is called when the application is launched.
     *
     * @param stage The primary stage for this application, provided by the JavaFX runtime.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Get the directory where the JAR file is located
            String filePath = getFilePath();

            // Ensure the directory and file exist
            ensureDirectoryAndFile(filePath);

            // Initialize Mavis with the file path
            mavisBot = new Mavis(filePath);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            configureStage(stage, scene);
            fxmlLoader.<MainWindow>getController().setMavis(mavisBot);
            stage.show();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Configures the given {@link Stage} with the specified {@link Scene}.
     * Sets the scene and adjusts the stage's minimum and maximum dimensions.
     *
     * @param stage The primary stage to configure.
     * @param scene The scene to be set on the stage.
     */
    public void configureStage(Stage stage, Scene scene) {
        stage.setScene(scene);
        stage.setMinHeight(220);
        stage.setMinWidth(417);
        stage.setMaxWidth(417);
    }

    /**
     * Constructs the correct file path for Mavis.txt relative to the JAR file's location.
     *
     * @return The file path for the Mavis.txt file.
     * @throws URISyntaxException If there is an error in URI processing.
     */
    private String getFilePath() throws URISyntaxException {
        File jarFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        String jarDirectory = jarFile.getParent(); // Path to the directory where the .jar is stored
        return jarDirectory + File.separator + "data" + File.separator + "Mavis.txt";
    }

    /**
     * Ensures the 'data' directory exists, and creates it if it doesn't.
     * Also creates the Mavis.txt file if it doesn't already exist.
     *
     * @param filePath The file path to the Mavis.txt file.
     * @throws IOException If an error occurs during file or directory creation.
     */
    private void ensureDirectoryAndFile(String filePath) throws IOException {
        File directory = new File(filePath).getParentFile();
        if (!directory.exists()) {
            boolean dirCreated = directory.mkdirs();
            if (dirCreated) {
                System.out.println("Directory created: " + directory.getAbsolutePath());
            }
        }
        File file = new File(filePath);
        if (!file.exists()) {
            boolean fileCreated = file.createNewFile();
            if (fileCreated) {
                System.out.println("File created: " + file.getAbsolutePath());
            }
        }
    }
}
