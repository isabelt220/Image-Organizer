package AppGUI.CenterPanel;

import AppComponents.ImageData;
import AppGUI.MainContainer;
import AppGUI.PopUpWindow.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MiddleWindowController extends FolderPanelController {
    /* Search button and text field when searching for tags*/
    @FXML
    public Button searchButton = new Button();
    @FXML
    public TextField searchTextField = new TextField();

    /* View of the image and it's current location*/
    @FXML
    private ImageView imageView = new ImageView();

    private String selectedItemLocation;

    /* Initialize the search field when search is clicked. Return
    * a error message through a dialog box if the search failed.*/
    @Override
    public void initialize(URL location, ResourceBundle r) {
        super.initialize(location, r);
        searchTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                try {
                    searchTagClicked();
                    searchTextField.setText("");
                } catch (Exception e) {
                    DialogBox dialogBox = new DialogBox("Sorry", "Search Failed");
                    dialogBox.display();
                }
            }
        });
    }


    /* Setter for panel*/
    @Override
    public void setPanel(String location) {
        selectedItemLocation = location;
        super.setPanel(location);
        File file = new File(location);
        Image image = new Image(file.toURI().toString());
        this.imageView.setImage(image);

    }

    /* Setter for item's location*/
    public void setSelectedItemLocation(String selectedItemLocation) {
        this.selectedItemLocation = selectedItemLocation;
    }

    /* Getter for item's location*/
    public String getSelectedItemLocation() {
        return selectedItemLocation;
    }

    /* When the search button is clicked, search and display the results for
    * the images with the corresponding tags. Otherwise give a dialog box with
    * a error message to the user.*/
    public void searchTagClicked() throws Exception {
        String inquired = searchTextField.getText();
        boolean existence = MainContainer.getAppTagManager().tagExists(inquired);
        if (existence) {
            ArrayList<ImageData> foundImages = MainContainer.getAppTagManager().getImagesWithTag(inquired);
            if (MainContainer.getSearchResults() == null) {
                SearchResults searchResults = new SearchResults();
                searchResults.display(foundImages);
                MainContainer.setSearchResults(searchResults);
            } else {
                MainContainer.getSearchResults().display(foundImages);
            }
        } else {
            DialogBox alertBox = new DialogBox("Warning", "Tag Not Found!");
            alertBox.display();

        }

    }

}
