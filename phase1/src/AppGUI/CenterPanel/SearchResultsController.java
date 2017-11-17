package AppGUI.CenterPanel;

import AppComponents.ImageData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class SearchResultsController {


//    @FXML
//    public TreeTableColumn imagePreview;
    private static ObservableList<ImageData> images;
    @FXML
    public TableView<ImageData> resultTable;
    @FXML
    public TableColumn<ImageData,String> imagePath;
    @FXML
    public TableColumn<ImageData, String> imageName;


    @FXML
    public void initialize() {
        imagePath.setCellValueFactory(cellData -> cellData.getValue().getPathProperty());
        imageName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        SortedList<ImageData> sortedData = new SortedList<>(images);
        sortedData.comparatorProperty().bind(resultTable.comparatorProperty());
        resultTable.setItems(sortedData);
        

    }

    public static void setImages(ObservableList<ImageData> oImages){
        images = oImages;
    }


}
