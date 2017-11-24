package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.Tag;
import AppGUI.AppFile;
import AppGUI.MainContainer;
import AppGUI.MainGUI;
import AppGUI.PopUpWindow.DialogBox;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class OperatingMenuController implements Initializable {

    @FXML
    private TextField addTagTextField = new TextField();
    @FXML
    private TextField deleteTagTextField = new TextField();

    private AppFile operatingImage;

    private MainGUI main;

    /**
     * Initializes the two textFields for addTag and deleteTag.
     * @param location URL
     * @param r ResourceBundle
     */
    public void initialize(URL location, ResourceBundle r) {
        addTagTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    addTagButton();
                }
            }
        });

        deleteTagTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    deleteTagButton();
                }
            }
        });
    }

    /**
     * Exits the current OperatingMenu and reverts back to the original treeView.
     * @throws IOException
     */
    @FXML
    public void returnToOtherPane() {
        main.showTreeView();
    }


    /**
     * Gets the tag entered into addTag textField and calls classes in AppComponents to add tag to operating image
     * accordingly.
     */
    public void addTagButton() {
        ArrayList<String> tagEditorTagList = new ArrayList<>();
        tagEditorTagList.add(0, addTagTextField.getText());
        ImageData image = MainContainer.getAppImageManager().getImage(operatingImage.getLocation());
        if(image==null){
            image = new ImageData(operatingImage.getLocation());
        }
        MainContainer.getAppImageManager().imAddTagWithImage(image, tagEditorTagList);
        File f = new File(image.getLocation());
        operatingImage.setCurrentFile(f);
        addTagTextField.setText("");

    }

    /**
     * Gets the tag entered into deleteTag textField and calls classes in AppComponents to delete tag to operating image
     * accordingly.
     */
    public void deleteTagButton() {
        String targetTag = deleteTagTextField.getText();
        ImageData image = MainContainer.getAppImageManager().getImage(operatingImage.getLocation());
        if (image != null) {
            Tag t = new Tag(targetTag);
            ArrayList<Tag> tagList = new ArrayList<>();
            tagList.add(t);
            MainContainer.getAppImageManager().removeTagFromPic(tagList, image);
            File f = new File(image.getLocation());
            operatingImage.setCurrentFile(f);
        } else {
            DialogBox warningBox = new DialogBox("Sorry", "This Image does not have the tag you want to delete");
            warningBox.display();
        }
        deleteTagTextField.setText("");
    }


    /**
     * Setter for Operating image
     * @param operatingImage ImageData
     */
    public void setOperatingImage(AppFile operatingImage) {
        this.operatingImage = operatingImage;
    }

    public AppFile getOperatingImage() {
        return operatingImage;
    }

    public void setMain(MainGUI main) {
        this.main = main;
    }
}