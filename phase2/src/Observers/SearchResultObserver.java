package Observers;

import AppComponents.ImageData;
import AppGUI.CenterPanel.SearchResultsController;

import javax.naming.directory.SearchResult;
import java.util.ArrayList;

/** SubClass of Observer, observes an SearchResults Controller */
public class SearchResultObserver extends Observer {

    /** The SearchResultsController that this SearchResults observes*/
    private SearchResultsController target;


    /**
     * Setter for the results of the user input search for the target SearchResultsController that this SearchResultsObserver observes.
     *
     * @param imageList results of the user tag search.
     */
    public void displayResults(ArrayList<ImageData> imageList){
        if (target != null){
            target.setResults(imageList);
        }
    }

    /**
     * Setter for the target SearchResultsController that this SearchResultsObserver observes.
     *
     * @param target SearchResultsController to observe.
     */
    public void setTarget(SearchResultsController target) {

        this.target = target;
    }


}
