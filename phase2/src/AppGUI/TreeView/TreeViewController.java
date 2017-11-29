package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.Tag;
import AppGUI.MainContainer;
import AppGUI.PopUpWindow.DialogBox;

import AppGUI.PopUpWindow.OpenPopUp;
import Observers.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public class TreeViewController implements Initializable {

    public ContextMenu contextMenu;
    @FXML
    private TreeView<File> treeView = new TreeView<>();
    @FXML
    ListView<Tag> listView = new ListView<>();
    @FXML
    HBox hBox = new HBox();
    @FXML
    TextField addTagField = new TextField();

    private CenterObserver centerObserver;
    private FolderObserver folderObserver;
    private OpMenuObserver opMenuObserver;
    private MainObserver mainObserver;


    /**
     * Initializes the treeView Controller, setting up the listview and add/delete tag buttons.
     *
     * @param location
     * @param r
     */
    public void initialize(URL location, ResourceBundle r) {
        listView.setItems(MainContainer.getAppTagManager().getObservableTagList());
        listView.setCellFactory(new Callback<ListView<Tag>, ListCell<Tag>>() {
            @Override
            public ListCell<Tag> call(ListView<Tag> param) {
                return new ListCell<Tag>() {
                    @Override
                    protected void updateItem(Tag item, boolean empty) {
                        super.updateItem(item, empty);
                        setText((empty || item == null) ? "" : item.getTagName());
                        if (item != null && item.getAssociatedImages().size() == 0) {
                            setStyle("-fx-background-color: wheat");
                        }
                    }
                };
            }
        });


        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        addTagField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String text = addTagField.getText();
                if (keyEvent.getCode() == KeyCode.ENTER && !MainContainer.getAppTagManager().tagExists(text)) {
                    ArrayList<String> tag = new ArrayList<>();
                    tag.add(text);
                    MainContainer.getAppTagManager().tmAddTagWithoutImage(tag);
                    System.out.println(MainContainer.getAppTagManager().getObservableTagList().size());
                    addTagField.setText("");
                } else if (keyEvent.getCode() == KeyCode.ENTER && MainContainer.getAppTagManager().tagExists(text)) {
                    DialogBox dialogBox = new DialogBox("Info", "Tag already exist");
                    dialogBox.display();
                }
            }
        });

    }


    /**
     * Takes the selected file from treeView and moves it to a different directory.
     */
    public void moveFile() {
        FolderOperation folderEditor = new FolderOperation();
        TreeViewObserver t = new TreeViewObserver();
        t.setTarget(this);
        folderEditor.moveFile(t,mainObserver);
    }


    /**
     * Used to open and display a folder, or show operating menu for an image file depending on the type of item selected.
     *
     */
    public void openFolder(){
        FolderOperation folderEditor = new FolderOperation();
        folderEditor.openFolder(mainObserver,treeView);
    }

    /**
     * Gets the tag selected when deleteTag button is clicked and removes the tag from TagManager and all images it is associated with.
     */
    public void deleteTagClick() {
        TagOperation tagEditor = new TagOperation();
        tagEditor.deleteTagClick(listView);
        reSetTree();
    }

    public void CleanTagClick(){
        TagOperation tagEditor = new TagOperation();
        tagEditor.CleanTagClick();
    }


    /**
     * Makes a text field pop up where the user can enter the tag they wish to add to TagManager
     */
    public void addTagClick() {
        hBox.setVisible(!hBox.isVisible());
    }


    /**
     * Handles a click on the treeview, determines the type of folder clicked on, whether the click is a
     * normal left click, right click, or double click and handles it in different ways.
     *
     * @throws IOException Exception
     */
    public void treeItemClick() throws IOException {
        TreeItem<File> currentNode = treeView.getSelectionModel().getSelectedItem();
        if (currentNode != null) {
            mainObserver.setPanel("center");
            centerObserver.update(currentNode.getValue().toPath().toString());
            if (!currentNode.getValue().isDirectory()) {
                if (currentNode.getValue() != null) {
                    treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
                        if (t.getButton() == MouseButton.SECONDARY) {
                            contextMenu.show(treeView, t.getScreenX(), t.getScreenY());
                        } else if (t.getButton() == MouseButton.PRIMARY && t.getClickCount() == 2) {

                            mainObserver.setPanel("OpMenu");
                            ImageData image = MainContainer.getAppImageManager().getImage(currentNode.getValue().toPath().toString());
                            if (image == null) {
                                image = new ImageData(currentNode.getValue().toPath().toString());
                            }
                            opMenuObserver.update(image);

                        }

                    });
                }
            } else {
                mainObserver.setPanel("folder");
                folderObserver.update(currentNode.getValue().toPath().toString());
            }
        }
    }


    /**
     * Takes the current selected image from the treeView, and initializes a ImageEditor that modifies selected image
     *
     * @throws Exception
     */
    public void openImageTagEditor() throws Exception {
        OpenPopUp openPopUp = new OpenPopUp(this);
        openPopUp.setCenterObserver(centerObserver);
        openPopUp.openImageTagEditor();
    }

    /**
     * Takes the current selected image in treeView and initializes a nameLog extracted from the ImageData that the image file is attached to.
     */
    public void openNameLogPopUp() throws Exception {
        OpenPopUp openPopUp = new OpenPopUp(this);
        openPopUp.openNameLog();
//        try {
//            NameLogPopUp nameLogPopUp = new NameLogPopUp();
//            nameLogPopUp.display();
//        } catch (Exception e) {
//            DialogBox alertBox = new DialogBox("Warning", "Please choose an Image");
//            alertBox.display();
//        }
    }

    /**
     * Reloads the treeView to updates any tag changes.
     */
    public void reSetTree() {
        if (treeView.getRoot() != null) {
            TreeViewItem listHelper = new TreeViewItem();
            treeView.setRoot(listHelper.generateTreeItem(treeView.getRoot().getValue()));
        }
    }

    /**
     * Calls AppComponent classes accordingly to add the selected tag in listView  to the selected image in treeView
     */
    public void addTagToImage() {
        TagOperation tagEditor = new TagOperation();
        TreeViewObserver treeViewObserver = new TreeViewObserver();
        treeViewObserver.setTarget(this);
        tagEditor.addTagToImage(listView, centerObserver, treeViewObserver);
    }


    /**
     * Getter for this treeView.
     *
     * @return TreeView<File></File>
     */
    public TreeView<File> getTreeView() {
        return treeView;
    }

    public void setCenterObserver(CenterObserver centerObserver) {
        this.centerObserver = centerObserver;
    }

    public void setFolderObserver(FolderObserver folderObserver) {
        this.folderObserver = folderObserver;
    }

    public void setOpMenuObserver(OpMenuObserver opMenuObserver) {
        this.opMenuObserver = opMenuObserver;
    }

    public void setMainObserver(MainObserver mainObserver) {
        this.mainObserver = mainObserver;
    }
}

