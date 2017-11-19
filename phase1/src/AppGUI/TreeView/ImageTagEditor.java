package AppGUI.TreeView;

import AppComponents.ImageData;
import AppGUI.MainGUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


class ImageTagEditor{


    void display() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainGUI.class.getResource("TreeView/ImageTagEditor.fxml"));
        Pane panel = loader.load();
        stage.setScene(new Scene(panel));
        stage.show();
    }

}
