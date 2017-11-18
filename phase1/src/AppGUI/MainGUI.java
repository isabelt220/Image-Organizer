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

    public void showCenterView() throws IOException {
        if (MainContainer.getCenterPanel() == null) {
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

    private void showTreeView() throws IOException{
        if (MainContainer.getTreeViewPanel() == null) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainGUI.class.getResource("TreeView/TreeView.fxml"));
        Pane treeView = loader.load();
        mainLayout.setLeft(treeView);
        MainContainer.setTreeViewController(loader.getController());
        MainContainer.setTreeViewPanel(treeView );
        } else {
            mainLayout.setCenter(MainContainer.getTreeViewPanel());
        }
    }

//    private void showSearchBar() throws IOException{
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(MainGUI.class.getResource("SearchBox/SearchBar.fxml"));
//        Pane searchBar = loader.load();
//        mainLayout.setRight(searchBar);
//    }

//    public void showOperatingMenu() throws IOException{
//        if (MainContainer.getOperatingMenuController() == null) {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainGUI.class.getResource("TreeView/OperatingMenu.fxml"));
//            Pane operatingMenu = loader.load();
//            mainLayout.setLeft(operatingMenu);
//            MainContainer.setOperatingMenuController(loader.getController());
//            MainContainer.setOperatingMenuPane(operatingMenu );}
//        else{
//            mainLayout.setLeft(MainContainer.getOperatingMenu());
//        }}



    public void stop() {
        MainContainer.getAppTagManager().saveTagsToFile("tagConfig.txt");
        MainContainer.getAppImageManager().saveImagesToFile("imageConfig.txt");
    }

    public void closeApplication() {
        mainStage.close();
    }

    public static void main(String[] args){
        launch(args);
    }
}