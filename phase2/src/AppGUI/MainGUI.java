package AppGUI;

import AppGUI.CenterPanel.FolderPanelController;
import AppGUI.CenterPanel.MiddleWindowController;
import AppGUI.TreeView.OperatingMenuController;
import AppGUI.TreeView.TreeViewController;
import Observers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * The master class for front end GUI, the application will be initialized when running MainGUI, and all components necessary for image view and tag  manipulation
 * will either be initialized in the start method or be initialized in an object initialized in the start method.
 */
public class MainGUI extends Application {
    /**
     * Stage window for the main application
     */
    private Stage mainStage;

    /**
     * Master border pane that holds all other panes (such as tree, center, etc.)
     */
    private BorderPane mainLayout;

    /**
     * TreeView pane controlled by TreeViewController, observed by TreeViewObserver
     */
    private Pane treePanel;

    /**
     * Center pane controlled by MiddleWindowController, observed by CenterObserver
     */
    private Pane centerPanel;

    /**
     * Operating Menu pane controlled by OperatingMenuController, observed by OpMenuObserver
     */
    private Pane opMenu;

    /**
     * Folder pane controlled by FolderPanelController, observed by FolderObserver
     */
    private Pane folderPanel;


    /**
     * Start the App, initiates main, center, treeView, OpMenu, folder, middle window panels and corresponding controllers
     * and observers. Loads the panels into designated location on the mainLayout.
     *
     * @param primaryStage Stage
     * @throws Exception Is thrown when the FXMLLoader fails to read the source file
     */
    public void start(Stage primaryStage) throws Exception {
        this.mainStage = primaryStage;
        this.mainStage.setTitle("Photo Manager");

        /*
        Initialize all the loaders
        */
        FXMLLoader mainLoader = new FXMLLoader();
        FXMLLoader opLoader = new FXMLLoader();
        FXMLLoader treeLoader = new FXMLLoader();
        FXMLLoader centerLoader = new FXMLLoader();
        FXMLLoader folderLoader = new FXMLLoader();

        /*
        Show and set all the panels on the mainStage
        */
        this.showAndSetPanels(mainLoader, opLoader, treeLoader, centerLoader, folderLoader);

        /*
        Initialize all the controllers
        */
        OperatingMenuController opController = opLoader.getController();
        TreeViewController treeController = treeLoader.getController();
        MiddleWindowController middleController = centerLoader.getController();
        FolderPanelController folderController = folderLoader.getController();

        /*
        Initialize all the observers.
        */
        CenterObserver centerObserver = new CenterObserver();
        TreeViewObserver treeViewObserver = new TreeViewObserver();
        FolderObserver folderObserver = new FolderObserver();
        OpMenuObserver opMenuObserver = new OpMenuObserver();
        MainObserver mainObserver = new MainObserver();

        /*
        Set each controller as a target to its affiliated observer. Note that the main
        observer gets its target set to MainGUI.
        */
        this.setObserverTargets(centerObserver, treeViewObserver, folderObserver, opMenuObserver,
                mainObserver, middleController, treeController, folderController, opController);

        /*
        Set the needed observers for the OpController, TreeController, FolderController,
        and MiddleWindowController
         */
        this.setOpMenuControllerObservers(opController,
                mainObserver, treeViewObserver, centerObserver);

        this.setTreeControllerObservers(treeController,
                mainObserver, opMenuObserver, centerObserver, folderObserver);

        this.setFolderControllerObservers(folderController,
                mainObserver, opMenuObserver, centerObserver);

        this.setMiddleWindowControllerObservers(middleController,
                mainObserver, opMenuObserver, centerObserver);

        /*
        Set the topPanel and set the controllers to the topPanel
        */
        TopPanel topPanel = mainLoader.getController();
        topPanel.setTreeViewController(treeController);
        topPanel.setMainObserver(mainObserver);
    }

