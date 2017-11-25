package AppGUI.PopUpWindow;

import AppGUI.MainGUI;
import Observers.TreeViewObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NameLogPopUp {
    /**
     * Set up the NameLogPopUp window and display it .
     *
     * @throws Exception Is thrown when the FXMLLoader fails to read the source file
     */
    public void display(TreeViewObserver t) throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainGUI.class.getResource("PopUpWindow/NameLogPopUp.fxml"));
        Pane panel = loader.load();
        NameLogPopUpController controller = loader.getController();
        controller.setSetting(t);
        stage.setScene(new Scene(panel));
        stage.show();
    }

}
