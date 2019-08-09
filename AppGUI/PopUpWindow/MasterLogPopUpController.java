package AppGUI.PopUpWindow;

import AppGUI.MainContainer;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MasterLogPopUpController {

    /**
     * Main table view of the pop up window, displays the entries in master log in separate rows
     */
    @FXML
    public TableView<Map.Entry<String, String>> mLogTable = new TableView<>();

    /**
     * First column in table view, displays the key of the masterLog: the timestamp of tag change or image change
     */
    @FXML
    public TableColumn<HashMap.Entry<String, String>, String> timeColumn;

    /**
     * Second column in table view, displays the value of the masterLog: description of the tag or image change
     */
    @FXML
    public TableColumn<HashMap.Entry<String, String>, String> logColumn;

    /**
     * Initializes a LinkedHashMap tht will become a copy of masterLog to be converted to an observable list.
     */
    private LinkedHashMap<String, String> data = new LinkedHashMap<>();

    /**
     * Displays the Table view according to master extracted from mainContainer in the format of
     * timestamp | description of modification
     */
    void showView() {
        data.putAll(MainContainer.getMasterLog().getLog());
        timeColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        logColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));
        ObservableList<Map.Entry<String, String>> items = FXCollections.observableArrayList(data.entrySet());
        mLogTable.setItems(items);
        mLogTable.getColumns().setAll(timeColumn, logColumn);
    }

}

