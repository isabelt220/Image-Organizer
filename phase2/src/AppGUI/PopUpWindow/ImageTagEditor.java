package AppGUI.PopUpWindow;

import AppGUI.MainGUI;
import Observers.CenterObserver;
import Observers.TreeViewObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Initialized by OpenPopUp which is in turn initialized by TreeViewController.
 * Sets up the stage and controller for a pop up window allowing the user to perform tag modification on the
 * selected file in treeView, and updates centerView for changes in image name.
 */
class ImageTagEditor{


    /**
     * Initializes a new stage (pop up window), and controller for this ImageTagEditor.
     * Then sets the treeView, and center observer for the controller for communication of tag modification
     * between panels. Then loads the scene ImageTagEditor onto the stage.
     *
     * @param t treeViewObserver initialized by MainGUI, and is used to obtain selected file information for the ImageTagEditor.
     * @param c centerViewObserver initialized by MainGUI, and is used to obtain selected file information for the ImageTagEditor.
     * @throws Exception IOException
     */
    void display(TreeViewObserver t, CenterObserver c) throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainGUI.class.getResource("PopUpWindow/ImageTagEditor.fxml"));
        Pane panel = loader.load();
        ImageTagEditorController controller = loader.getController();
        controller.setTreeViewObserver(t);
        controller.setCenterObserver(c);
        controller.setView(t);
        stage.setScene(new Scene(panel));
        stage.show();
    }

}
