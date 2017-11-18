package AppGUI.CenterPanel;

import AppGUI.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class ParentTable  implements Initializable {
    @FXML
    private   TableView<TableListElement> tableView = new TableView<>();
    @FXML
    TableColumn<TableListElement, ImageView> tableColumn1 = new TableColumn<>();
    @FXML
    TableColumn<TableListElement, String > tableColumn2 = new TableColumn<>();
    @FXML
    TableColumn<TableListElement, String > tableColumn3 = new TableColumn<>();

    public void initialize(URL location, ResourceBundle r){
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("picture"));
        tableColumn1.setCellFactory(column -> {
            return new TableCell<TableListElement, ImageView>() {
                @Override
                protected void updateItem(ImageView image, boolean empty) {
                    super.updateItem(image, empty);

                    if (image == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {setGraphic(image);
                    }
                }
            };
        });
        tableColumn2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TableListElement, String>,
                ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TableListElement, String> t) {
                return new SimpleStringProperty(t.getValue().getName());
            }
        });

        tableColumn3.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TableListElement, String>,
                ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TableListElement, String> t) {
                return new SimpleStringProperty(t.getValue().getCoreName());
            }
        });
    }

    public void setPanel(TreeItem<File> pic){
        tableView.getColumns().clear();
        tableView.getItems().clear();
        TreeItem<File> dir;
        if(pic.getValue().isDirectory()){
            dir=pic;
        }
        else{dir = pic.getParent();}

        ArrayList<TableListElement> imageTable =  new ArrayList<>();
        for(TreeItem<File> f: dir.getChildren()){
            if(!f.getValue().isDirectory()){
                String url = f.getValue().toURI().toString();
                Image image2 = new Image(f.getValue().toURI().toString(),100,100,true,true);
                ImageView tableImage = new ImageView();
                tableImage.setImage(image2);

                TableListElement newElement = new TableListElement(f.getValue().getName());
                newElement.setPicture(tableImage);
                imageTable.add(newElement);
                if(MainController.getAppImageManager().ImageExist(url)){
                    newElement.setCoreName(MainController.getAppImageManager().getImage(url).getCoreName());
                }
            }
        }
        tableView.getColumns().addAll(tableColumn1,tableColumn3,tableColumn2);
        tableView.setItems(FXCollections.observableList(imageTable));
        System.out.println(tableColumn2.getCellData(0));

    }


}