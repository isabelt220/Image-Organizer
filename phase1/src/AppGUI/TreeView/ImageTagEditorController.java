package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.ImageManager;
import AppGUI.MainController;
import javafx.fxml.FXML;
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
        File selectedFile = MainController.getTreeViewController().treeView.getSelectionModel().getSelectedItem().getValue();
        ImageData currImage = MainController.getAppImageManager().getImage(selectedFile.toPath().toString());

        ArrayList<String> tagEditorTagList = new ArrayList<>();
        tagEditorTagList.add(0, addTagFieldTagEditor.getText());

        ImageData newNode = MainController.getAppImageManager().imAddTagWithImage(currImage, tagEditorTagList);
        File f= new File(newNode.getLocation());
        MainController.getTreeViewController().treeView.getSelectionModel().getSelectedItem().setValue(f);



    }

}
