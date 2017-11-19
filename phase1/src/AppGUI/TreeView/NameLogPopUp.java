package AppGUI.TreeView;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NameLogPopUp {

    public void display() throws Exception{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../TreeView/NameLogPopUp.fxml"));
        stage.setScene(new Scene(root, 543, 458));
        stage.show();
    }

}
