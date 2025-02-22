package fleur;

import fleur.ui.MainWindow;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Fleur using FXML.
 */
public class Main extends Application {

    private Fleur fleur = new Fleur();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // inject the Fleur instance
            fxmlLoader.<MainWindow>getController().setFleur(fleur);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
