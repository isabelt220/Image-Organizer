package Observers;

import AppComponents.ImageData;
import AppGUI.CenterPanel.SearchResultsController;

import javax.naming.directory.SearchResult;
import java.util.ArrayList;

public class SearchResultObserver extends Observer {

    private SearchResultsController target;

    public void setTarget(SearchResultsController target) {
        this.target = target;
    }

    public void displayResults(ArrayList<ImageData> imageList){
        if (target != null){
            target.setResults(imageList);
        }
    }
}
