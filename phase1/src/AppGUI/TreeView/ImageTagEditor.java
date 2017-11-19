package AppGUI.TreeView;

import AppComponents.ImageData;
import AppGUI.MainGUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;

public class ImageTagEditor{


    public void display(ImageData image) throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainGUI.class.getResource("TreeView/ImageTagEditor.fxml"));
        Pane panel = loader.load();
        stage.setScene(new Scene(panel));
        stage.show();
    }

}
