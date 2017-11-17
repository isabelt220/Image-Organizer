package AppGUI.CenterPanel;

import AppGUI.TreeView.TreeViewController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MiddleWindowController implements Initializable{
    @FXML
    TableColumn<TableListElement, ImageView> tableColumn1 = new TableColumn<>("image");
    @FXML
    TableColumn<TableListElement, String > tableColumn2 = new TableColumn<>("tag");
    @FXML
    private   TableView<TableListElement> tableView = new TableView<>();
    @FXML
    private  ImageView imageView = new ImageView();

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
    }

    public void setCenterPanel(TreeItem<File> pic){
        Image image = new Image(pic.getValue().toURI().toString());
        this.imageView.setImage(image);
        TreeItem<File> dir = pic.getParent();
        ArrayList<TableListElement> imageTable =  new ArrayList<>();
        for(TreeItem<File> f: dir.getChildren()){
            if(!f.getValue().isDirectory()){
                TableListElement newElement = new TableListElement(f.getValue().getName());
                imageTable.add(newElement);
            }
        }
        tableView.getColumns().clear();
        tableView.getColumns().addAll(tableColumn1,tableColumn2);
        tableView.setItems(FXCollections.observableList(imageTable));

    }

    public void check(){
        System.out.println("!");
        imageView.setImage(new Image("file:/E:/Users/Mikari/Pictures/Saved%20Pictures/CmcKdPvVYAEEvoR.jpg"));
    }
}