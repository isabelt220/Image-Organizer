package AppGUI.CenterPanel;

import AppComponents.ImageData;
import AppGUI.MainContainer;
import Observers.SearchResultObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Initialized by MiddleWindow controller when the Search Tag button is clicked and the result of the search is not null.
 */
public class SearchResults {

    /** SearchObserver object initialized by the MiddleWindowController for this search, observers the SearchResultController
     * initialized in the display method.
     * */
    private SearchResultObserver searchResultObserver;

    /**
     * Setter for this searchResultObserver, called upon by MiddleWindowController.
     *
     * @param searchResultObserver Observer for the controller of this SearchResult object.
     */
     void setSearchResultObserver(SearchResultObserver searchResultObserver) {
        this.searchResultObserver = searchResultObserver;
    }

    /**
     * Initializes a new window and displays the results of the images associated with the tag put in the search bar.
     *
     * @param imageList the results of the search
     * @throws Exception mostly IOException
     */
    public void display(ArrayList<ImageData> imageList) throws Exception {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SearchResults.class.getResource("SearchResults.fxml"));
            AnchorPane mainLayout = loader.load();
            searchResultObserver.setTarget((loader.getController()));
            searchResultObserver.displayResults(imageList);
            stage.setScene(new Scene(mainLayout));
            stage.show();
            searchResultObserver.displayResults(imageList);
            stage.show();
    }
}

