package AppGUI.TreeView;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class ImageTagEditorController {
    @FXML
     private ImageView myImageView = new ImageView();

    public void initialize() {
        myImageView.setImage(TreeViewController.selectedImage);
    }
}
