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

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainGUI.class.getResource("MainView.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        TopPanel topPanel = loader.getController();
        mainStage.setScene(scene);
        mainStage.show();

        FXMLLoader OpLoader = new FXMLLoader();
        OpLoader.setLocation(MainGUI.class.getResource("TreeView/OperatingMenu.fxml"));
        opMenu = OpLoader.load();
        mainLayout.setLeft(opMenu);
        OperatingMenuController OpController = OpLoader.getController();


        FXMLLoader treeLoader = new FXMLLoader();
        treeLoader.setLocation(MainGUI.class.getResource("TreeView/TreeView.fxml"));
        treePanel = treeLoader.load();
        mainLayout.setLeft(treePanel);
        TreeViewController treeController = treeLoader.getController();

        FXMLLoader centerLoader = new FXMLLoader();
        centerLoader.setLocation(MainGUI.class.getResource("CenterPanel/CenterPanel.fxml"));
        centerPanel = centerLoader.load();
        mainLayout.setCenter(centerPanel);
        MiddleWindowController middleController = centerLoader.getController();

        FXMLLoader folderLoader = new FXMLLoader();
        folderLoader.setLocation(MainGUI.class.getResource("CenterPanel/FolderPanel.fxml"));
        folderPanel = folderLoader.load();
        mainLayout.setCenter(folderPanel);
        FolderPanelController folderController = folderLoader.getController();

        CenterObserver centerObserver = new CenterObserver();
        centerObserver.setTarget(middleController);

        TreeViewObserver treeViewObserver = new TreeViewObserver();
        treeViewObserver.setTarget(treeController);

        FolderObserver folderObserver = new FolderObserver();
        folderObserver.setTarget(folderController);

        MainObserver mainObserver = new MainObserver();
        mainObserver.setMain(this);

        OpMenuObserver opMenuObserver = new OpMenuObserver();
        opMenuObserver.setTarget(OpController);

        OpController.setMainObserver(mainObserver);
        OpController.setTreeViewObserver(treeViewObserver);
        OpController.setCenterObserver(centerObserver);

        treeController.setMainObserver(mainObserver);
        treeController.setOpMenuObserver(opMenuObserver);
        treeController.setCenterObserver(centerObserver);
        treeController.setFolderObserver(folderObserver);

        folderController.setCenterObserver(centerObserver);
        folderController.setMainObserver(mainObserver);
        folderController.setOpMenuObserver(opMenuObserver);

        middleController.setCenterObserver(centerObserver);
        middleController.setMainObserver(mainObserver);
        middleController.setOpMenuObserver(opMenuObserver);

        topPanel.setTreeViewController(treeController);
        topPanel.setMainObserver(mainObserver);


    }

    /**
     * Display images under a folder
     */

    public void showFolderPanel() {

        mainLayout.setCenter(folderPanel);
    }

    /**
     * Checks if the current center panel is the MiddleWindowPanel
     * @return true if the current center panel is the MiddleWindowPanel
     */
    public boolean isMiddleWindow(){


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