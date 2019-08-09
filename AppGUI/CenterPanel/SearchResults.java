package AppGUI.CenterPanel;

import AppComponents.ImageData;
import Observers.CenterObserver;
import Observers.MainObserver;
import Observers.OpMenuObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Initialized by MiddleWindow controller when the Search Tag button is clicked and the result of the search is not null.
 */
public class SearchResults {

    /**
     * Initializes a new window and displays the results of the images associated with the tag put in the search bar.
     *
     * @param imageList the results of the search
     * @throws Exception mostly IOException
     */
    public void display(ArrayList<ImageData> imageList, CenterObserver c, MainObserver m, OpMenuObserver o) throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SearchResults.class.getResource("SearchResults.fxml"));
        AnchorPane mainLayout = loader.load();
        SearchResultsController controller = loader.getController();
        controller.setResults(imageList);
        controller.setCenterObserver(c);
        controller.setMainObserver(m);
        controller.setOpMenuObserver(o);
        stage.setScene(new Scene(mainLayout));
        stage.show();
    }
}

