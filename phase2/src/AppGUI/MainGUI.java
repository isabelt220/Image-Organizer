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

import java.io.IOException;

public class MainGUI extends Application {
    private Stage mainStage;
    private BorderPane mainLayout;
    private Pane treePanel;
    private Pane centerPanel;
    private Pane OpMenu;
    private Pane folderPanel;

    /**
     * Start the App
     *
     * @param primaryStage Stage
     * @throws Exception Is thrown when the FXMLLoader fails to read the source file
     */
    public void start(Stage primaryStage) throws Exception {
        MainContainer.setMain(this);
        this.mainStage = primaryStage;
        this.mainStage.setTitle("Photo Manager");
        showMainView();

        FXMLLoader OpLoader = new FXMLLoader();
        OpLoader.setLocation(MainGUI.class.getResource("TreeView/OperatingMenu.fxml"));
        Pane OMenuPanel = OpLoader.load();
        mainLayout.setLeft(OMenuPanel);
        OperatingMenuController OpController = OpLoader.getController();

        FXMLLoader treeLoader = new FXMLLoader();
        treeLoader.setLocation(MainGUI.class.getResource("TreeView/TreeView.fxml"));
        treePanel = treeLoader.load();
        mainLayout.setLeft(treePanel);
        TreeViewController treeController = treeLoader.getController();

        FXMLLoader centerLoader = new FXMLLoader();
        centerLoader.setLocation(MainGUI.class.getResource("CenterPanel/CenterPanel.fxml"));
        centerPanel= centerLoader .load();
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

        treeController.setMainObserver(mainObserver);
        treeController.setOpMenuObserver(opMenuObserver);
        treeController.setCenterObserver(centerObserver);
        treeController.setFolderObserver(folderObserver);

//        OpController.addObserver(mainObserver);
//        OpController.addObserver(treeViewObserver);
//        OpController.addObserver(centerObserver);



    }

    /**
     * Display the background scene
     *
     * @throws IOException Is thrown when the FXMLLoader fails to read the source file
     */

    private void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainGUI.class.getResource("MainView.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        mainStage.setScene(scene);
        mainStage.show();
    }

    /**
     * Display images under a folder
     *
     */

    public void showFolderPanel(){
        mainLayout.setCenter(folderPanel);
    }

    /**
     * Display the OperatingMenu
     *
     */
    public void showOperatingMenu()  {
       mainLayout.setLeft(OpMenu);
    }

    /**
     * Display the MiddleWindow
     *
     */
    public void showCenterView()  {
        mainLayout.setCenter(centerPanel);
    }

    /**
     * Display the left Panel
     */
    public void showTreeView(){
        mainLayout.setLeft(treePanel);
    }

    /**
     * Serialize imageList and ListOfTags
     */
    public void stop() {
        MainContainer.getAppDataSerializer().saveDataToFile("AppDataConfig.txt");
    }

    /**
     * Close the App and
     */
    void closeApplication() {
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


    public static void main(String[] args) {
        launch(args);
    }
}