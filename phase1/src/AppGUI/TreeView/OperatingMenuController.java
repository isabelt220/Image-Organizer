package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.ImageManager;
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
    public Button closeButton;
    @FXML
    public Button addButton;
    @FXML
    public TextField addTagTestField= new TextField();
    @FXML
    public TextField deleteTagTestField = new TextField();
    @FXML
    public Button deleteButton;

    private ImageData operatingImage;

    public void initialize(URL location, ResourceBundle r){
        addTagTestField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER){
                addTagButton();
            }}
        });

        deleteTagTestField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER){deleteTagButton();}
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
        tagEditorTagList.add(0, addTagTestField.getText());
        System.out.println(operatingImage);
        ImageData newNode = MainContainer.getAppImageManager().imAddTagWithImage(operatingImage, tagEditorTagList);
        File f= new File(newNode.getLocation());
        MainContainer.getTreeViewController().reSetTree();
        MainContainer.getMiddleWindowController().setPanel(operatingImage.getLocation());

    }

    public void deleteTagButton(){
    }


}
