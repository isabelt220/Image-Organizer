package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.ImageManager;
import AppGUI.MainContainer;
//import AppGUI.PaneNavigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
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
    public TextField deleteTagTestField;
    @FXML
    public Button deleteButton;


    ImageData operatingImage;

    public void initialize(URL location, ResourceBundle r){}

@FXML
    public void returnToOtherPane() throws IOException{
        MainContainer.getMain().showTreeView();
    }

    public void setOperatingMenu(ImageData chosenImage){
     operatingImage = chosenImage;
    }

    public void addTagButton(){
        File selectedFile = MainContainer.getTreeViewController().treeView.getSelectionModel().getSelectedItem().getValue();
        ImageData currImage = MainContainer.getAppImageManager().getImage(selectedFile.toPath().toString());

        ArrayList<String> tagEditorTagList = new ArrayList<>();
        tagEditorTagList.add(0, addTagTestField.getText());

        ImageData newNode = MainContainer.getAppImageManager().imAddTagWithImage(currImage, tagEditorTagList);
        File f= new File(newNode.getLocation());
        MainContainer.getTreeViewController().treeView.getSelectionModel().getSelectedItem().setValue(f);
        MainContainer.getMiddleWindowController().setPanel(MainContainer.getTreeViewController().treeView.getSelectionModel().getSelectedItem().getValue().toPath().toString());

    }

    public void deleteTagButton(){
    }


}
