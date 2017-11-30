package AppGUI.PopUpWindow;

import AppGUI.MainGUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MasterLogPopUp {

    public void display() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainGUI.class.getResource("PopUpWindow/MasterLogPopUp.fxml"));
        Pane panel = loader.load();
        MasterLogPopUpController controller = loader.getController();
        controller.showView();
        stage.setScene(new Scene(panel));
        stage.show();
    }
}
