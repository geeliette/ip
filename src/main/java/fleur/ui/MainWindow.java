package fleur.ui;

import fleur.Fleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Controller for the main GUI.
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

    private Fleur fleur;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/cat.png"));
    private Image fleurImage = new Image(this.getClass().getResourceAsStream("/images/flower.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Fleur instance */
    public void setFleur(Fleur f) {
        fleur = f;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Fleur's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = fleur.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFleurDialog(response, fleurImage)
        );
        userInput.clear();

        if (fleur.isExit(input)) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
            fleur.saveOnExit();
        }
    }
}
