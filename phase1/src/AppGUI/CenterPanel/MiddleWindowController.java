package AppGUI.CenterPanel;

import AppComponents.ImageData;
import AppComponents.Tag;
import AppGUI.TreeView.TreeViewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MiddleWindowController implements Initializable{
    @FXML
    static VBox imageDisplayer = new VBox(10);
    private static File selectedFile;

    public void initialize(URL location, ResourceBundle r){}

    public static void sethBox(TreeItem<File> node){
        TreeItem<File> dirNode = node;
        if(!node.getValue().isDirectory()){
            dirNode = node.getParent();
        }
        for(TreeItem<File> t: dirNode.getChildren()){
            if(!t.getValue().isDirectory()){
                ImageView imageView = new ImageView(new Image(t.getValue().toURI().toString()));
                imageDisplayer.getChildren().add(imageView);}
        }
    }

}