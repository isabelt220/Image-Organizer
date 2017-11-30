package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.Tag;
import AppGUI.MainContainer;
import AppGUI.PopUpWindow.DialogBox;
import AppGUI.PopUpWindow.OpenPopUp;
import Observers.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Initialized by MainGUI and is observed by TreeViewObserver. Controls the treeView pane where the
 * user can navigate through imported directories and image files. Also consists of a listView of
 * all tags in TagManager. Contains all other observers in order to communicate information of the
 * selected files in treeView, and to be updated when there is a name/tag modification in the
 * corresponding panel of the observers.
 */
public class TreeViewController implements Initializable {

  /** Basic platform that the tree view and list view will be displayed in */
  @FXML public ContextMenu contextMenu;

  /** Initializes a new empty tree view that can be filled with TreeItems of imported files */
  @FXML private TreeView<File> treeView = new TreeView<>();

  /** Initializes a new empty tree view that can be filled with Items of added tags */
  @FXML ListView<Tag> listView = new ListView<>();

  /** Initializes a new HBox to aid switch between context menus */
  @FXML HBox hBox = new HBox();

  /** Initializes an text field that the user can use to add individual tags to the TagManager */
  @FXML TextField addTagField = new TextField();

  /**
   * CenterObserver initialized in MainGUI and used to communicate information between this
   * TreeViewController and the observer's corresponding panel
   */
  private CenterObserver centerObserver;

  /**
   * FolderObserver initialized in MainGUI and used to communicate information between this
   * TreeViewController and the observer's corresponding panel
   */
  private FolderObserver folderObserver;

  /**
   * OpMenuObserver initialized in MainGUI and used to communicate information between this
   * TreeViewController and the observer's corresponding panel
   */
  private OpMenuObserver opMenuObserver;

  /**
   * MainObserver initialized in MainGUI and used to communicate information between this
   * TreeViewController and the observer's corresponding panel
   */
  private MainObserver mainObserver;

