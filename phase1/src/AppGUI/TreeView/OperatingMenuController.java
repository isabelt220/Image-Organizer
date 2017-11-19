package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.ImageManager;
import AppComponents.Tag;
import AppComponents.TagManager;
import AppGUI.MainContainer;
//import AppGUI.PaneNavigate;
import AppGUI.PopUpWindow.DialogBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class OperatingMenuController implements Initializable{
    @FXML
    private Button closeButton;
    @FXML
    private Button addButton;
    @FXML
    private TextField addTagTextField= new TextField();
    @FXML
    private TextField deleteTagTextField = new TextField();
    @FXML
    private Button deleteButton;

    private ImageData operatingImage;

    public void initialize(URL location, ResourceBundle r){
        addTagTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER){
                addTagButton();
            }}
        });

        deleteTagTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER){deleteTagButton(); }
            }
        });
    }

@FXML
    public void returnToOtherPane() throws IOException{
        MainContainer.getMain().showTreeView();
    }

    public void setOperatingMenu(ImageData chosenImage){
     operatingImage = chosenImage;
    }

    public void addTagButton(){
        ArrayList<String> tagEditorTagList = new ArrayList<>();
        tagEditorTagList.add(0, addTagTextField.getText());

        ImageData newNode = MainContainer.getAppImageManager().imAddTagWithImage(operatingImage, tagEditorTagList);
        operatingImage = newNode;
        MainContainer.getTreeViewController().reSetTree();
        MainContainer.getMiddleWindowController().setPanel(operatingImage.getLocation());
        addTagTextField.setText("");

    }

    public void deleteTagButton(){
        String targetTag = deleteTagTextField.getText();
        if(operatingImage.hasTag(targetTag)){
            Tag t = new Tag(targetTag);
            ArrayList<Tag> tagList = new ArrayList<>();
            tagList.add(t);
            MainContainer.getAppImageManager().removeTagFromPic(tagList,operatingImage);
            File newFile = new File(operatingImage.getLocation());
            MainContainer.getTreeViewController().reSetTree();
            MainContainer.getMiddleWindowController().setPanel(operatingImage.getLocation());
        }else{
            DialogBox warningBox = new DialogBox("Sorry","This Image does not have the tag you want to delete");
            warningBox.display();
        }
        deleteTagTextField.setText("");
    }


    TextField getAddTagTesxtField() {
        return addTagTextField;
    }

    TextField getDeleteTagTextField() {
        return deleteTagTextField;
    }

    public ImageData getOperatingImage() {
        return operatingImage;
    }

    public void setOperatingImage(ImageData operatingImage) {

        this.operatingImage = operatingImage;
    }
}
