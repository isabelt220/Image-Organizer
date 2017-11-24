package AppGUI;


import AppGUI.PopUpWindow.DialogBox;
import AppGUI.TreeView.TreeViewController;
import Observers.TreeViewObserver;
import sun.applet.Main;

import java.io.IOException;

public class TopPanel {

    private TreeViewController treeViewController;

    private MainGUI main;

    public void setTreeViewController(TreeViewController treeViewController) {
        this.treeViewController = treeViewController;
    }

    public void addNewTag() {
        treeViewController.addTagClick();
    }

    public void deleteExistingTag() {
        treeViewController.deleteTagClick();
    }

    public void openImageTagEditor() throws Exception {
        try{
            treeViewController.openImageTagEditor();}
        catch (NullPointerException e){
            DialogBox warning = new DialogBox("Warning","Please choose an image");
            warning.display();
        }
    }

    public void openChooseDirectory() throws IOException{
        treeViewController.openFolder();
    }

    public void saveTagsAndExitApp() {
        MainContainer.getAppDataSerializer().saveDataToFile("AppDataConfig.txt");
        main.closeApplication();
    }

    public void openNameLog() throws Exception {
        treeViewController.openNameLogPopUp();
    }

    public void setMain(MainGUI main) {
        this.main = main;
    }
}