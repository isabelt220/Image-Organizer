package AppGUI.CenterPanel;

import AppComponents.ImageData;
import AppGUI.MainContainer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SearchResults {
    private Stage mainStage;

    public void display(ArrayList<ImageData> imageList) throws Exception {
        if (MainContainer.getSearchResultsController() == null) {
            mainStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SearchResults.class.getResource("SearchResults.fxml"));
            AnchorPane mainLayout = loader.load();
            mainStage.setScene(new Scene(mainLayout));
            MainContainer.setSearchResultsController((loader.getController()));
            MainContainer.getSearchResultsController().setResults(imageList);
            mainStage.show();
        } else {
            MainContainer.getSearchResultsController().setResults(imageList);
            mainStage.show();
        }
    }


}
