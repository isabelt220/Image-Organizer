package AppGUI.PopUpWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NameLogPopUp {
    /**
     * Set up the NameLogPopUp window and display it .
     *
     * @throws Exception Is thrown when the FXMLLoader fails to read the source file
     */
    public void display() throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("NameLogPopUp.fxml"));
        stage.setScene(new Scene(root, 543, 458));
        stage.show();
    }

}
