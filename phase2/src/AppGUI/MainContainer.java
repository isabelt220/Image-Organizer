package AppGUI;

import AppComponents.AppDataSerializer;
import AppComponents.ImageManager;
import AppComponents.MasterLog;
import AppComponents.TagManager;
import AppGUI.CenterPanel.FolderPanelController;
import AppGUI.CenterPanel.MiddleWindowController;
import AppGUI.CenterPanel.SearchResults;
import AppGUI.CenterPanel.SearchResultsController;
import AppGUI.TreeView.OperatingMenuController;
import AppGUI.TreeView.TreeViewController;
import javafx.scene.layout.Pane;

/**
 * Master container for all back-end classes (or models, in the this application's MVC design)
 */
public class MainContainer {
    /** Initializes a new static TagManager to hold all Tags to be initialized (Static so that they can be accessed
     * through getters in other classes)
     */
    private static TagManager appTagManager = new TagManager();

    /** Initializes a new static ImageManager to hold all ImageData to be initialized (Static so that they can be accessed
     * through getters in other classes)
     */
    private static ImageManager appImageManager = new ImageManager();

    /** SearchResultsController initiated by the SearchResult when SearchResults is initialized*/
    private static SearchResultsController searchResultsController;

    /** SearchResults initialized when user clicks search button in MainView */
    private static SearchResults searchResults;

    /** Initializes a new static AppDataSerializer to hold all Tag andImageData initiated (Static so that they can be accessed
     * through getters in other classes)
     */
    private static AppDataSerializer appDataSerializer = new AppDataSerializer();

    /** FolderPanelController which is initialized after user double clicks on an image file*/
    private static FolderPanelController folderPanelController;

    /** Master Log of all the tag modifications of this application */
    private static MasterLog masterLog = new MasterLog();


    /** Static getter for MasterLog */
    public static MasterLog getMasterLog(){

        return masterLog;
    }

    /**
     * Static getter for SearchResultsController Object
     * @return SearchResultsController
     */
    public static SearchResultsController getSearchResultsController() {

        return searchResultsController;
    }


    /**
     * Static getter for FolderPanelController Object
     */
    public static FolderPanelController getFolderPanelController() {

        return folderPanelController;
    }

    /**
     * Static getter for this AppTagManager Object
     */
    public static TagManager getAppTagManager() {

        return appTagManager;
    }

    /**
     * Static getter for this AppImageManager Object
     */
    public static ImageManager getAppImageManager() {

        return appImageManager;
    }

    /**
     * Static getter for this AppDataSerializer Object
     */
    public static AppDataSerializer getAppDataSerializer() {

        return appDataSerializer;
    }
}
