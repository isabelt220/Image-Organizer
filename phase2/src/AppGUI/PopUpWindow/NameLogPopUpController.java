package AppGUI.PopUpWindow;

import AppComponents.ImageData;
import AppComponents.Tag;
import AppGUI.MainContainer;
import Observers.CenterObserver;
import Observers.TreeViewObserver;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.io.File;
import java.util.*;

/**
 * Initialized ny NameLogPopUp, takes the selected item in the treeView observed by this treeViewObserver
 * and loads the nameLog of the ImageData that is attached to the selected image file.
 * Provides method for reverting by to a previous set of tags of the image file.
 */
public class NameLogPopUpController {

    /** Main table view of the pop up window, displays the entries in name log in separate columns*/
    @FXML
    public TableView<Map.Entry<String,String>> logTable = new TableView<>();

    /** First column in table view, displays the key of the nameLog: the timestamp of tag change of the ImageData*/
    @FXML
    public TableColumn<HashMap.Entry<String, String>, String> timeStampColumn;

    /** Second column in table view, displays the value of the nameLog: the name and tag change of the ImageData*/
    @FXML
    public TableColumn<Map.Entry<String,String>, String> nameColumn;

    /** Revert option when an entry is right clicked, may choose to revert back to tags selected time point. */
    @FXML
    public MenuItem revertButton;

    /** ImageData whose nameLog to display and revert, obtained by communicating with treeViewObserver. */
    private ImageData curImage;

    /** Initializes a LinkedHashMap tht will become a copy of nameLog to be converted to an observable list. */
    private LinkedHashMap<String,String> data = new LinkedHashMap<>();

    /** Observer of the treeView that this nameLogPopUpController obtains information from. */
    private TreeViewObserver treeViewObserver;

    /** Observer of the centerView that this nameLogPopUpController updates after name and tag change. */
    private CenterObserver centerObserver;


    /**
     * Initializes the NameLogPopUp according to nameLog extracted from curImage in the format of
     * timestamp | name of image
     *
     * @throws NullPointerException Exception
     */
    public void initialize() throws NullPointerException{
//        File selectedFile = MainContainer.getTreeViewController().getTreeView().getSelectionModel().getSelectedItem().getValue();
        File selectedFile =treeViewObserver.getSelectedFile();
        curImage = MainContainer.getAppImageManager().getImage(selectedFile.toPath().toString());
        data.putAll(curImage.getNameLog());
        nameColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));
        timeStampColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        ObservableList<Map.Entry<String, String>> items = FXCollections.observableArrayList(data.entrySet());
        logTable.setItems(items);
        logTable.getColumns().setAll(nameColumn, timeStampColumn);

    }

    /**
     * Takes a selection on the tableView of the nameLog and processes the curImage to the name, and tags it had
     * at the chosen timestamp using the corresponding timestamp entry in the tagLog of the selected image.
     */
    public void revertName(){
        String chosenTime = logTable.getSelectionModel().getSelectedItem().getValue();
        ArrayList<String> revertList = curImage.getTagLog().get(chosenTime);
        ImageData newNode = MainContainer.getAppImageManager().imAddTagWithImage(curImage, revertList);
        File f= new File(newNode.getLocation());
        treeViewObserver.setItem(f);
        centerObserver.update(treeViewObserver.getSelectedFile().toPath().toString());
        treeViewObserver.update();


    }

    /**
     * Setter for the tree view observer of the tree view panel to obtain selected image information with
     *
     * @param treeViewObserver Initialized by MainGUI and observer for treeView panel that this nameLog communicates information with.
     */
    public void setTreeViewObserver(TreeViewObserver treeViewObserver) {

        this.treeViewObserver = treeViewObserver;
    }

    /**
     * Setter for the center observer of the center panel to update when there has been a change in name and tags
     *
     * @param centerObserver Initialized by MainGUI and observer for center panel that this nameLog updates.
     */
    public void setCenterObserver(CenterObserver centerObserver) {

        this.centerObserver = centerObserver;
    }

//    /**
//     * Helper method for initialize, unused.
//     *
//     * @param t this tree view observer to obtain selected image
//     */
//    public void setSetting(TreeViewObserver t){
//        treeViewObserver = t;
//        File selectedFile = t.getSelectedFile();
//        curImage = MainContainer.getAppImageManager().getImage(selectedFile.toPath().toString());
//        if(curImage!=null){
//            data.putAll(curImage.getNameLog());
//        }
//    }


}


