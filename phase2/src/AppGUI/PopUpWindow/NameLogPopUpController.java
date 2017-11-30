package AppGUI.PopUpWindow;

import AppComponents.ImageData;
import AppComponents.Tag;
import AppGUI.MainContainer;
import Observers.CenterObserver;
import Observers.TreeViewObserver;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Initialized ny NameLogPopUp, takes the selected item in the treeView observed by this treeViewObserver
 * and loads the nameLog of the ImageData that is attached to the selected image file.
 * Provides method for reverting by to a previous set of tags of the image file.
 */
public class NameLogPopUpController {

    /** Main table view of the pop up window, displays the entries in name log in separate rows*/
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
     * Initializes the NameLogPopUp
     *
     * @throws NullPointerException Exception
     */
    public void initialize() throws NullPointerException{

    }

    /**
     * Displays the Table view according to nameLog extracted from selected file in the format of
     * timestamp | name of image
     */
     void showView(){
         File selectedFile;
        if (treeViewObserver.getSelectedFile() == null){
            selectedFile = new File(centerObserver.getTarget().getTableView().getSelectionModel().getSelectedItem().getLocation());
        }
        else{
            selectedFile =treeViewObserver.getSelectedFile();
        }
        curImage = MainContainer.getAppImageManager().getImage(selectedFile.toPath().toString());
        data.putAll(curImage.getNameLog());
        nameColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));
        timeStampColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        ObservableList<Map.Entry<String, String>> items = FXCollections.observableArrayList(data.entrySet());
        logTable.setItems(items);
        logTable.getColumns().setAll(timeStampColumn, nameColumn);
    }

    /**
     * Takes a selection on the tableView of the nameLog and processes the curImage to the name, and tags it had
     * at the chosen timestamp using the corresponding timestamp entry in the tagLog of the selected image.
     */
    public void revertName(){
        String chosenTime = logTable.getSelectionModel().getSelectedItem().getKey();
        ArrayList<String> revertList = curImage.getTagLog().get(chosenTime);
        ArrayList<Tag> oldTags = curImage.getImageTags();
        ImageData newNode = MainContainer.getAppImageManager().imSetImageTags(curImage, nameListToTagList(revertList));
        curImage.getImageLog().addEntry(curImage.getLocation(), curImage.getCoreName(), curImage.getImageTags(), oldTags);
        File f= new File(newNode.getLocation());
        treeViewObserver.setItem(f);
        centerObserver.update(newNode.getLocation());
        treeViewObserver.update();
        DialogBox confirmation = new DialogBox("Reverted!", "The images has been reverted! Close this window to check changes.");
        confirmation.display();
    }

    /**
     * For each String tagName in  ArrayList<String> tagNames, existence of a Tag with the tagName is checked in
     * the master tagList of the TagManager, if it exists, the Tag is added to the tagArrayList.
     * If it doesn't exist, a new Tag is initiated and add to the tagArrayList.
     * @param tagNames list of String tagNames to convert to list of tags
     * @return ArrayList<Tag> of tags
     */
    private ArrayList<Tag> nameListToTagList(ArrayList<String> tagNames){
        ArrayList<Tag> tagArrayList = new ArrayList<>();
        for(String tagName: tagNames){
            if(MainContainer.getAppTagManager().tagExists(tagName)){
                tagArrayList.add(MainContainer.getAppTagManager().getTag(tagName));
            }
            else{
                Tag newTag = new Tag(tagName);
                MainContainer.getAppTagManager().getListOfTags().add(newTag);
                tagArrayList.add(newTag);
            }
        }

        return tagArrayList;
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


