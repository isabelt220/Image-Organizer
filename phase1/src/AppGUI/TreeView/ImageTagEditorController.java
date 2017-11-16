package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.ImageManager;
import AppComponents.TagManager;
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


    public void addTagExistingImage() {
        ImageData currImage = new ImageData(
                TreeViewController.selectedImage.getUrl());

        ArrayList<String> tagEditorTagList = new ArrayList<>();
        tagEditorTagList.add(0, addTagFieldTagEditor.getText());

        for (ImageData i : ImageManager.getImageList()) {
            if (((ImageData) i).equals(currImage)) {
                ImageManager.imAddTagWithImage(i, tagEditorTagList);
                return;
            }
        }

        this.addTagNewImage();
    }

    public void addTagNewImage(){
        // Need to complete.
    }

}
