package AppGUI;

import AppGUI.CenterPanel.FolderPanelController;
import AppGUI.CenterPanel.MiddleWindowController;
import AppGUI.TreeView.OperatingMenuController;
import AppGUI.TreeView.TreeViewController;
import Observers.CenterObserver;
import Observers.FolderObserver;
import Observers.TreeViewObserver;
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
    private Pane folderPane;
    private Pane middlePane;
    private Pane OMenu;
    private Pane treePane;

    /**
     * Start the App
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
        TopPanel topPanel = loader.getController();
        topPanel.setMain(this);
        Scene scene = new Scene(mainLayout);
        mainStage.setScene(scene);
        mainStage.show();


        FXMLLoader OLoader = new FXMLLoader();
        OLoader.setLocation(MainGUI.class.getResource("TreeView/OperatingMenu.fxml"));
        OMenu = OLoader.load();
        mainLayout.setLeft(OMenu);
        OperatingMenuController OController = OLoader.getController();
        OController.setOperatingImage(new AppFile());
        OController.setMain(this);


        FXMLLoader centerLoader = new FXMLLoader();
        centerLoader.setLocation(MainGUI.class.getResource("CenterPanel/CenterPanel.fxml"));
        middlePane = centerLoader.load();
        mainLayout.setCenter(middlePane);
        MiddleWindowController middleController = centerLoader.getController();
        middleController.setTargetFile(OController.getOperatingImage());
        middleController.setMain(this);

        FXMLLoader folderLoader = new FXMLLoader();
        folderLoader.setLocation(MainGUI.class.getResource("CenterPanel/FolderPanel.fxml"));
        folderPane =  folderLoader.load();
        mainLayout.setCenter(folderPane);
        FolderPanelController folderController = folderLoader.getController();
        folderController.setTargetFile(OController.getOperatingImage());
        folderController.setMain(this);

        FXMLLoader treeLoader = new FXMLLoader();
        treeLoader.setLocation(MainGUI.class.getResource("TreeView/TreeView.fxml"));
        treePane = treeLoader.load();
        mainLayout.setLeft(treePane);
        TreeViewController treeController = treeLoader.getController();
        treeController.setTargetFile(OController.getOperatingImage());

        CenterObserver centerObserver = new CenterObserver();
        centerObserver.setTarget(middleController);

        FolderObserver folderObserver = new FolderObserver();
        folderObserver.setTarget(folderController);

        TreeViewObserver treeObserver = new TreeViewObserver();
        treeObserver.setTarget(treeController);

        OController.getOperatingImage().addObserver(folderObserver);
        OController.getOperatingImage().addObserver(centerObserver);
        treeController.setMain(this);

        topPanel.setTreeViewController(treeController);

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
        mainLayout.setCenter(folderPane);
    }

    /**
     * Display the OperatingMenu
     *
     */
    public void showOperatingMenu() {
        mainLayout.setLeft(OMenu);
    }

    /**
     * Display the MiddleWindow
     *
     */
    public void showMiddlePanel(){
        mainLayout.setCenter(middlePane);
    }

    /**
     * Display the left Panel
     *
     */
    public void showTreeView() {
        mainLayout.setLeft(treePane);
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