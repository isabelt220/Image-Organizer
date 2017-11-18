package AppGUI;

import AppComponents.ImageManager;
import AppComponents.TagManager;
import AppGUI.CenterPanel.MiddleWindowController;
import AppGUI.TreeView.TreeViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sun.applet.Main;

import java.io.IOException;

public class MainGUI extends Application{
    private Stage mainStage;
    private BorderPane mainLayout;


    public void start(Stage primaryStage) throws Exception {
        MainController.setMain(this);
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
        if(MainController.getFolderPanel()==null){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainGUI.class.getResource("CenterPanel/FolderPanel.fxml"));
        Pane folderPanel = loader.load();
        mainLayout.setCenter(folderPanel);
        MainController.setFolderPanelController(loader.getController());
        MainController.setFolderPanel(folderPanel);}
        else {
            System.out.println("aaa");
            mainLayout.setCenter(MainController.getFolderPanel());
        }
    }

    public void showCenterView() throws IOException {
        if (MainController.getCenterPanel() == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainGUI.class.getResource("CenterPanel/CenterPanel.fxml"));
            Pane centerPane = loader.load();
            mainLayout.setCenter(centerPane);
            MainController.setMiddleWindowController(loader.getController());
            MainController.setCenterPanel(centerPane);
        } else {
            mainLayout.setCenter(MainController.getCenterPanel());
        }
    }

    private void showTreeView() throws IOException{
        if (MainController.getTreeViewPanel() == null) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainGUI.class.getResource("TreeView/TreeView.fxml"));
        Pane treeView = loader.load();
        mainLayout.setLeft(treeView);
        MainController.setTreeViewController(loader.getController());
        MainController.setTreeViewPanel(treeView );
        } else {
            mainLayout.setCenter(MainController.getTreeViewPanel());
        }
    }

//    private void showSearchBar() throws IOException{
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(MainGUI.class.getResource("SearchBox/SearchBar.fxml"));
//        Pane searchBar = loader.load();
//        mainLayout.setRight(searchBar);
//    }

//    public void showOperatingMenu() throws IOException{
//        if (MainController.getOperatingMenuController() == null) {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainGUI.class.getResource("TreeView/OperatingMenu.fxml"));
//            Pane operatingMenu = loader.load();
//            mainLayout.setLeft(operatingMenu);
//            MainController.setOperatingMenuController(loader.getController());
//            MainController.setOperatingMenuPane(operatingMenu );}
//        else{
//            mainLayout.setLeft(MainController.getOperatingMenu());
//        }}

    public void stop() {
        MainController.getAppTagManager().saveTagsToFile("tagConfig.txt");
    }

    public void closeApplication() {
        mainStage.close();
    }

    public static void main(String[] args){
        launch(args);
    }
}