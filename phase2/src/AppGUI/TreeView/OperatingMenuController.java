package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.Tag;
import AppGUI.MainContainer;
import AppGUI.PopUpWindow.DialogBox;
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
    public void returnToOtherPane() throws IOException {
        MainContainer.getMain().showTreeView();
    }

    /**
     * Sets the operating menu to chosenImage, add and delete tag textfields are attched to chosenImage.
     * @param chosenImage ImageData
     */
    public void setOperatingMenu(ImageData chosenImage) {

        operatingImage = chosenImage;
    }

    /**
     * Gets the tag entered into addTag textField and calls classes in AppComponents to add tag to operating image
     * accordingly.
     */
    public void addTagButton() {
        ArrayList<String> tagEditorTagList = new ArrayList<>();
        tagEditorTagList.add(0, addTagTextField.getText());

        operatingImage = MainContainer.getAppImageManager().imAddTagWithImage(operatingImage, tagEditorTagList);
        MainContainer.getTreeViewController().reSetTree();
        MainContainer.getMiddleWindowController().setPanel(operatingImage.getLocation());
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
            MainContainer.getTreeViewController().reSetTree();
            MainContainer.getMiddleWindowController().setPanel(operatingImage.getLocation());
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
    void setOperatingImage(ImageData operatingImage) {

        this.operatingImage = operatingImage;
    }
}
