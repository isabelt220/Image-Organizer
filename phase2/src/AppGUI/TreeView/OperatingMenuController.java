package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.Tag;
import AppGUI.MainContainer;
import AppGUI.PopUpWindow.DialogBox;
import Observers.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class OperatingMenuController implements Initializable {

    @FXML
    private TextField addTagTextField = new TextField();
    @FXML
    private TextField deleteTagTextField = new TextField();

    private ImageData operatingImage;
    private TreeViewObserver treeViewObserver;
    private MainObserver mainObserver;
    private CenterObserver centerObserver;

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

    public void setTreeViewObserver(TreeViewObserver treeViewObserver) {
        this.treeViewObserver = treeViewObserver;
    }

    public void setMainObserver(MainObserver mainObserver) {
        this.mainObserver = mainObserver;
    }

    public void setCenterObserver(CenterObserver centerObserver) {
        this.centerObserver = centerObserver;
    }

    /**
     * Exits the current OperatingMenu and reverts back to the original treeView.
     * @throws IOException
     */
    @FXML
    public void returnToOtherPane() throws IOException {
        mainObserver.setPanel("Tree");
    }

    /**
     * Gets the tag entered into addTag textField and calls classes in AppComponents to add tag to operating image
     * accordingly.
     */
    public void addTagButton() {
        ArrayList<String> tagEditorTagList = new ArrayList<>();
        tagEditorTagList.add(0, addTagTextField.getText());
        //Special care
        operatingImage = MainContainer.getAppImageManager().imAddTagWithImage(operatingImage, tagEditorTagList);
        treeViewObserver.update();
        centerObserver.update(operatingImage.getLocation());
        addTagTextField.setText("");

    }

    /**
     * Gets the tag entered into deleteTag textField and calls classes in AppComponents to delete tag to operating image
     * accordingly.
     */
    public void deleteTagButton() {
        String targetTag = deleteTagTextField.getText();
        if (operatingImage.hasTag(targetTag)) {
            Tag t = new Tag(targetTag);
            ArrayList<Tag> tagList = new ArrayList<>();
            tagList.add(t);
            MainContainer.getAppImageManager().removeTagFromPic(tagList, operatingImage);
            treeViewObserver.update();
            centerObserver.update(operatingImage.getLocation());
        } else {
            DialogBox warningBox = new DialogBox("Sorry", "This image does not have the tag you want to delete");
            warningBox.display();
        }
        deleteTagTextField.setText("");
    }


    /**
     * Setter for Operating image
     * @param operatingImage ImageData
     */
    public void setOperatingImage(ImageData operatingImage) {

        this.operatingImage = operatingImage;
    }
}
