package AppGUI;

import AppGUI.CenterPanel.FolderPanelController;
import AppGUI.CenterPanel.MiddleWindowController;
import AppGUI.TreeView.OperatingMenuController;
import AppGUI.TreeView.TreeViewController;
import Observers.*;

class MainGUIActions {

    /**
     * Controllers for all our panes. These variables are set outside the constructor.
     */
    private OperatingMenuController opController;
    private TreeViewController treeController;
    private MiddleWindowController middleController;
    private FolderPanelController folderController;
    private TopPanel topPanel;

    /**
     * Observers for all our panes. These variables are set outside the constructor.
     */
    private CenterObserver centerObserver;
    private TreeViewObserver treeViewObserver;
    private FolderObserver folderObserver;
    private OpMenuObserver opObserver;
    private MainObserver mainObserver;


    MainGUIActions(OperatingMenuController opController, TreeViewController treeController,
                   MiddleWindowController middleController, FolderPanelController folderController,
                   TopPanel topPanel) {
        this.opController = opController;
        this.treeController = treeController;
        this.middleController = middleController;
        this.folderController = folderController;
        this.topPanel = topPanel;
    }

    /*
    * Initialize all the observers.
    */
    void setObservers() {
        this.centerObserver = new CenterObserver();
        this.treeViewObserver = new TreeViewObserver();
        this.folderObserver = new FolderObserver();
        this.opObserver = new OpMenuObserver();
        this.mainObserver = new MainObserver();
    }

    /*
    * Set the necessary targets for each Observer.
    */
    void setAllObserverTargets(MainGUI mainGUI) {
        centerObserver.setTarget(middleController);
        treeViewObserver.setTarget(treeController);
        folderObserver.setTarget(folderController);
        opObserver.setTarget(opController);
        mainObserver.setMain(mainGUI);
        topPanel.setMainObserver(mainObserver);
    }

    /*
    * Set the necessary observers for all the initialized controllers
    */
    void setAllControllerObservers() {
        this.setOpMenuControllerObservers();
        this.setTreeControllerObservers();
        this.setFolderControllerObservers();
        this.setMiddleWindowControllerObservers();
    }

    /*
    * Set the necessary observers for OperatingMenuController
    */
    private void setOpMenuControllerObservers() {
        opController.setMainObserver(mainObserver);
        opController.setTreeViewObserver(treeViewObserver);
        opController.setCenterObserver(centerObserver);
    }

    /*
    * Set the necessary observers for TreeViewController
    */
    private void setTreeControllerObservers() {
        treeController.setMainObserver(mainObserver);
        treeController.setOpMenuObserver(opObserver);
        treeController.setCenterObserver(centerObserver);
        treeController.setFolderObserver(folderObserver);
    }

    /*
    * Set the necessary observers for FolderPanelController
    */
    private void setFolderControllerObservers() {
        folderController.setMainObserver(mainObserver);
        folderController.setOpMenuObserver(opObserver);
        folderController.setCenterObserver(centerObserver);
    }

    /*
    * Set the necessary observers for MiddleWindowController
    */
    private void setMiddleWindowControllerObservers() {
        middleController.setMainObserver(mainObserver);
        middleController.setOpMenuObserver(opObserver);
        middleController.setCenterObserver(centerObserver);
    }
}
