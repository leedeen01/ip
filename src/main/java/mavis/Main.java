package mavis;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Mavis using FXML.
 */
public class Main extends Application {

    private Mavis mavisBot = new Mavis("src/main/data/Mavis.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            configureStage(stage, scene);
            fxmlLoader.<MainWindow>getController().setMavis(mavisBot);
            stage.show();
        } catch (IOException e) {
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
}
