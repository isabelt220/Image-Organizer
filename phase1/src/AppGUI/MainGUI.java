package AppGUI;

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
        showTreeView();
        showCenterView();
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
     * @throws IOException Is thrown when the FXMLLoader fails to read the source file
     */

    public void showFolderPanel() throws IOException {
        if (MainContainer.getFolderPanel() == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainGUI.class.getResource("CenterPanel/FolderPanel.fxml"));
            Pane folderPanel = loader.load();
            mainLayout.setCenter(folderPanel);
            MainContainer.setFolderPanelController(loader.getController());
            MainContainer.setFolderPanel(folderPanel);
        } else {
            mainLayout.setCenter(MainContainer.getFolderPanel());
        }
    }

    /**
     * Display the OperatingMenu
     *
     * @throws IOException Is thrown when the FXMLLoader fails to read the source file
     */
    public void showOperatingMenu() throws IOException {
        if (MainContainer.getOperatingMenu() == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainGUI.class.getResource("TreeView/OperatingMenu.fxml"));
            Pane OMenuPanel = loader.load();
            mainLayout.setLeft(OMenuPanel);
            MainContainer.setOperatingMenuController(loader.getController());
            MainContainer.setOperatingMenu(OMenuPanel);
        } else {
            mainLayout.setLeft(MainContainer.getOperatingMenu());
        }
    }

    /**
     * Display the MiddleWindow
     *
     * @throws IOException Is thrown when the FXMLLoader fails to read the source file
     */
    public void showCenterView() throws IOException {
        if (MainContainer.getMiddleWindowController() == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainGUI.class.getResource("CenterPanel/CenterPanel.fxml"));
            Pane centerPane = loader.load();
            mainLayout.setCenter(centerPane);
            MainContainer.setMiddleWindowController(loader.getController());
            MainContainer.setCenterPanel(centerPane);
        } else {
            mainLayout.setCenter(MainContainer.getCenterPanel());
        }
    }

    /**
     * Display the left Panel
     *
     * @throws IOException Is thrown when the FXMLLoader fails to read the source file
     */
    public void showTreeView() throws IOException {
        if (MainContainer.getTreeViewPanel() == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainGUI.class.getResource("TreeView/TreeView.fxml"));
            Pane treeView = loader.load();
            mainLayout.setLeft(treeView);
            MainContainer.setTreeViewController(loader.getController());
            MainContainer.setTreeViewPanel(treeView);
        } else {
            mainLayout.setLeft(MainContainer.getTreeViewPanel());
        }
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