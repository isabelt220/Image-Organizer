package AppGUI.PopUpWindow;

import AppGUI.MainGUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Initialized by OpenPopUp.
 * Set up the stage for a pop-up window, initializes the controller and sets the data of Master log that
 * will be displayed in the pop-up
 */
public class MasterLogPopUp {

    /**
     * Displays the a new stage, with corresponding controller and calls showView in the controller
     * to set up and obtain tag modification history from mainContainer.
     *
     * @throws Exception Is thrown when the FXMLLoader fails to read the source file
     */
    public void display() throws Exception {
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