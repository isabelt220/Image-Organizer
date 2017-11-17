package AppGUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import sun.applet.Main;


public class TopPanel {
    @FXML
    private MenuItem exitButton;

    public void addNewTag() {
        MainController.getTreeViewController().addTagClick();
    }

    public void deleteExistingTag() {MainController.getTreeViewController().deleteTagClick();
    }

    public void openImageTagEditor() throws Exception {
       MainController.getTreeViewController().openImageTagEditor();
    }

    public void openChooseDirectory() {
        MainController.getTreeViewController().openFolder();
    }

}
