package AppGUI;

import AppComponents.ImageManager;
import AppComponents.TagManager;
import AppGUI.CenterPanel.MiddleWindowController;
//import AppGUI.TreeView.OperatingMenuController;
import AppGUI.TreeView.TreeViewController;
import javafx.scene.layout.Pane;

public class MainController {
    private static TagManager appTagManager = new TagManager();
    private static ImageManager appImageManager = new ImageManager();
    private static TreeViewController treeViewController;
    private static MiddleWindowController middleWindowController;
    private static MainGUI main;
    private static Pane treeViewPanel;
//    private static Pane operatingMenu;
//    private static OperatingMenuController operatingMenuController;
//
//    public static OperatingMenuController getOperatingMenuController() {
//        return operatingMenuController;
//    }
//
//    public static void setOperatingMenuController(OperatingMenuController operatingMenuController) {
//        MainController.operatingMenuController = operatingMenuController;
//    }
//
//    public static void setOperatingMenuPane(Pane operatingMenuPane){MainController.operatingMenu = operatingMenuPane;}

    public static void setTreeViewPanel(Pane treeViewPanel) {
        MainController.treeViewPanel = treeViewPanel;
    }

//    public static Pane getOperatingMenu() {
//        return operatingMenu;
//    }

    public static void setCenterPanel(Pane centerPanel) {
        MainController.centerPanel = centerPanel;
    }

    public static Pane getTreeViewPanel() {

        return treeViewPanel;
    }

    public static Pane getCenterPanel() {
        return centerPanel;
    }

    private static Pane centerPanel;

    public static void setAppTagManager(TagManager appTagManager) {
        MainController.appTagManager = appTagManager;
    }

    public static void setAppImageManager(ImageManager appImageManager) {
        MainController.appImageManager = appImageManager;
    }

    public static TagManager getAppTagManager() {

        return appTagManager;
    }

    public static ImageManager getAppImageManager() {
        return appImageManager;
    }

    public static void setTreeViewController(TreeViewController treeViewController) {
        MainController.treeViewController = treeViewController;
    }

    public static void setMiddleWindowController(MiddleWindowController middleWindowController) {
        MainController.middleWindowController = middleWindowController;
    }

    public static void setMain(MainGUI main) {
        MainController.main = main;
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