    private void showAndSetPanels(FXMLLoader mainLoader, FXMLLoader opLoader,
                                  FXMLLoader treeLoader, FXMLLoader centerLoader,
                                  FXMLLoader folderLoader) throws IOException {
        mainLoader.setLocation(MainGUI.class.getResource("MainView.fxml"));
        mainLayout = mainLoader.load();
        Scene scene = new Scene(mainLayout);
        mainStage.setScene(scene);
        mainStage.show();

        opLoader.setLocation(MainGUI.class.getResource("TreeView/OperatingMenu.fxml"));
        opMenu = opLoader.load();
        mainLayout.setLeft(opMenu);

        treeLoader.setLocation(MainGUI.class.getResource("TreeView/TreeView.fxml"));
        treePanel = treeLoader.load();
        mainLayout.setLeft(treePanel);

        centerLoader.setLocation(MainGUI.class.getResource("CenterPanel/CenterPanel.fxml"));
        centerPanel = centerLoader.load();
        mainLayout.setCenter(centerPanel);

        folderLoader.setLocation(MainGUI.class.getResource("CenterPanel/FolderPanel.fxml"));
        folderPanel = folderLoader.load();
        mainLayout.setCenter(folderPanel);
    }

    /*
    * Set the necessary targets for each Observer.
    */
    private void setObserverTargets(CenterObserver centerObserver,
                                    TreeViewObserver treeViewObserver,
                                    FolderObserver folderObserver,
                                    OpMenuObserver opObserver,
                                    MainObserver mainObserver,
                                    MiddleWindowController middleController,
                                    TreeViewController treeController,
                                    FolderPanelController folderController,
                                    OperatingMenuController opController) {

        centerObserver.setTarget(middleController);
        treeViewObserver.setTarget(treeController);
        folderObserver.setTarget(folderController);
        opObserver.setTarget(opController);
        mainObserver.setMain(this);
    }

    /*
    * Set the necessary observers for OperatingMenuController
    */
    private void setOpMenuControllerObservers(OperatingMenuController opMenuController,
                                              MainObserver mainObserver,
                                              TreeViewObserver treeObserver,
                                              CenterObserver centerObserver) {
        opMenuController.setMainObserver(mainObserver);
        opMenuController.setTreeViewObserver(treeObserver);
        opMenuController.setCenterObserver(centerObserver);
    }

    /*
    * Set the necessary observers for TreeViewController
    */
    private void setTreeControllerObservers(TreeViewController treeController,
                                            MainObserver mainObserver,
                                            OpMenuObserver opMenuObserver,
                                            CenterObserver centerObserver,
                                            FolderObserver folderObserver) {
        treeController.setMainObserver(mainObserver);
        treeController.setOpMenuObserver(opMenuObserver);
        treeController.setCenterObserver(centerObserver);
        treeController.setFolderObserver(folderObserver);
    }

    /*
    * Set the necessary observers for FolderPanelController
    */
    private void setFolderControllerObservers(FolderPanelController folderPanelController,
                                              MainObserver mainObserver,
                                              OpMenuObserver opMenuObserver,
                                              CenterObserver centerObserver) {
        folderPanelController.setMainObserver(mainObserver);
        folderPanelController.setOpMenuObserver(opMenuObserver);
        folderPanelController.setCenterObserver(centerObserver);
    }

    /*
    * Set the necessary observers for MiddleWindowController
    */
    private void setMiddleWindowControllerObservers(MiddleWindowController middleWindowController,
                                                    MainObserver mainObserver,
                                                    OpMenuObserver opMenuObserver,
                                                    CenterObserver centerObserver) {
        middleWindowController.setMainObserver(mainObserver);
        middleWindowController.setOpMenuObserver(opMenuObserver);
        middleWindowController.setCenterObserver(centerObserver);
    }

    /**
     * Display images under a folder
     */
    public void showFolderPanel() {
        mainLayout.setCenter(folderPanel);
    }

    /**
     * Checks if the current center panel is the MiddleWindowPanel
     *
     * @return true if the current center panel is the MiddleWindowPanel
     */
    public boolean isMiddleWindow() {


        return mainLayout.getCenter() == centerPanel;
    }

    /**
     * Display the OperatingMenu
     */
    public void showOperatingMenu() {
        mainLayout.setLeft(opMenu);
    }

    /**
     * Display the MiddleWindow
     */
    public void showCenterView() {
        mainLayout.setCenter(centerPanel);
    }

    /**
     * Display the left Panel
     */
    public void showTreeView() {
        mainLayout.setLeft(treePanel);
    }

    /**
     * Serialize imageList and ListOfTags
     */
    public void stop() throws IOException {
        MainContainer.getAppDataSerializer().saveDataToFile("AppDataConfig.txt");
    }

    /**
     * Close the App and saves
     */
    public void closeApplication() {
        mainStage.close();
    }

    /**
     * Return App's main stage
     *
     * @return Stage
     */
    public Stage getMainStage() {
        return mainStage;
    }

    /**
     * Launches application.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        launch(args);
    }
}