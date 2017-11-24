package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.ImageManager;
import AppGUI.MainContainer;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class ImageTagEditorController extends OperatingMenuController {
    @FXML
    //Placement of image that the ImageTagEditor is currently editing.
    private ImageView myImageView = new ImageView();


    /**
     * Initializes the image view by taking the selected image File and sets it in the myImageView of the fxml.
     * @param location URL
     * @param r ResourceBundle
     */
    @Override
    public void initialize(URL location, ResourceBundle r) {
        super.initialize(location, r);
        Image image = new Image(getOperatingImage().getUrl());
        myImageView.setImage(image);


    }

}