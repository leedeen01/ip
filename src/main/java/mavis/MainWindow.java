package mavis;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI of the Mavis application.
 * <p>
 * This class manages the main window of the application, handling user input, displaying dialog boxes
 * for interaction, and managing the layout of the user interface.
 * </p>
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Mavis mavis;

    /**
     * The image representing the user in the dialog box.
     */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    /**
     * The image representing Mavis (the bot) in the dialog box.
    */
    private Image mavisImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window by binding the scroll pane's value property to the dialog container's height property
     * and adding an initial dialog box from Mavis.
     * <p>
     * This method ensures that the dialog container automatically scrolls as new messages are added.
     * </p>
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
            DialogBox.getMavisDialog("Hello! I'm Mavis\nHow can i help you?", mavisImage)
        );
    }

    /**
     * Injects the Mavis instance into this controller to facilitate interaction with the task manager.
     *
     * @param m The Mavis instance to be injected.
     */
    public void setMavis(Mavis m) {
        mavis = m;
    }

    /**
     * Handles the user input, creating two dialog boxes: one for the user input and one for Mavis's response.
     * The dialog boxes are then added to the dialog container.
     * The user input field is cleared after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mavis.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMavisDialog(response, mavisImage)
        );
        userInput.clear();
    }
}
