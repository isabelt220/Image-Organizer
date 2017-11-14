package AppGUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;



public class TopPanel extends MenuBar {


    private Menu editButton;
    private Menu helpButton;

    public TopPanel(){

        Menu fileButton = new Menu("File");
        editButton = new Menu("Edit");
        helpButton = new Menu("Help");
        MenuItem fileOpen = new CustomMenuItem(new Label("Open"));
        CustomMenuItem fileClose = new CustomMenuItem(new Label("Close"));
        CustomMenuItem editTags = new CustomMenuItem(new Label("Tags"));
        CustomMenuItem editLocation = new CustomMenuItem(new Label("Location"));
        CustomMenuItem helpTerminate = new CustomMenuItem(new Label("Terminate"));
        CustomMenuItem helpGuide = new CustomMenuItem(new Label("UserGuide"));
        fileButton.getItems().addAll(fileOpen, fileClose);
        editButton.getItems().addAll(
                editTags,
                editLocation
        );
        helpButton.getItems().addAll(
                helpGuide,
                helpTerminate
        );

//        fileOpen.setOnAction(event -> );
//        fileClose.setOnAction(event -> );
//        editTags.setOnAction(event -> );
//        editLocation.setOnAction(event -> );
//        helpGuide.setOnAction(event -> );
//        helpTerminate.setOnAction(event -> );
        this.getMenus().addAll(fileButton, editButton, helpButton);

    }

}
