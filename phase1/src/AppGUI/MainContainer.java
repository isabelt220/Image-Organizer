package AppGUI;

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



    public static void setSearchResults(SearchResults searchResults) {
        MainContainer.searchResults = searchResults;
    }

    public static SearchResults getSearchResults() {

        return searchResults;
    }

    public static SearchResultsController getSearchResultsController() {
        return searchResultsController;
    }

    public static void setSearchResultsController(SearchResultsController searchResultsController) {
        MainContainer.searchResultsController = searchResultsController;
    }

    static void setFolderPanelController(FolderPanelController folderPanelController) {
        MainContainer.folderPanelController = folderPanelController;
    }

    static void setFolderPanel(Pane folderPanel) {
        MainContainer.folderPanel = folderPanel;
    }

    public static FolderPanelController getFolderPanelController() {

        return folderPanelController;
    }

    static Pane getFolderPanel() {
        return folderPanel;
    }

    private static FolderPanelController folderPanelController;
    private static Pane folderPanel;

    static void setOperatingMenu(Pane operatingMenu) {
        MainContainer.operatingMenu = operatingMenu;
    }

    public static OperatingMenuController getOperatingMenuController() {
        return operatingMenuController;
    }

    static void setOperatingMenuController(OperatingMenuController operatingMenuController) {
        MainContainer.operatingMenuController = operatingMenuController;
    }


    static void setTreeViewPanel(Pane treeViewPanel) {
        MainContainer.treeViewPanel = treeViewPanel;
    }

    static Pane getOperatingMenu() {
        return operatingMenu;
    }

    static void setCenterPanel(Pane centerPanel) {
        MainContainer.centerPanel = centerPanel;
    }

    static Pane getTreeViewPanel() {

        return treeViewPanel;
    }

    static Pane getCenterPanel() {
        return centerPanel;
    }

    private static Pane centerPanel;


    public static TagManager getAppTagManager() {

        return appTagManager;
    }

    public static ImageManager getAppImageManager() {
        return appImageManager;
    }

    static void setTreeViewController(TreeViewController treeViewController) {
        MainContainer.treeViewController = treeViewController;
    }

    static void setMiddleWindowController(MiddleWindowController middleWindowController) {
        MainContainer.middleWindowController = middleWindowController;
    }

    static void setMain(MainGUI main) {
        MainContainer.main = main;
    }

    public static TreeViewController getTreeViewController() {

        return treeViewController;
    }

    public static MiddleWindowController getMiddleWindowController() {
        return middleWindowController;
    }

    public static MainGUI getMain() {
        return main;
    }
}
