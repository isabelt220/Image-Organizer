package AppGUI;


import AppGUI.PopUpWindow.DialogBox;
import AppGUI.PopUpWindow.OpenPopUp;
import AppGUI.TreeView.TreeViewController;
import Observers.MainObserver;

import java.io.IOException;

/**
 * Controller for the menu bar of the application, communicates with main, and treeViewController to initiate pop ups through
 * their controllers.
 */
public class TopPanel{

    /** The controller for the current treeView*/
    private TreeViewController treeViewController;

    /** Observer for the current mainView*/
    private MainObserver mainObserver;

    /**
     * Calls treeViewController to initiate a ImageTagEditor pop up
     * Catches NullPointerException and displays warning message.
     *
     * @throws Exception IOException
     */
    public void openImageTagEditor() throws Exception {
        try{
            treeViewController.openImageTagEditor();}
       catch (NullPointerException e){
            DialogBox warning = new DialogBox("Warning","Please choose an image");
            warning.display();
       }
    }

    /**
     * Calls treeViewController to open directory chooser.
     *
     * @throws IOException exceptions
     */
    public void openChooseDirectory() throws IOException{

        treeViewController.openFolder();
    }

    /**
     * Calls AppDataSerializer to save current ImageData and Tag information.
     */
    public void saveTagsAndExitApp() {
        MainContainer.getAppDataSerializer().saveDataToFile("AppDataConfig.txt");
        mainObserver.closeApplication();
    }

    /**
     * Calls treeViewController to open a nameLog pop up.
     *
     * @throws Exception IOException
     */
    public void openNameLog() throws Exception {

        treeViewController.openNameLogPopUp();
    }

    public void openTagLog(){
        OpenPopUp openPopUp = new OpenPopUp();
    }

    /**
     * Sets the mainObserver to communicate with.
     *
     * @param mainObserver initialized in MainGUI
     */
     void setMainObserver(MainObserver mainObserver) {

        this.mainObserver = mainObserver;
    }

    /**
     * Setter for this treeView controller.
     *
     * @param treeViewController of the current treeView panel
     */
     void setTreeViewController(TreeViewController treeViewController) {

        this.treeViewController = treeViewController;
    }


}
