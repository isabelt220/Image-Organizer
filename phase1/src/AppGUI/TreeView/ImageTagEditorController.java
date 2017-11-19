package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.ImageManager;
import AppComponents.Tag;
import AppGUI.MainContainer;
import AppGUI.PopUpWindow.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ImageTagEditorController extends OperatingMenuController{
    @FXML
    private ImageView myImageView = new ImageView();

    @Override
    public void initialize(URL location, ResourceBundle r) {
        super.initialize(location,r);
        Image image = new Image(TreeViewController.selectedImage.toURI().toString());
        myImageView.setImage(image);
        ImageData i = ImageManager.getImage(TreeViewController.selectedImage.toPath().toString());
        setOperatingImage(i);

    }


}
