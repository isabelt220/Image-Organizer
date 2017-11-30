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
import sun.applet.Main;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Pane that takes up the center of the application when a directory file is selected from the treeView.
 * Consists of a table view that displays a preview of all images within the folder and their names and tags.
 * Is observed by a FolderObserver object initialized in MainGUI and associated with CenterObserver, MainObserver, and OpMenuObserver.
 */
public class FolderPanelController implements Initializable {

    /** Main table view of images to be displayed, any images within the selected directory will be listed.*/
    @FXML
    private TableView<ImageData> tableView = new TableView<>();

    /** First column of the tableView, image view of the image file of the ImageData will be displayed here*/
    @FXML
    private TableColumn<ImageData, String> preViewColumn = new TableColumn<>();

    /** Second column of the tableView, the core name of the ImageData of the image file will be displayed here.*/
    @FXML
    private TableColumn<ImageData, String> coreNameColumn = new TableColumn<>();

    /** Third column of the tableView, the full name of the ImageData, including all tags of the image file will be displayed here.*/
    @FXML
    private TableColumn<ImageData, String> nameColumn = new TableColumn<>();

    /** CenterObserver initialized by MainGUI to communicate between this folder panel and the center panel*/
    private CenterObserver centerObserver;

    /** MainObserver initialized by MainGUI to communicate between this folder panel and the Middle Window panel*/
    private MainObserver mainObserver;

    /** OprObserver initialized by MainGUI to communicate between this folder panel and the operating menu panel*/
    private OpMenuObserver opMenuObserver;

    /** Initiate the table on mouse click on a treeView directory, communicates with other panels and automatically updates itself after
     * selected item change, or tag change.
    */
    public void initialize(URL location, ResourceBundle r) {
        tableView.setOnMouseClicked((MouseEvent event) -> {
            try {
                /**Check if the table has an item, if so get the properties
                of that item and get all the static variables of that item.
                */
                if (tableView.getSelectionModel().getSelectedItem() != null) {
                    String location1 = tableView.getSelectionModel().getSelectedItem().getLocation();
                    centerObserver.update(location1);
                    mainObserver.setPanel("center");
                    mainObserver.setPanel("OpMenu");
                    ImageData image = MainContainer.getAppImageManager().getImage(location1);
                    if(image == null){
                        image = new ImageData(location1);
                    }
                    opMenuObserver.update(image);
                }

            }
            catch (Exception e) {
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
                }
                else {
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

    /**
     * Getter the table view of ImageData*
     * @return TableView<ImageData></> this table view
     */
    public TableView<ImageData> getTableView() {

        return tableView;
    }

    /**
     * Clears and initializes a new panel with table view of the file with the absolute path of String location
     * @param location String absolute path of the folder desired to display.
     */
    public void setPanel(String location) {
        ArrayList<ImageData> imageTable = new ArrayList<>();
        tableView.getColumns().clear();
        tableView.getItems().clear();
        File file = new File(location);
        if (!file.isDirectory()) {
            file = file.getParentFile();
        }
        read(file.toPath().toString(), imageTable);
        tableView.getColumns().addAll(preViewColumn, coreNameColumn, nameColumn);
        tableView.setItems(FXCollections.observableList(imageTable));

    }

    public void read(String location,  ArrayList<ImageData> imageTable){

        File file = new File(location);
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
                else{
                    read(f.toPath().toString(), imageTable);
                }
            }
        }


    }

    /**
     * Setter for center observer for data communication between panels.
     *
     * @param centerObserver initialized by MainGUI and observes the center panel that this folder panel is communicating with.
     */
    public void setCenterObserver(CenterObserver centerObserver) {

        this.centerObserver = centerObserver;
    }

    /**
     * Setter for middle winddle observer for data communication between panels.
     *
     * @param mainObserver initialized by MainGUI and observes the middle window panel that this folder panel is communicating with.
     */
    public void setMainObserver(MainObserver mainObserver) {

        this.mainObserver = mainObserver;
    }

    /**
     * Setter for operating menu observer for data communication between panels.
     *
     * @param opMenuObserver initialized by MainGUI and observes the operating menu panel that this folder panel is communicating with.
     */
    public void setOpMenuObserver(OpMenuObserver opMenuObserver) {

        this.opMenuObserver = opMenuObserver;
    }

    public CenterObserver getCenterObserver() {
        return centerObserver;
    }

    public MainObserver getMainObserver() {
        return mainObserver;
    }

    public OpMenuObserver getOpMenuObserver() {
        return opMenuObserver;
    }
}