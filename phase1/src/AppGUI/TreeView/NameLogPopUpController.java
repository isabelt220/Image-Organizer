package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.Tag;
import AppGUI.MainContainer;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NameLogPopUpController {
//    TableView<Map.Entry<String,String>> logTable = new TableView<>();
    @FXML
    public TableColumn<Map.Entry<String,String>, String> nameColumn;
    @FXML
    public TableColumn<HashMap.Entry<String, String>, String> timeStampColumn;
    @FXML
    public MenuItem revertButton;
    @FXML
    public TableView<Map.Entry<String,String>> logTable = new TableView<>();

    private ImageData curImage;

    private HashMap<String,String> data = new HashMap<>();

    @FXML
    public void initialize() {
        File selectedFile = MainContainer.getTreeViewController().getTreeView().getSelectionModel().getSelectedItem().getValue();
        ImageData currImage = MainContainer.getAppImageManager().getImage(selectedFile.toPath().toString());
        curImage = currImage;
        data.putAll(curImage.getNameLog());
        nameColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));
        timeStampColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        ObservableList<Map.Entry<String, String>> items = FXCollections.observableArrayList(data.entrySet());
        logTable.setItems(items);
        logTable.getColumns().setAll(nameColumn, timeStampColumn);

    }

    public void revertName(){
        String chosenTime = logTable.getSelectionModel().getSelectedItem().getValue();
        int x = chosenTime.indexOf("--> ");
        String temp = chosenTime.substring(x+1);
        System.out.println(temp+"temp");
        int i = temp.indexOf("@");
        String temp1 = temp.substring(i+1);
        System.out.println(temp1+"temp1");
        String[] parts = temp1.split(" @");
        System.out.println(parts.toString()+"parts");
        ArrayList<Tag> imageTags = new ArrayList<>();
        for (String tag: parts) {
            imageTags.add(new Tag(tag));
        }
        ImageData newNode = MainContainer.getAppImageManager().imSetImageTags(curImage, imageTags);
        File f= new File(newNode.getLocation());
        MainContainer.getTreeViewController().getTreeView().getSelectionModel().getSelectedItem().setValue(f);
        MainContainer.getMiddleWindowController().setPanel(MainContainer.getTreeViewController().getTreeView().getSelectionModel().getSelectedItem().getValue().toPath().toString());

    }


}


