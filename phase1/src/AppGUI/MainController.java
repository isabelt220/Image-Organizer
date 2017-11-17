package AppGUI;

import AppComponents.ImageManager;
import AppComponents.TagManager;
import AppGUI.CenterPanel.MiddleWindowController;
import AppGUI.TreeView.TreeViewController;

public class MainController {
    private static TagManager appTagManager = new TagManager();
    private static ImageManager appImageManager = new ImageManager();
    private static TreeViewController treeViewController;
    private static MiddleWindowController middleWindowController;
    private static MainGUI main;

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
