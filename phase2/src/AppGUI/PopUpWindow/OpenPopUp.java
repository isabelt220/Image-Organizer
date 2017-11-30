package AppGUI.PopUpWindow;

import AppGUI.TreeView.TreeViewController;
import Observers.CenterObserver;
import Observers.TreeViewObserver;

import java.io.File;

/**
 * Although not a parent class, but acts as a manager for all pop up windows in this application.
 * Initialized by MiddleWindowController and TopPanel, and in turn initialises new ImageEditors, NameLogPopUps and MasterLogPopUps.
 */
public class OpenPopUp {

    /** Initializes new tree observer object that will be set to the treeViewController in the constructor*/
    private TreeViewObserver treeViewObserver = new TreeViewObserver();

    /** Initializes new center observer to update*/
    private CenterObserver centerObserver = new CenterObserver();

    /**
     * Constructor for this OpenPopUp, set the target of the this treeViewObserver.
     *
     * @param t TreeViewController of the current treeView panel
     */
    public OpenPopUp(TreeViewController t){

        treeViewObserver.setTarget(t);
    }

    /**
     * Takes the current selected image from the treeView, and initializes a ImageEditor that modifies selected image
     *
     * @throws Exception IOException
     */
    public void openImageTagEditor() throws Exception {
        File currentFile = treeViewObserver.getSelectedFile();
        if (currentFile != null && currentFile.isDirectory()) {
            DialogBox alertBox = new DialogBox("Info", "Cannot rename a folder");
            alertBox.display();
        }
        else if (currentFile != null) {
            ImageTagEditor imageTagEditor = new ImageTagEditor();
            imageTagEditor.display(treeViewObserver, centerObserver);
        }
    }

    /**
     * Takes the current selected image from the treeView, and initializes a NameLogPopUp that displays a history of tag modification of
     * the selected image.
     *
     * @throws Exception IOException
     */
    public void openNameLog() throws Exception{
        File currentFile = treeViewObserver.getSelectedFile();
        if (currentFile != null && currentFile.isDirectory()) {
            DialogBox alertBox = new DialogBox("Info", "Cannot choose a folder!");
            alertBox.display();
        }
        else if (currentFile != null) {
            NameLogPopUp nameLogPopUp = new NameLogPopUp();
            nameLogPopUp.display(treeViewObserver, centerObserver);
        }
    }

    /**
     * Setter for this center observer, used to give and update information to the center panel observed by centerObserver.
     *
     * @param centerObserver observes current center panel of the application
     */
    public void setCenterObserver(CenterObserver centerObserver) {

        this.centerObserver = centerObserver;
    }

    /**
     * Setter for this treeView observer, used to obtain and update information to the tree view panel observed by treeViewObserver.
     *
     * @param treeViewObserver observes current treeView panel of the application
     */
    public void setTreeViewObserver(TreeViewObserver treeViewObserver) {

        this.treeViewObserver = treeViewObserver;
    }


}
