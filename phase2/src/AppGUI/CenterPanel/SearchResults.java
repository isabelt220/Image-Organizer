package AppGUI.CenterPanel;

import AppComponents.ImageData;
import AppGUI.MainContainer;
import Observers.SearchResultObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SearchResults {
    // Main stage used to display the search results
//    private Stage mainStage;
    private SearchResultObserver searchResultObserver;

    public void setSearchResultObserver(SearchResultObserver searchResultObserver) {
        this.searchResultObserver = searchResultObserver;
    }

    /* Display the results of the images associated with the tag put
        * in the search bar.*/
    public void display(ArrayList<ImageData> imageList) throws Exception {
//        if (searchResultObserver == null) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SearchResults.class.getResource("SearchResults.fxml"));
            AnchorPane mainLayout = loader.load();
            searchResultObserver.setTarget((loader.getController()));
            searchResultObserver.displayResults(imageList);
//            MainContainer.setSearchResultsController((loader.getController()));
//            MainContainer.getSearchResultsController().setResults(imageList);
            stage.setScene(new Scene(mainLayout));
            stage.show();
//        } else {
            searchResultObserver.displayResults(imageList);
//            MainContainer.getSearchResultsController().setResults(imageList);
            stage.show();
        }
    }


//}
