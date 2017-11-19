package AppGUI.PopUpWindow;

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
import java.util.*;

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

    private LinkedHashMap<String,String> data = new LinkedHashMap<>();

    @FXML
    public void initialize() throws NullPointerException{
        File selectedFile = MainContainer.getTreeViewController().getTreeView().getSelectionModel().getSelectedItem().getValue();
        curImage = MainContainer.getAppImageManager().getImage(selectedFile.toPath().toString());;
        data.putAll(curImage.getNameLog());

        nameColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));
        timeStampColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        ObservableList<Map.Entry<String, String>> items = FXCollections.observableArrayList(data.entrySet());
        logTable.setItems(items);
        logTable.getColumns().setAll(nameColumn, timeStampColumn);

    }

    public void revertName(){
        String chosenTime = logTable.getSelectionModel().getSelectedItem().getValue();
        String stripList = logNameStrip(chosenTime);
        ArrayList<Tag> revertList = generateTagList(stripList);
        ImageData newNode = MainContainer.getAppImageManager().imSetImageTags(curImage, revertList);
        File f= new File(newNode.getLocation());
        MainContainer.getTreeViewController().getTreeView().getSelectionModel().getSelectedItem().setValue(f);
//        MainContainer.getMiddleWindowController().setPanel(MainContainer.getTreeViewController().getTreeView().getSelectionModel().getSelectedItem().getValue().toPath().toString());

    }

    public ArrayList<Tag> generateTagList (String chosen){
        ArrayList<Tag> imageTags = new ArrayList<>();
        if (chosen.contains("@")){
        int i = chosen.indexOf("@");
        String temp1 = chosen.substring(i+1);
        String[] parts = temp1.split(" @");
            for (String tag: parts) {
                imageTags.add(new Tag(tag));
            }}
        return imageTags;
    }

    public String logNameStrip(String chosenTime){
        if(chosenTime.contains("Initially named : ")){
            int x = chosenTime.indexOf(" : ");
            String temp = chosenTime.substring(x+2);
            return temp;

        }
        else{
            int x = chosenTime.indexOf("--> ");
            String temp = chosenTime.substring(x+1);
            return temp;

        }
    }


}


