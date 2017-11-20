package AppGUI.CenterPanel;

import AppComponents.ImageData;
import AppGUI.MainContainer;
import AppGUI.PopUpWindow.DialogBox;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MiddleWindowController extends FolderPanelController{
    @FXML
    public Button searchButton = new Button();
    @FXML
    public TextField searchTextField = new TextField();

    @FXML
    private  ImageView imageView = new ImageView();

    private String selectedItemLocation;

    public String currentImageLocation;

    @Override
    public void initialize(URL location, ResourceBundle r){
        super.initialize(location,r);
        searchTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER)  {
                    try{searchTagClicked();
                    searchTextField.setText("");}
                    catch (Exception e){
                        DialogBox dialogBox = new DialogBox("Sorry","Search Failed");
                        dialogBox.display();
                    }
                }
            }
        });
    }
    public void refreshTable(){
        String location = selectedItemLocation;
        if (location != null){
            MainContainer.getFolderPanelController().setPanel(location);
    }
}


    @Override
    public void setPanel(String location){
        selectedItemLocation = location;
        super.setPanel(location);
        File file = new File(location);
        Image image = new Image(file.toURI().toString());
        this.imageView.setImage(image);

    }

    public void setSelectedItemLocation(String selectedItemLocation) {
        this.selectedItemLocation = selectedItemLocation;
    }

    public String getSelectedItemLocation() {
        return selectedItemLocation;
    }

    public void searchTagClicked() throws Exception{
        String inquired = searchTextField.getText();
        boolean existence = MainContainer.getAppTagManager().tagExists(inquired);
        if (existence){
            ArrayList<ImageData> foundImages = MainContainer.getAppTagManager().getImagesWithTag(inquired);
            if(MainContainer.getSearchResults() == null){
            SearchResults searchResults = new SearchResults();
            searchResults.display(foundImages);
            MainContainer.setSearchResults(searchResults);}
            else{
            MainContainer.getSearchResults().display(foundImages);}
        }
        else{DialogBox alertBox = new DialogBox("Warning","Tag Not Found!");
            alertBox.display();

        }

    }


}

