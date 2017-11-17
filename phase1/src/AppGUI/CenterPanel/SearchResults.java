package AppGUI.CenterPanel;

import AppComponents.ImageData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SearchResults {
    private ArrayList<ImageData> images;

    public void display() throws Exception{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SearchResults.fxml"));
        stage.setScene(new Scene(root, 543, 458));
        stage.show();
    }

    public void setImages(ArrayList<ImageData> imagesList){
        images = imagesList;
    }

}
