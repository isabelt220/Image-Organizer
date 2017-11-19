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
    private ImageView myImageView = new ImageView();

    @Override
    public void initialize(URL location, ResourceBundle r) {
        super.initialize(location, r);
        Image image = new Image(TreeViewController.selectedImage.toURI().toString());
        myImageView.setImage(image);
        ImageData i = MainContainer.getAppImageManager().getImage(TreeViewController.selectedImage.toPath().toString());
        setOperatingImage(i);

    }

}
