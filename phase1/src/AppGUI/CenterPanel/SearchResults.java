package AppGUI.CenterPanel;

import AppComponents.ImageData;
import AppGUI.MainContainer;
import AppGUI.MainGUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sun.applet.Main;

import java.util.ArrayList;

public class SearchResults {
    private Stage mainStage;
    private AnchorPane mainLayout;

    public void display(ArrayList<ImageData> imageList) throws Exception{
        if(MainContainer.getSearchResultsController()==null){
        mainStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SearchResults.class.getResource("SearchResults.fxml"));
        mainLayout = loader.load();
        mainStage.setScene(new Scene(mainLayout));
        MainContainer.setSearchResultsController((loader.getController()));
        MainContainer.getSearchResultsController().setResults(imageList);
        mainStage.show();
    }else{
            MainContainer.getSearchResultsController().setResults(imageList);
          mainStage.show();
        }
    }


}
