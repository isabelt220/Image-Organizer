package AppGUI.PopUpWindow;

import AppComponents.ImageData;
import AppGUI.MainGUI;
import AppGUI.TreeView.TreeViewController;
import Observers.TreeViewObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


class ImageTagEditor{


    /**
     * Initializes a new stage (window for popup) and loads the scene ImageTagEditor onto it.
     * @throws Exception
     */
    void display(TreeViewObserver t) throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainGUI.class.getResource("PopUpWindow/ImageTagEditor.fxml"));
        Pane panel = loader.load();
        ImageTagEditorController controller = loader.getController();
        controller.setView(t);
        stage.setScene(new Scene(panel));
        stage.show();
    }

}
