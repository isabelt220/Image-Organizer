package AppGUI.PopUpWindow;

import AppGUI.TreeView.TreeViewController;
import Observers.CenterObserver;
import Observers.TreeViewObserver;

import java.io.File;

public class OpenPopUp {

    private TreeViewObserver treeViewObserver = new TreeViewObserver();

    private CenterObserver centerObserver = new CenterObserver();

    public OpenPopUp(TreeViewController t){
        treeViewObserver.setTarget(t);
    }

    /**
     * Takes the current selected image from the treeView, and initializes a ImageEditor that modifies selected image
     *
     * @throws Exception
     */
    public void openImageTagEditor() throws Exception {
        File currentFile = treeViewObserver.getSelectedFile();
        if (currentFile != null && currentFile.isDirectory()) {
            DialogBox alertBox = new DialogBox("Info", "Cannot rename a folder");
            alertBox.display();
        } else if (currentFile != null) {
            ImageTagEditor imageTagEditor = new ImageTagEditor();
            imageTagEditor.display(treeViewObserver, centerObserver);
        }
    }

    public void openNameLog(){
        try {
            NameLogPopUp nameLogPopUp = new NameLogPopUp();
            nameLogPopUp.display(treeViewObserver);
        } catch (Exception e) {
            DialogBox alertBox = new DialogBox("Warning", "Please choose an Image");
            alertBox.display();
        }
    }

    public void setCenterObserver(CenterObserver centerObserver) {
        this.centerObserver = centerObserver;
    }


}
