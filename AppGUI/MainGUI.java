package AppGUI;

import AppGUI.CenterPanel.FolderPanelController;
import AppGUI.CenterPanel.MiddleWindowController;
import AppGUI.TreeView.OperatingMenuController;
import AppGUI.TreeView.TreeViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
     * Folder pane controlled by FolderPanelController, observed by FolderObserver
     */
    private TopPanel topPanel;


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
        this.showAndSetPanels(mainLoader, opLoader, treeLoader,
                centerLoader, folderLoader);

        /*
        Initialize all the controllers
        */
        OperatingMenuController opController = opLoader.getController();
        TreeViewController treeController = treeLoader.getController();
        MiddleWindowController middleController = centerLoader.getController();
        FolderPanelController folderController = folderLoader.getController();
        topPanel.setTreeViewController(treeController);

        // Initialize MainGUIActions to use all actions for MainGUI
        MainGUIActions mainActions = new MainGUIActions(opController, treeController,
                middleController, folderController, topPanel);

        // Set observers to MainGUIActions
        mainActions.setObservers();

        /*
        Set the needed observers for the OpController, TreeController, FolderController,
        and MiddleWindowController
         */
        mainActions.setAllControllerObservers();

        /*
        Set each controller as a target to its affiliated observer. Note that the main
        observer gets its target set to MainGUI.
        */
        mainActions.setAllObserverTargets(this);
    }

    private void showAndSetPanels(FXMLLoader mainLoader, FXMLLoader opLoader, FXMLLoader treeLoader,
                                  FXMLLoader centerLoader, FXMLLoader folderLoader) throws IOException {

        mainLoader.setLocation(MainGUI.class.getResource("MainView.fxml"));
        mainLayout = mainLoader.load();

        Scene scene = new Scene(mainLayout);
        mainStage.setScene(scene);
        mainStage.show();

        topPanel = mainLoader.getController();

        opMenu = this.loadPanel("TreeView/OperatingMenu.fxml", opLoader);
        treePanel = this.loadPanel("TreeView/TreeView.fxml", treeLoader);
        centerPanel = this.loadPanel("CenterPanel/CenterPanel.fxml", centerLoader);
        folderPanel = this.loadPanel("CenterPanel/FolderPanel.fxml", folderLoader);

        mainLayout.setLeft(opMenu);
        mainLayout.setLeft(treePanel);
        mainLayout.setCenter(centerPanel);
        mainLayout.setCenter(folderPanel);
    }

    private Pane loadPanel(String fxml, FXMLLoader loader) throws IOException {
        loader.setLocation(MainGUI.class.getResource(fxml));
        return loader.load();
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
        MainContainer.getAppDataSerializer() .saveDataToFile("AppDataConfig.txt");
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