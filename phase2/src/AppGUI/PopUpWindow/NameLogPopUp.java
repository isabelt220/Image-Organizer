package AppGUI.PopUpWindow;

import AppGUI.AppFile;
import AppGUI.CenterPanel.MiddleWindowController;
import AppGUI.MainGUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class NameLogPopUp {
    /**
     * Set up the NameLogPopUp window and display it .
     *
     * @throws Exception Is thrown when the FXMLLoader fails to read the source file
     */
    public void display(AppFile t) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainGUI.class.getResource("PopUpWindow/NameLogPopUp.fxml"));
        Pane panel = loader.load();
        NameLogPopUpController n = loader.getController();
        n.setTarget(t);
        stage.setScene(new Scene(panel));
        stage.show();

    }

}