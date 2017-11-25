package AppGUI;

import AppComponents.AppDataSerializer;
import AppComponents.ImageManager;
import AppComponents.TagManager;
import AppGUI.CenterPanel.FolderPanelController;
import AppGUI.CenterPanel.MiddleWindowController;
import AppGUI.CenterPanel.SearchResults;
import AppGUI.CenterPanel.SearchResultsController;
import AppGUI.TreeView.OperatingMenuController;
import AppGUI.TreeView.TreeViewController;
import javafx.scene.layout.Pane;

public class MainContainer {
    private static TagManager appTagManager = new TagManager();
    private static ImageManager appImageManager = new ImageManager();
    private static SearchResultsController searchResultsController;
    private static SearchResults searchResults;
    private static AppDataSerializer appDataSerializer = new AppDataSerializer();


    /**
     * Static setter for searchResults Object
     */
    public static void setSearchResults(SearchResults searchResults) {
        MainContainer.searchResults = searchResults;
    }

    /**
     * Static getter for OperatingMenu Object
     * @return Searchresults
     */
    public static SearchResults getSearchResults() {

        return searchResults;
    }

    /**
     * Static getter for SearchResultsController Object
     * @return SearchResultsController
     */
    public static SearchResultsController getSearchResultsController() {
        return searchResultsController;
    }

    /**
     * Static setter for SearchResultsController Object
     */
    public static void setSearchResultsController(SearchResultsController searchResultsController) {
        MainContainer.searchResultsController = searchResultsController;
    }

    /**
     * Static setter for FolderPanelController Object
     */
    static void setFolderPanelController(FolderPanelController folderPanelController) {
        MainContainer.folderPanelController = folderPanelController;
    }


    /**
     * Static getter for FolderPanelController Object
     */
    public static FolderPanelController getFolderPanelController() {

        return folderPanelController;
    }


    private static FolderPanelController folderPanelController;


    /**
     * Static getter for AppTagManager Object
     */
    public static TagManager getAppTagManager() {

        return appTagManager;
    }

    /**
     * Static getter for AppImageManager Object
     */
    public static ImageManager getAppImageManager() {
        return appImageManager;
    }



    /**
     * Static getter for AppDataSerializer Object
     */
    public static AppDataSerializer getAppDataSerializer() {
        return appDataSerializer;
    }
}
