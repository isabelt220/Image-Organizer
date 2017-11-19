package AppGUI.CenterPanel;

import AppComponents.ImageData;
import AppComponents.ImageManager;
import AppGUI.MainContainer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FolderPanelController implements Initializable {
    @FXML
    private TableView<ImageData> tableView = new TableView<>();
    @FXML
    private TableColumn<ImageData, String> preViewColumn = new TableColumn<>();
    @FXML
    private TableColumn<ImageData, String> coreNameColumn = new TableColumn<>();
    @FXML
    private TableColumn<ImageData, String> nameColumn = new TableColumn<>();

    public void initialize(URL location, ResourceBundle r) {
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {

                    if (tableView.getSelectionModel().getSelectedItem() != null) {
                        String location = tableView.getSelectionModel().getSelectedItem().getLocation();
                        MainContainer.getMiddleWindowController().setPanel(location);
                        MainContainer.getMain().showCenterView();
                        MainContainer.getMain().showOperatingMenu();
                        ImageData image = ImageManager.getImage(location);
                        MainContainer.getOperatingMenuController().setOperatingMenu(image);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        preViewColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        preViewColumn.setCellFactory(column -> {
            return new TableCell<ImageData, String>() {
                @Override
                protected void updateItem(String image, boolean empty) {
                    super.updateItem(image, empty);

                    if (image == null || empty) {
                        setText(null);
                        setGraphic(null);
                        setStyle("");
                    } else {
                        File f = new File(image);
                        Image i = new Image(f.toURI().toString(), 120, 120, true, true);
                        ImageView preView = new ImageView();
                        preView.setImage(i);
                        setGraphic(preView);
                    }
                }
            };
        });
        coreNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ImageData, String>,
                ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ImageData, String> t) {
                return new SimpleStringProperty(t.getValue().getCoreName());
            }
        });

        nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ImageData, String>,
                ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ImageData, String> t) {
                return new SimpleStringProperty(t.getValue().getName());
            }
        });
    }

    public TableView<ImageData> getTableView() {
        return tableView;
    }

    public void setPanel(String location) {
        tableView.getColumns().clear();
        tableView.getItems().clear();
        File file = new File(location);
        if (!file.isDirectory()) {
            file = file.getParentFile();
        }
        ArrayList<ImageData> imageTable = new ArrayList<>();
        for (File f : file.listFiles()) {
            if (!f.isDirectory()) {
                String mimeType = new MimetypesFileTypeMap().getContentType(f);
                String type = mimeType.split("/")[0];
                if (type.equals("image")) {
                    String url = f.toPath().toString();
                    Image image2 = new Image(f.toURI().toString(), 100, 100, true, true);
                    ImageView tableImage = new ImageView();
                    tableImage.setImage(image2);

                    ImageData imageData = ImageManager.getImage(url);
                    imageTable.add(imageData);
                }
            }
        }
        tableView.getColumns().addAll(preViewColumn, coreNameColumn, nameColumn);
        tableView.setItems(FXCollections.observableList(imageTable));

    }

}