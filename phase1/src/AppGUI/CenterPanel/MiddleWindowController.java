package AppGUI.CenterPanel;

import AppComponents.ImageData;
import AppGUI.PopUpWindow.DialogBox;
import AppGUI.TreeView.TreeViewController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import static AppComponents.TagManager.getImagesWithTag;
import static AppComponents.TagManager.tagExists;

public class MiddleWindowController extends FolderPanelController{
    @FXML
    public Button searchButton = new Button();
    @FXML
    public TextField searchTextField = new TextField();

    @FXML
    private  ImageView imageView = new ImageView();

    public ArrayList<ImageData> foundImages;
    public ObservableList<ImageData> obserImages;

    @Override
    public void setPanel(TreeItem<File> pic){
        super.setPanel(pic);
        Image image = new Image(pic.getValue().toURI().toString());
        this.imageView.setImage(image);
    }

    public void searchTagClicked() throws Exception{
        String inquired = searchTextField.getText();
        boolean existence = tagExists(inquired);
        if (existence){
            foundImages = getImagesWithTag(inquired);
            SearchResults searchResults = new SearchResults();
            searchResults.setImages(foundImages);
            obserImages = FXCollections.observableArrayList(foundImages);
            SearchResultsController.setImages(obserImages);
            searchResults.display();}
        else{DialogBox alertBox = new DialogBox("Warning","Tag Not Found!");
            alertBox.display();

        }

    }


}

