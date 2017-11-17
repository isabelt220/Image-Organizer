package AppGUI;

import AppComponents.ImageManager;
import AppComponents.TagManager;
import AppGUI.CenterPanel.MiddleWindowController;
import AppGUI.TreeView.OperatingMenuController;
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

import java.io.IOException;

public class MainGUI extends Application{
    private Stage mainStage;
    private BorderPane mainLayout;
    public static TagManager appTagManager = new TagManager();
    public static ImageManager appImageManager = new ImageManager();
    public static TreeViewController treeViewController;
    public static MiddleWindowController middleWindowController;
    public static OperatingMenuController operatingMenuController;

    public void start(Stage primaryStage) throws Exception {
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

    private void showCenterView() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainGUI.class.getResource("CenterPanel/CenterPanel.fxml"));
        Pane centerPane = loader.load();
        mainLayout.setCenter(centerPane);
        middleWindowController = loader.getController();
    }
    private void showTreeView() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainGUI.class.getResource("TreeView/TreeView.fxml"));
        Pane treeView = loader.load();
        mainLayout.setLeft(treeView);
        treeViewController = loader.getController();
    }

//    private void showSearchBar() throws IOException{
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(MainGUI.class.getResource("SearchBox/SearchBar.fxml"));
//        Pane searchBar = loader.load();
//        mainLayout.setRight(searchBar);
//    }

    private void showOperatingMenu() throws IOException{
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(MainGUI.class.getResource("TreeView/OperatingMenu.fxml"));
    Pane operatingMenu = loader.load();
    mainLayout.setLeft(operatingMenu);
    operatingMenuController = loader.getController(); }



    public static void main(String[] args){
        launch(args);
    }
}