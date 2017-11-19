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
import java.util.ArrayList;

public class ImageTagEditorController {
    @FXML
    private ImageView myImageView = new ImageView();
    @FXML
    private TextField addTagFieldTagEditor = new TextField();
    @FXML
    private TextField deleteTagFieldTagEditor = new TextField();

    public void initialize() {
        Image image = new Image(TreeViewController.selectedImage.toURI().toString());
        myImageView.setImage(image);

    }


    public void addTagToImage() {
        File selectedFile = MainContainer.getTreeViewController().getTreeView().getSelectionModel().getSelectedItem().getValue();
        ImageData currImage = MainContainer.getAppImageManager().getImage(selectedFile.toPath().toString());

        ArrayList<String> tagEditorTagList = new ArrayList<>();
        tagEditorTagList.add(0, addTagFieldTagEditor.getText());

        ImageData newNode = MainContainer.getAppImageManager().imAddTagWithImage(currImage, tagEditorTagList);
        File f= new File(newNode.getLocation());
        MainContainer.getTreeViewController().getTreeView().getSelectionModel().getSelectedItem().setValue(f);
        MainContainer.getMiddleWindowController().setPanel(MainContainer.getTreeViewController().getTreeView().getSelectionModel().getSelectedItem().getValue().toPath().toString());

    }

    public void deleteTagFromImage(){
        String targetTag = deleteTagFieldTagEditor.getText();
        File selectedFile = MainContainer.getTreeViewController().getTreeView().getSelectionModel().getSelectedItem().getValue();
        ImageData currentImage = ImageManager.getImage(selectedFile.toPath().toString());
        if(currentImage.hasTag(targetTag)){
            MainContainer.getAppImageManager().removeTagFromPic(targetTag);
            MainContainer.getTreeViewController().getTreeView().getSelectionModel().getSelectedItem().setValue(selectedFile);
            MainContainer.getMiddleWindowController().setPanel(MainContainer.getTreeViewController().getTreeView().getSelectionModel().getSelectedItem().getValue().toPath().toString());
        }else{
            DialogBox warningBox = new DialogBox("Sorry","This Image does not have the tag you want to delete");
            warningBox.display();
        }
        }

}
