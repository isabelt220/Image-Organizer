package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.Tag;
import AppGUI.MainContainer;
import AppGUI.PopUpWindow.DialogBox;
import Observers.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Used to initialize a panel that takes an image and can add and delete tags.
 */
public class OperatingMenuController implements Initializable {

    /** Initializes an empty list view, used to display tags of current image */
    @FXML
    public ListView<Tag> currentTagListView = new ListView<>();

    /** Context menu that pops up when user right clicks tag list of current operating image, includes delete tag option*/
    @FXML
    public ContextMenu listContextMenu;

    /** Initializes an empty list view, used to display list of all existing tags */
    @FXML
    public ListView<Tag> masterTagList = new ListView<>();

    /** Context menu that pops up when user right clicks master tag list, includes add tag option*/
    @FXML
    public ContextMenu masterTagListContextMenu;

    /** Initializes an empty text field for user input, used to add tags to image */
    @FXML
    private TextField addTagTextField = new TextField();

    /** Initializes an empty text field for user input, used to delete tags from image */
    @FXML
    private TextField deleteTagTextField = new TextField();

    /** Selected item from the tree view and also the image under tag modification of the user */
    private ImageData operatingImage;

    /** TreeViewObserver initialized by MainGUI to communicate between this folder panel and the Middle Window panel*/
    private TreeViewObserver treeViewObserver;

    /** MainObserver initialized by MainGUI to communicate between this folder panel and the Middle Window panel*/
    private MainObserver mainObserver;

    /** CenterObserver initialized by MainGUI to communicate between this folder panel and the center panel*/
    private CenterObserver centerObserver;

    /**
     * Initializes the two textFields for addTag and deleteTag.
     * @param location URL
     * @param r ResourceBundle
     */
    public void initialize(URL location, ResourceBundle r) {
        addTagTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                addTagButton();
            }
        });

        deleteTagTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                deleteTagButton();
            }
        });
    }

    /**
     * Loads and displays the tagList of the current operating image.
     */
    public void displayLists(){
        ObservableList<Tag> observableTagList = FXCollections.observableList(operatingImage.getImageTags());
        currentTagListView.setItems(observableTagList);
        currentTagListView.setCellFactory(new Callback<ListView<Tag>, ListCell<Tag>>() {
            @Override
            public ListCell<Tag> call(ListView<Tag> param) {
                return new ListCell<Tag>() {
                    @Override
                    protected void updateItem(Tag item, boolean empty) {
                        super.updateItem(item, empty);
                        setText((empty || item == null) ? "" : item.getTagName());
                        }
                    };
                }
            }
        );
        currentTagListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        masterTagList.setItems(MainContainer.getAppTagManager().getObservableTagList());
        masterTagList.setCellFactory(
                new Callback<ListView<Tag>, ListCell<Tag>>() {
                    @Override
                    public ListCell<Tag> call(ListView<Tag> param) {
                        return new ListCell<Tag>() {
                            @Override
                            protected void updateItem(Tag item, boolean empty) {
                                super.updateItem(item, empty);
                                setText((empty || item == null) ? "" : item.getTagName());
                                setStyle("-fx-control-inner-background: #f5f5f5");
                                if (item != null && item.getAssociatedImages().size() == 0) {
                                    setStyle("-fx-control-inner-background: wheat");
                                }
                            }
                        };
                    }
                });
        masterTagList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


    /**
     * Exits the current OperatingMenu and reverts back to the original treeView.
     * @throws IOException to Main
     */
    @FXML
    public void returnToOtherPane() throws IOException {
        mainObserver.setPanel("Tree");
    }

    /**
     * Handles a click on the current list view of tags
     */
    public void tagListClick(){
        currentTagListView.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
            if (t.getButton() == MouseButton.SECONDARY) {
                listContextMenu.show(currentTagListView, t.getScreenX(), t.getScreenY());
    }
            });}

    /**
     * Handles a click on the master tag list view of Tags
     */
    public void masterTagListClicked(){
        masterTagList.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
            if (t.getButton() == MouseButton.SECONDARY) {
                masterTagListContextMenu.show(currentTagListView, t.getScreenX(), t.getScreenY());
            }
        });}



    /**
     * Deletes selected tag in current list view from the operating image
     */
    public void deleteSelected(){
        System.out.println("SEL:  "+ currentTagListView.getSelectionModel().getSelectedItem());
        TagOperation deleteOperation = new TagOperation();
        Tag delete = currentTagListView.getSelectionModel().getSelectedItem();
        ArrayList<Tag> deleteList = new ArrayList<>();
        deleteList.add(delete);
        deleteOperation.deleteTagFromImage(deleteList, operatingImage);
        treeViewObserver.update();
        centerObserver.update(operatingImage.getLocation());
        currentTagListView.refresh();
    }

    /**
     * Adds selected tag in master tags list view from the operating image
     */
    public void addTagsFromList(){
        ObservableList<Tag> tagList = masterTagList.getSelectionModel().getSelectedItems();
        ArrayList<String> addList = new ArrayList<>();
        for (Tag t : tagList) {
            addList.add(t.getTagName());
        }
        if(!operatingImage.containsTags(addList)){
        MainContainer.getAppImageManager().imAddTagWithImage(operatingImage, addList);
        treeViewObserver.update();
        centerObserver.update(operatingImage.getLocation());
        currentTagListView.setItems(FXCollections.observableList(operatingImage.getImageTags()));
        masterTagList.refresh();}
        else{
            DialogBox repeatedTags = new DialogBox("Warning", "Don't add repeating tags!");
            repeatedTags.display();
        }
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
        currentTagListView.refresh();
        masterTagList.refresh();

    }

    /**
     * Gets the tag entered into deleteTag textField and calls classes in AppComponents to delete tag to operating image
     * accordingly.
     */
    public void deleteTagButton() {
        String targetTag = deleteTagTextField.getText();
        if (operatingImage.hasTag(targetTag)) {
            ArrayList<Tag> tagList = new ArrayList<>();
            tagList.add(MainContainer.getAppTagManager().getTag(targetTag));
            MainContainer.getAppImageManager().removeTagFromPic(tagList, operatingImage);
            treeViewObserver.update();
            centerObserver.update(operatingImage.getLocation());
            currentTagListView.refresh();
            masterTagList.refresh();
        }
        else {
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


    /**
     * Setter for this TreeViewObserver to communicate information
     * @param treeViewObserver to communicate with
     */
    public void setTreeViewObserver(TreeViewObserver treeViewObserver) {

        this.treeViewObserver = treeViewObserver;
    }

    /**
     * Setter for this TreeViewObserver to communicate information
     * @param mainObserver to communicate with
     */
    public void setMainObserver(MainObserver mainObserver) {

        this.mainObserver = mainObserver;
    }

    /**
     * Setter for this CenterObserver to communicate information
     * @param centerObserver to communicate with
     */
    public void setCenterObserver(CenterObserver centerObserver) {

        this.centerObserver = centerObserver;
    }
}
