package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.Tag;
import AppGUI.MainContainer;
import AppGUI.PopUpWindow.DialogBox;

import AppGUI.PopUpWindow.NameLogPopUp;
import Observers.CenterObserver;
import Observers.MainObserver;
import Observers.Observer;
import Observers.OpMenuObserver;
import javafx.collections.ObservableList;
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
import java.util.List;
import java.util.Observable;
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

    private ArrayList<Observer> observerList = new ArrayList<>();

    static File selectedImage;


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
                    addTagField.setText("");
                } else if (keyEvent.getCode() == KeyCode.ENTER && MainContainer.getAppTagManager().tagExists(text)) {
                    DialogBox dialogBox = new DialogBox("Info", "Tag already exist");
                    dialogBox.display();
                }
            }
        });

    }

    /**
     * Used to open and display a folder, or show operating menu for an image file depending on the type of item selected.
     *
     * @throws IOException Exception
     */
    public void openFolder() throws IOException {
        DirectoryChooser dc = new DirectoryChooser();
        File choice = dc.showDialog(MainContainer.getMain().getMainStage().getOwner());
        if (choice != null) {
            TreeViewItem listHelper = new TreeViewItem();
            treeView.setRoot(listHelper.generateTreeItem(choice));
            treeView.setCellFactory(new Callback<TreeView<File>, TreeCell<File>>() {

                public TreeCell<File> call(TreeView<File> file) {
                    return new TreeCell<File>() {

                        @Override
                        protected void updateItem(File item, boolean empty) {
                            super.updateItem(item, empty);
                            setText((empty || item == null) ? "" : item.getName());
                        }

                    };
                }
            });
            MainContainer.getMain().showFolderPanel();
        }
    }

    /**
     * Gets the tag selected when deleteTag button is clicked and removes the tag from TagManager and all images it is associated with.
     */
    public void deleteTagClick() {
        ObservableList<Tag> selectedItems = listView.getSelectionModel().getSelectedItems();
        ArrayList<Tag> selectedCopies = new ArrayList<Tag>(selectedItems.subList(0, selectedItems.size()));
        Tag temp = null;

        for (Tag t : selectedCopies) {
            MainContainer.getAppImageManager().removeTagFromAppAndImages(t.getTagName());
            reSetTree();
            MainContainer.getMiddleWindowController().refreshTable();
        }


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
            selectedImage = currentNode.getValue();
            MainObserver o = (MainObserver)observerList.get(0);
            o.setPanel("center");
            CenterObserver co = (CenterObserver)observerList.get(2);
            co.update(currentNode.getValue().toPath().toString());
            if (!currentNode.getValue().isDirectory()) {
                if (currentNode.getValue() != null) {
                    treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
                        if (t.getButton() == MouseButton.SECONDARY) {
                            contextMenu.show(treeView, t.getScreenX(), t.getScreenY());
                        } else if (t.getButton() == MouseButton.PRIMARY && t.getClickCount() == 2) {

                                o.setPanel("OpMenu");
                                ImageData image = MainContainer.getAppImageManager().getImage(currentNode.getValue().toPath().toString());
                                OpMenuObserver op = ( OpMenuObserver)observerList.get(1);
                                op.update(image);

                        }

                    });
                }
            } else {
                MainContainer.getMiddleWindowController().setSelectedItemLocation(null);
                MainContainer.getMain().showFolderPanel();
                MainContainer.getFolderPanelController().setPanel(currentNode.getValue().toPath().toString());
            }
        }
    }


    /**
     * Takes the current selected image from the treeView, and initializes a ImageEditor that modifies selected image
     *
     * @throws Exception
     */
    public void openImageTagEditor() throws Exception {
        File currentFile = treeView.getSelectionModel().getSelectedItem().getValue();
        if (currentFile != null && currentFile.isDirectory()) {
            DialogBox alertBox = new DialogBox("Info", "Cannot rename a folder");
            alertBox.display();
        } else if (currentFile != null) {
            ImageTagEditor imageTagEditor = new ImageTagEditor();
            imageTagEditor.display();
        }
    }

    /**
     * Takes the selected file from treeView and moves it to a different directory.
     */
    public void moveFile() {
        File selectedFile = treeView.getSelectionModel().getSelectedItem().getValue();
        DirectoryChooser dc = new DirectoryChooser();
        File choice = dc.showDialog(MainContainer.getMain().getMainStage().getOwner());
        if (choice != null) {
            try {
                String name = selectedFile.getName();
                Path targetPath = Paths.get(choice.toPath().toString() + File.separator + name);
                Files.move(selectedFile.toPath(), targetPath, REPLACE_EXISTING);
                if (!selectedFile.getParentFile().toPath().toString().equals(choice.toPath().toString())) {
                    reSetTree();
                }
            } catch (IOException e) {
                DialogBox warning = new DialogBox("Warning", "Remove Failed");
                warning.display();
            }
        }

    }

    /**
     * Takes the current selected image in treeView and initializes a nameLog extracted from the ImageData that the image file is attached to.
     *
     * @throws Exception
     */
    public void openNameLogPopUp() throws Exception {
        try {
            selectedImage = treeView.getSelectionModel().getSelectedItem().getValue();
            NameLogPopUp nameLogPopUp = new NameLogPopUp();
            nameLogPopUp.display();
        } catch (Exception e) {
            DialogBox alertBox = new DialogBox("Warning", "Please choose an Image");
            alertBox.display();
        }
    }

    /**
     * Reloads the treeView to updates any tag changes.
     */
    void reSetTree() {
        if (MainContainer.getTreeViewController().getTreeView().getRoot() != null) {
            TreeViewItem listHelper = new TreeViewItem();
            treeView.setRoot(listHelper.generateTreeItem(treeView.getRoot().getValue()));
        }
    }

    /**
     * Calls AppComponent classes accordingly to add the selected tag in listView  to the selected image in treeView
     */
    public void addTagToImage() {
        ObservableList<Tag> tagList = listView.getSelectionModel().getSelectedItems();
        System.out.println(tagList);
        String location = MainContainer.getMiddleWindowController().getSelectedItemLocation();

        if (tagList != null && MainContainer.getMiddleWindowController().getSelectedItemLocation() != null) {
            ImageData currentImage = MainContainer.getAppImageManager().getImage(location);
            ArrayList<String> addList = new ArrayList<>();
            for (Tag t : tagList) {
                addList.add(t.getTagName());
            }
            MainContainer.getAppImageManager().imAddTagWithImage(currentImage, addList);
            MainContainer.getMiddleWindowController().setPanel(currentImage.getLocation());
            reSetTree();
        }
    }


    /**
     * Getter for this treeView.
     *
     * @return TreeView<File></File>
     */
    public TreeView<File> getTreeView() {
        return treeView;
    }

    public void addObserver(Observer o){
        observerList.add(o);
    }
}

