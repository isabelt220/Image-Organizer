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
    private static TreeViewController treeViewController;
    private static MiddleWindowController middleWindowController;
    private static SearchResultsController searchResultsController;
    private static SearchResults searchResults;
    private static MainGUI main;
    private static Pane treeViewPanel;
    private static Pane operatingMenu;
    private static OperatingMenuController operatingMenuController;
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
     * Static setter for FolderPanelController Object
     */
    static void setFolderPanel(Pane folderPanel) {
        MainContainer.folderPanel = folderPanel;
    }

    /**
     * Static getter for FolderPanelController Object
     */
    public static FolderPanelController getFolderPanelController() {

        return folderPanelController;
    }

    /**
     * Static getter for FolderPanel Object
     */
    static Pane getFolderPanel() {
        return folderPanel;
    }


    private static FolderPanelController folderPanelController;
    private static Pane folderPanel;

    /**
     * Static setter for OperatingMenu Object
     */
    static void setOperatingMenu(Pane operatingMenu) {
        MainContainer.operatingMenu = operatingMenu;
    }

    /**
     * Static getter for OperatingMenuController Object
     */
    public static OperatingMenuController getOperatingMenuController() {
        return operatingMenuController;
    }

    /**
     * Static setter for OperatingMenuController Object
     */
    static void setOperatingMenuController(OperatingMenuController operatingMenuController) {
        MainContainer.operatingMenuController = operatingMenuController;
    }


    /**
     * Static setter for treeViewPanel
     * @param treeViewPanel Pane
     */
    static void setTreeViewPanel(Pane treeViewPanel) {
        MainContainer.treeViewPanel = treeViewPanel;
    }

    /**
     * Static getter for OperatingMenu Object
     * @return Pane
     */
    static Pane getOperatingMenu() {
        return operatingMenu;
    }

    /**
     * Static setter for centerPanel Object
     */
    static void setCenterPanel(Pane centerPanel) {
        MainContainer.centerPanel = centerPanel;
    }

    /**
     * Static getter for TreeViewPanel Object
     * @return Pane
     */
    static Pane getTreeViewPanel() {

        return treeViewPanel;
    }

    /**
     * Static getter for CenterPanel Object
     */
    static Pane getCenterPanel() {
        return centerPanel;
    }

    private static Pane centerPanel;


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
     * Static setter for TreeViewController Object
     */
    static void setTreeViewController(TreeViewController treeViewController) {
        MainContainer.treeViewController = treeViewController;
    }

    /**
     * Static setter for MiddleWindowController Object
     */
    static void setMiddleWindowController(MiddleWindowController middleWindowController) {
        MainContainer.middleWindowController = middleWindowController;
    }

    /**
     * Static setter for MainGUI Object
     */
    static void setMain(MainGUI main) {
        MainContainer.main = main;
    }

    /**
     * Static getter for TreeViewController Object
     */
    public static TreeViewController getTreeViewController() {

        return treeViewController;
    }

    /**
     * Static getter for MiddleWindow Object
     */
    public static MiddleWindowController getMiddleWindowController() {
        return middleWindowController;
    }

    /**
     * Static getter for MainGUI Object
     */
    public static MainGUI getMain() {
        return main;
    }

    /**
     * Static getter for AppDataSerializer Object
     */
    static AppDataSerializer getAppDataSerializer() {
        return appDataSerializer;
    }
}
