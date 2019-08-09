package AppGUI.CenterPanel;

import AppComponents.ImageData;
import AppGUI.MainContainer;
import AppGUI.PopUpWindow.DialogBox;
import Observers.FolderObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Controller for CenterPanel.fxml, contains and implements the search bar, the imageView of the selected item in treeView,
 * as well as a tableView of the parent directory of the selected item.
 * Is initialized by MainGUI when the application first starts, and is observed by a CenterObserver.
 */
public class MiddleWindowController extends FolderPanelController {
    /**
     * Search button when searching for tags
     */
    @FXML
    public Button searchButton = new Button();

    /**
     * User manipulable text field when searching for tags
     */
    @FXML
    public TextField searchTextField = new TextField();

    /**
     * ImageView of the selected item
     */
    @FXML
    private ImageView imageView = new ImageView();

    /**
     * String absolute path of the selected item in treeView
     */
    private String selectedItemLocation;

    /**
     * Text of the absolute path of the selected item, not manipulable by the user
     */
    @FXML
    public Text locationText;

    private FolderObserver folderObserver;

    /**
     * Initialize the search field when search is clicked and clears the text after user hits enter.
     * Returns error message through a dialog box if the search failed.
     */
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

    /**
     * * Setter for this center panel.
     * Takes the location of the selected item, and initializes a ImageView and corresponding absolute path of locationText.
     *
     * @param location String absolute path of the folder desired to display.
     */
    @Override
    public void setPanel(String location) {
        selectedItemLocation = location;
        super.setPanel(location);
        File file = new File(location);
        Image image = new Image(file.toURI().toString());
        this.imageView.setImage(image);
        this.locationText.setText(location);

    }

    /**
     * Getter for the location of this selected item used in center panel.
     *
     * @return String absolute path of the file of the selected item
     */
    public String getSelectedItemLocation() {
        return selectedItemLocation;
    }

    /**
     * When the search button is clicked, search and display the results for the images with the corresponding tags.
     * Otherwise displays a dialog box with corresponding error message to the user.
     *
     * @throws Exception IOException
     */
    public void searchTagClicked() throws Exception {
        String inquired = searchTextField.getText();
        ArrayList<ImageData> inquiredResults = filterSearchedTags(inquired);
        if (!(inquiredResults.size() == 0)) {
            SearchResults searchResults = new SearchResults();
            searchResults.display(inquiredResults, getCenterObserver(), getMainObserver(), getOpMenuObserver());
        } else {
            DialogBox alertBox = new DialogBox("Warning", "Tag Not Found!");
            alertBox.display();
        }
    }

    /**
     * Returns the center panel back to the parent directory of the file
     */
    public void openParentFolder() {
        getMainObserver().setPanel("Tree");
        getMainObserver().setPanel("folder");
        folderObserver.update(selectedItemLocation);
    }

    /**
     * Helper method for the search bar, takes a string user input of one or multiple tags, splices it for individual tags
     * and searches and return a list of ImageData that is associated with all tag(s) inputted
     *
     * @param tags user inputted String tags to searc, may be single tag or multiple tags seperated by ", "
     * @return ArrayList<ImageData> ImageData associated with all tags searched
     */
    private ArrayList<ImageData> filterSearchedTags(String tags) {
        String[] tagList = tags.split(", ");
        ArrayList<String> tagArray = new ArrayList<>(Arrays.asList(tagList));
        return new ArrayList<>(MainContainer.getAppImageManager().getImageList().stream().filter(i -> i.containsTags(tagArray)).collect(Collectors.toList()));
    }

    /**
     * Setter for folderObserver
     *
     * @param folderObserver initialized by MainGUI and observes the folder panel that this MiddleWindowController is communicating with
     */
    public void setFolderObserver(FolderObserver folderObserver) {
        this.folderObserver = folderObserver;
    }
}
