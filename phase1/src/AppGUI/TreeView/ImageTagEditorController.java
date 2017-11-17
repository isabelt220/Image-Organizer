package AppGUI.TreeView;

import AppComponents.ImageData;
import AppGUI.MainController;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.ArrayList;

public class ImageTagEditorController {
    @FXML
    private ImageView myImageView = new ImageView();
    @FXML
    private TextField addTagFieldTagEditor = new TextField();

    public void initialize() {
        myImageView.setImage(TreeViewController.selectedImage);
    }


    public void addTagToImage() {
        ImageData currImage = new ImageData(
                TreeViewController.selectedImage.getUrl());

        ArrayList<String> tagEditorTagList = new ArrayList<>();
        tagEditorTagList.add(0, addTagFieldTagEditor.getText());

        MainController.getAppImageManager().imAddTagWithImage(currImage, tagEditorTagList);

    }

}
