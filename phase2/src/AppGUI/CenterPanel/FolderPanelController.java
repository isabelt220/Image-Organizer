package AppGUI.CenterPanel;

import AppComponents.ImageData;
import AppGUI.MainContainer;
import Observers.CenterObserver;
import Observers.MainObserver;
import Observers.OpMenuObserver;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FolderPanelController implements Initializable {

    /* The below are components of a table that we created for
    * our application.*/
    @FXML
    private TableView<ImageData> tableView = new TableView<>();
    @FXML
    private TableColumn<ImageData, String> preViewColumn = new TableColumn<>();
    @FXML
    private TableColumn<ImageData, String> coreNameColumn = new TableColumn<>();
    @FXML
    private TableColumn<ImageData, String> nameColumn = new TableColumn<>();

    private CenterObserver centerObserver;

    private MainObserver mainObserver;

    private OpMenuObserver opMenuObserver;

    /* Initiate the table on mouse click
    * */
    public void initialize(URL location, ResourceBundle r) {
        tableView.setOnMouseClicked((MouseEvent event) -> {
            try {
                /*Check if the table has an item, if so get the properties
                of that item and get all the static variables of that item.
                */
                if (tableView.getSelectionModel().getSelectedItem() != null) {
                    String location1 = tableView.getSelectionModel().getSelectedItem().getLocation();
                    centerObserver.update(location1);
                    mainObserver.setPanel("center");
                    mainObserver.setPanel("OpMenu");
                    ImageData image = MainContainer.getAppImageManager().getImage(location1);
                    opMenuObserver.update(image);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        preViewColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        preViewColumn.setCellFactory(column -> new TableCell<ImageData, String>() {
            /* Update the screen display of the item in the table*/
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
        });
        coreNameColumn.setCellValueFactory(t -> new SimpleStringProperty(t.getValue().getCoreName()));

        nameColumn.setCellValueFactory(t -> new SimpleStringProperty(t.getValue().getName()));
    }

    /* Get the table ArrayList of ImageData*/
    TableView<ImageData> getTableView() {
        return tableView;
    }

    /* Setter for the panel*/
    public void setPanel(String location) {
        tableView.getColumns().clear();
        tableView.getItems().clear();
        File file = new File(location);
        if (!file.isDirectory()) {
            file = file.getParentFile();
        }
        ArrayList<ImageData> imageTable = new ArrayList<>();
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (!f.isDirectory()) {
                    String mimeType = new MimetypesFileTypeMap().getContentType(f);
                    String type = mimeType.split("/")[0];
                    if (type.equals("image")) {
                        String url = f.toPath().toString();
                        Image image2 = new Image(f.toURI().toString(), 100, 100, true, true);
                        ImageView tableImage = new ImageView();
                        tableImage.setImage(image2);
                        ImageData imageData = MainContainer.getAppImageManager().getImage(url);
                        if (imageData == null) {
                            imageData = new ImageData(url);
                        }
                        imageTable.add(imageData);
                    }
                }
            }
        }
        tableView.getColumns().addAll(preViewColumn, coreNameColumn, nameColumn);
        tableView.setItems(FXCollections.observableList(imageTable));
    }

    public void setCenterObserver(CenterObserver centerObserver) {
        this.centerObserver = centerObserver;
    }

    public void setMainObserver(MainObserver mainObserver) {
        this.mainObserver = mainObserver;
    }

    public void setOpMenuObserver(OpMenuObserver opMenuObserver) {
        this.opMenuObserver = opMenuObserver;
    }
}