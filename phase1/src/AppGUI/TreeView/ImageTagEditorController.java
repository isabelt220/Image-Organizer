package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.TagManager;
import AppGUI.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;

public class ImageTagEditorController {
    @FXML
    private ImageView myImageView = new ImageView();
    @FXML
    private TextField addTagFieldTagEditor = new TextField();

    public void initialize() {
        Image image = new Image(TreeViewController.selectedImage.toURI().toString());
        myImageView.setImage(image);

    }


    public void addTagToImage() {
        ImageData currImage = new ImageData(
                TreeViewController.selectedImage.toPath().toString());
        ArrayList<String> tagEditorTagList = new ArrayList<>();
        tagEditorTagList.add(0, addTagFieldTagEditor.getText());
        ImageData newNode = MainController.getAppImageManager().imAddTagWithImage(currImage, tagEditorTagList);
        File f= new File(newNode.getLocation());
        MainController.getTreeViewController().treeView.getSelectionModel().getSelectedItem().setValue(f);


    }

}
