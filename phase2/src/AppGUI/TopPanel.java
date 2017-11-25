package AppGUI;


import AppGUI.PopUpWindow.DialogBox;
import AppGUI.TreeView.TreeViewController;
import Observers.MainObserver;

import java.io.IOException;

public class TopPanel{

    private TreeViewController treeViewController;
    private MainObserver mainObserver;


    public void openImageTagEditor() throws Exception {
        try{
            treeViewController.openImageTagEditor();}
       catch (NullPointerException e){
            DialogBox warning = new DialogBox("Warning","Please choose an image");
            warning.display();
       }
    }

    public void setMainObserver(MainObserver mainObserver) {
        this.mainObserver = mainObserver;
    }

    public void setTreeViewController(TreeViewController treeViewController) {
        this.treeViewController = treeViewController;
    }

    public void openChooseDirectory() throws IOException{
        treeViewController.openFolder();
    }

    public void saveTagsAndExitApp() {
        MainContainer.getAppDataSerializer().saveDataToFile("AppDataConfig.txt");
        mainObserver.closeApplication();
    }

    public void openNameLog() throws Exception {
        treeViewController.openNameLogPopUp();
    }

}
