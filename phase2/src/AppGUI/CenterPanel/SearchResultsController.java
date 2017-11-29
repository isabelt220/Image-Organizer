package AppGUI.CenterPanel;

import AppComponents.ImageData;
import javafx.collections.FXCollections;

import java.util.ArrayList;

/**
 * Initialized by SearchResults and is the controller for the stage for a single search action inputted by the user on MiddleWindowController.
 * Extends FolderPanelController as they are both table view with columns of image view, core name, and full name.
 */
public class SearchResultsController extends FolderPanelController {

    /**
     * Set the results of the table when they press the search button.
     * Set the results to the observableList, which has the associatedImagesList
     * for each tag
     *
     * @param result ArrayList<ImageData> that fits the criteria of the search.
     */
    public void setResults(ArrayList<ImageData> result) {
        getTableView().setItems(FXCollections.observableList(result));
    }
}



