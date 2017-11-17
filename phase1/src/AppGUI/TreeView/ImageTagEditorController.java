package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.TagManager;
import AppGUI.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
        System.out.println(addTagFieldTagEditor.getText());

        System.out.println("New list from text: " + tagEditorTagList);
        MainController.getAppImageManager().imAddTagWithImage(currImage, tagEditorTagList);
        System.out.println(MainController.getAppTagManager().getListOfTags());
        MainController.getTreeViewController().updateListView(TagManager.getObservableTagList());
//        System.out.println(MainController.getAppImageManager().getImageList().get(0));
//        System.out.println(MainController.getAppTagManager().getListOfTags().get(0));

    }

}
