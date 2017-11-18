package AppGUI;

import AppComponents.ImageManager;
import AppComponents.TagManager;
import AppGUI.CenterPanel.FolderPanelController;
import AppGUI.CenterPanel.MiddleWindowController;
//import AppGUI.TreeView.OperatingMenuController;
import AppGUI.TreeView.OperatingMenuController;
import AppGUI.TreeView.TreeViewController;
import javafx.scene.layout.Pane;

public class MainContainer {
    private static TagManager appTagManager = new TagManager();
    private static ImageManager appImageManager = new ImageManager();
    private static TreeViewController treeViewController;
    private static MiddleWindowController middleWindowController;

    public static void setFolderPanelController(FolderPanelController folderPanelController) {
        MainContainer.folderPanelController = folderPanelController;
    }

    public static void setFolderPanel(Pane folderPanel) {
        MainContainer.folderPanel = folderPanel;
    }

    public static FolderPanelController getFolderPanelController() {

        return folderPanelController;
    }

    public static Pane getFolderPanel() {
        return folderPanel;
    }

    private static FolderPanelController folderPanelController;
    private static Pane folderPanel;

    public static void setOperatingMenu(Pane operatingMenu) {
        MainContainer.operatingMenu = operatingMenu;
    }

    private static MainGUI main;
    private static Pane treeViewPanel;
    private static Pane operatingMenu;
    private static OperatingMenuController operatingMenuController;

    public static OperatingMenuController getOperatingMenuController() {
        return operatingMenuController;
    }

    public static void setOperatingMenuController(OperatingMenuController operatingMenuController) {
        MainContainer.operatingMenuController = operatingMenuController;
    }



    public static void setTreeViewPanel(Pane treeViewPanel) {
        MainContainer.treeViewPanel = treeViewPanel;
    }

    public static Pane getOperatingMenu() {
        return operatingMenu;
    }

    public static void setCenterPanel(Pane centerPanel) {
        MainContainer.centerPanel = centerPanel;
    }

    public static Pane getTreeViewPanel() {

        return treeViewPanel;
    }

    public static Pane getCenterPanel() {
        return centerPanel;
    }

    private static Pane centerPanel;

    public static void setAppTagManager(TagManager appTagManager) {
        MainContainer.appTagManager = appTagManager;
    }

    public static void setAppImageManager(ImageManager appImageManager) {
        MainContainer.appImageManager = appImageManager;
    }

    public static TagManager getAppTagManager() {

        return appTagManager;
    }

    public static ImageManager getAppImageManager() {
        return appImageManager;
    }

    public static void setTreeViewController(TreeViewController treeViewController) {
        MainContainer.treeViewController = treeViewController;
    }

    public static void setMiddleWindowController(MiddleWindowController middleWindowController) {
        MainContainer.middleWindowController = middleWindowController;
    }

    public static void setMain(MainGUI main) {
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
