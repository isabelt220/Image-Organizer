package AppGUI.CenterPanel;

import AppComponents.ImageData;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.io.File;
import java.util.*;

public class SearchResultsController {


//    @FXML
//    public TreeTableColumn imagePreview;
    private static ObservableList<ImageData> images;
    @FXML
    public TableView<TableListElement> resultTable;
//    @FXML
//    public TableColumn<ImageData,String> imagePath;
    @FXML
    public TableColumn<TableListElement, String> imageName;
    @FXML
    public TableColumn<TableListElement, ImageView> imagePreview;


    @FXML
    public void initialize() {
        imagePreview.setCellValueFactory(new PropertyValueFactory<>("picture"));
        imagePreview.setCellFactory(column -> {
            return new TableCell<TableListElement, ImageView>() {
                @Override
                protected void updateItem(ImageView image, boolean empty) {
                    super.updateItem(image, empty);
                    if (image == null || empty) {
                        setText(null);
                        setGraphic(null);
                        setStyle("");
                    } else {setGraphic(image);
                    }
                }
            };
        });
//        imagePath.setCellValueFactory(cellData -> cellData.getValue().getPathProperty());
//        imageName.setCellValueFactory(cellData -> cellData.getValue().getName());
        imageName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TableListElement, String>,
                ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TableListElement, String> t) {
                return new SimpleStringProperty(t.getValue().getName());
            }
        });setResults();
//        SortedList<ImageData> sortedData = new SortedList<>(images);
//        sortedData.comparatorProperty().bind(resultTable.comparatorProperty());
//        resultTable.setItems(sortedData);
//        ArrayList<TableListElement> imageTable =  new ArrayList<>();
//        for(ImageData pic: images){
//            Image image2 = new Image(pic.getImageLocation(),100,100,true,true);
//                ImageView tableImage = new ImageView();
//                tableImage.setImage(image2);
//                TableListElement newElement = new TableListElement(pic.getImageLocation());
//                newElement.setPicture(tableImage);
//                imageTable.add(newElement);
//            }
//        }
////        resultTable.get
////        tableView.setItems(FXCollections.observableList(imageTable));


//
}

    public static void setImages(ObservableList<ImageData> iImages){
        images = iImages;
    }

    public void setResults(){
        ArrayList<TableListElement> imageTable =  new ArrayList<>();
        for(ImageData pic: images){
            File f = new File(pic.getLocation());
            String url = f.toPath().toString();
            Image image2 = new Image(f.toURI().toString(),100,100,true,true);
            ImageView tableImage = new ImageView();
            tableImage.setImage(image2);

            String temp = f.getName();
            TableListElement newElement = new TableListElement(temp.substring(0,temp.lastIndexOf(".")));
            newElement.setPicture(tableImage);
            imageTable.add(newElement);
        }
        resultTable.getColumns().addAll(imagePreview,imageName);
        resultTable.setItems(FXCollections.observableList(imageTable));
    }
}



