package AppGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGUI extends Application{
    private Stage mainStage;
    private BorderPane mainLayout;
    public Pane operatingMenu;
    public Pane treeView;

    public Stage getMainStage() {
        return mainStage;
    }

    public void start(Stage primaryStage) throws Exception {
        MainContainer.setMain(this);
        this.mainStage = primaryStage;
        this.mainStage.setTitle("Photo Manager");
        showMainView();
        showTreeView();
        showCenterView();
    }

    private void showMainView() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainGUI.class.getResource("MainView.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        mainStage.setScene(scene);
        mainStage.show();
    }
    public void showFolderPanel()throws IOException{
        if(MainContainer.getFolderPanel()==null){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainGUI.class.getResource("CenterPanel/FolderPanel.fxml"));
        Pane folderPanel = loader.load();
        mainLayout.setCenter(folderPanel);
        MainContainer.setFolderPanelController(loader.getController());
        MainContainer.setFolderPanel(folderPanel);}
        else {
            mainLayout.setCenter(MainContainer.getFolderPanel());
        }
    }

    public void showOperatingMenu()throws IOException{
        if(MainContainer.getOperatingMenu()==null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainGUI.class.getResource("TreeView/OperatingMenu.fxml"));
            Pane OMenuPanel = loader.load();
            mainLayout.setLeft(OMenuPanel);
            MainContainer.setOperatingMenuController(loader.getController());
            MainContainer.setOperatingMenu(OMenuPanel);}
        else {
            mainLayout.setLeft(MainContainer.getOperatingMenu());
        }
    }

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

    public void showTreeView() throws IOException{
        if (MainContainer.getTreeViewPanel() == null) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainGUI.class.getResource("TreeView/TreeView.fxml"));
        Pane treeView = loader.load();
        mainLayout.setLeft(treeView);
        MainContainer.setTreeViewController(loader.getController());
        MainContainer.setTreeViewPanel(treeView );
        } else {
            mainLayout.setLeft(MainContainer.getTreeViewPanel());
        }
    }

    public void stop() {
        MainContainer.getAppDataSerializer().saveDataToFile("AppDataConfig.txt");
    }

    void closeApplication() {
        mainStage.close();
    }

    public static void main(String[] args){
        launch(args);
    }
}