  /**
   * Initializes the treeView Controller, setting up the listView and add/delete tag buttons that
   * modifies TagManager.
   */
  public void initialize(URL location, ResourceBundle r) {
    listView.setItems(MainContainer.getAppTagManager().getObservableTagList());
    listView.setCellFactory(
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
    listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    addTagField.setOnKeyPressed(
        keyEvent -> {
          String text = addTagField.getText();
          if (keyEvent.getCode() == KeyCode.ENTER
              && !MainContainer.getAppTagManager().tagExists(text)) {
            ArrayList<String> tag = new ArrayList<>();
            tag.add(text);
            MainContainer.getAppTagManager().tmAddTagWithoutImage(tag);
            addTagField.setText("");
          } else if (keyEvent.getCode() == KeyCode.ENTER
              && MainContainer.getAppTagManager().tagExists(text)) {
            DialogBox dialogBox = new DialogBox("Info", "Tag already exist");
            dialogBox.display();
          }
        });
  }

  /** Takes the selected file from treeView and moves it to a different directory. */
  public void moveFile() {
    FolderOperation folderEditor = new FolderOperation();
    TreeViewObserver t = new TreeViewObserver();
    t.setTarget(this);
    folderEditor.moveFile(t, mainObserver);
  }

  /**
   * Initializes a FolderOperation used to open and display a folder, or show operating menu for an
   * image file depending on the type of item selected.
   */
  public void openFolder() {
    FolderOperation folderEditor = new FolderOperation();
    String location = folderEditor.openFolder(mainObserver, treeView);
    if (location != null) {
      folderObserver.update(location);
    }
  }

  /** Initializes a TagOperation used to clear all tags from TagManager and associated ImageData. */
  public void CleanTagClick() {
    TagOperation tagEditor = new TagOperation();
    tagEditor.CleanTagClick();
    listView.refresh();
  }

  /**
   * Gets the tag selected when deleteTag button is clicked and removes the tag from TagManager and
   * all images it is associated with.
   */
  public void deleteTagClick() {
    TagOperation tagEditor = new TagOperation();
    tagEditor.deleteTagClick(listView);
    reSetTree();
    listView.refresh();
  }

  /** Makes a text field pop up where the user can enter the tag they wish to add to TagManager */
  public void addTagClick() {
    hBox.setVisible(!hBox.isVisible());
  }

  /**
   * Handles a click on the treeview, determines the type of folder clicked on, whether the click is
   * a normal left click, right click, or double click and offers the user different utilities in
   * the application.
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
          treeView.addEventHandler(
              MouseEvent.MOUSE_CLICKED,
              t -> {
                if (t.getButton() == MouseButton.SECONDARY) {
                  contextMenu.show(treeView, t.getScreenX(), t.getScreenY());
                } else if (t.getButton() == MouseButton.PRIMARY && t.getClickCount() == 2) {
                  if (treeView.getSelectionModel().getSelectedItem() != null
                      && !treeView.getSelectionModel().getSelectedItem().getValue().isDirectory()) {
                    mainObserver.setPanel("OpMenu");
                    ImageData image =
                        MainContainer.getAppImageManager()
                            .getImage(currentNode.getValue().toPath().toString());
                    if (image == null) {
                      image = new ImageData(currentNode.getValue().toPath().toString());
                    }
                    opMenuObserver.update(image);
                  }
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
   * Takes the current selected image from the treeView, and initializes a OpenPopUp that in turn
   * initializes a ImageEditor that modifies selected image
   *
   * @throws Exception IOException
   */
  public void openImageTagEditor() throws Exception {
    if (treeView.getRoot() != null) {
      OpenPopUp openPopUp = new OpenPopUp();
      openPopUp.setCenterObserver(centerObserver);
      openPopUp.setTreeViewObserver(this);
      openPopUp.openImageTagEditor();
    }
  }

  /**
   * Takes the current selected image in treeView and initializes a OpenPopUp that in turn
   * initializes a nameLog extracted from the ImageData that the selected image file is attached to.
   *
   * @throws Exception IOException
   */
  public void openNameLogPopUp() throws Exception {
    OpenPopUp openPopUp = new OpenPopUp();
    openPopUp.setCenterObserver(centerObserver);
    openPopUp.setTreeViewObserver(this);
    openPopUp.openNameLog();
  }

  /** Reloads the treeView to updates any name and tag changes. */
  public void reSetTree() {
    if (treeView.getRoot() != null) {
      TreeViewItem listHelper = new TreeViewItem();
      treeView.setRoot(listHelper.generateTreeItem(treeView.getRoot().getValue()));
    }
  }

  /**
   * Calls AppComponent classes accordingly to add the selected tag in listView to the selected
   * image in treeView
   */
  public void addTagToImage() {
    if (mainObserver.isMiddleWindow()) {
      TagOperation tagEditor = new TagOperation();
      TreeViewObserver treeViewObserver = new TreeViewObserver();
      treeViewObserver.setTarget(this);
      tagEditor.addTagToImage(listView, centerObserver, treeViewObserver);
      listView.refresh();
    } else {
      DialogBox warning = new DialogBox("Sorry!", "Can't add tag to a folder!");
      warning.display();
    }
  }

    public ListView<Tag> getListView() {
        return listView;
    }

    /**
   * Getter for this treeView.
   *
   * @return TreeView<File></File> tree view of its children files
   */
  public TreeView<File> getTreeView() {
    return treeView;
  }

  /**
   * Setter for this center observer, used to communicate information between selections and updates
   * in tree view to center panel observed by this center observer
   *
   * @param centerObserver observer of the current center panel of the application.
   */
  public void setCenterObserver(CenterObserver centerObserver) {

    this.centerObserver = centerObserver;
  }

  /**
   * Setter for this folder observer, used to communicate information between selections and updates
   * in tree view to folder panel observed by this folder observer
   *
   * @param folderObserver observer of the current folder panel of the application.
   */
  public void setFolderObserver(FolderObserver folderObserver) {

    this.folderObserver = folderObserver;
  }

  /**
   * Setter for this operating menu observer, used to communicate information between selections and
   * updates in tree view to operating menu panel observed by this operating menu observer
   *
   * @param opMenuObserver observer of the current operating menu panel of the application.
   */
  public void setOpMenuObserver(OpMenuObserver opMenuObserver) {

    this.opMenuObserver = opMenuObserver;
  }

  /**
   * Setter for this main observer, used to communicate information between selections and updates
   * in tree view to main panel observed by this main observer
   *
   * @param mainObserver observer of the current main panel of the application.
   */
  public void setMainObserver(MainObserver mainObserver) {

    this.mainObserver = mainObserver;
  }
}
