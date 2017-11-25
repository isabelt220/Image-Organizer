package AppGUI.CenterPanel;

import AppComponents.ImageData;
import javafx.collections.FXCollections;

import java.util.ArrayList;

public class SearchResultsController extends FolderPanelController {

    /* Set the results of the table when they press the search button.
    * Set the results to the observableList, which has the associatedImagesList
    * for each tag*/
    public void setResults(ArrayList<ImageData> result) {
        getTableView().setItems(FXCollections.observableList(result));
    }
}



