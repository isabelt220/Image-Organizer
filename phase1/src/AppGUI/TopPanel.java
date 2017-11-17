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



public class TopPanel {
    @FXML
    private MenuItem exitButton;

    public void addNewTag() {
        MainGUI.treeViewController.addTagClick();
    }

    public void deleteExistingTag() {
        MainGUI.treeViewController.deleteTagClick();
    }

    public void openImageTagEditor() throws Exception {
        MainGUI.treeViewController.openImageTagEditor();
    }

    public void openChooseDirectory() {
        MainGUI.treeViewController.openFolder();
    }

}
