package AppGUI.PopUpWindow;

import AppGUI.MainGUI;
import Observers.TreeViewObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Initialized by OpenPopUp.
 * Set up the stage for a pop-up window, initializes the controller and sets the treeView observer
 * that will be communicating with the controller.
 */
public class NameLogPopUp {


    /**
     * Displays the a new stage, with corresponding controller and sets tree view observer that contains
     * information of the selected image file to obtain tag modification history on.
     *
     * @param t the treeViewObserver of the treeView of the selected image file.
     * @throws Exception Is thrown when the FXMLLoader fails to read the source file
     */
    public void display(TreeViewObserver t) throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainGUI.class.getResource("PopUpWindow/NameLogPopUp.fxml"));
        Pane panel = loader.load();
        NameLogPopUpController controller = loader.getController();
        controller.setTreeViewObserver(t);
        stage.setScene(new Scene(panel));
        stage.show();
    }

}
