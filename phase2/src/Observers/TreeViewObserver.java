package Observers;

import AppGUI.TreeView.TreeViewController;

import java.io.File;

/** SubClass of Observer, observes an TreeView Controller
 */
public class TreeViewObserver extends Observer{


    private TreeViewController target;

    /**
     * Setter for the target TreeViewController that this TreeViewObserver observes.
     *
     * @param t TreeViewController to observe.
     */
    public void setTarget(TreeViewController t) {

        this.target =t;
    }

    /**
     * Calls TreeViewController to refresh and updates the treeView for the newest name and tags of the files.
     */
    public void update(){

        target.reSetTree();
    }


    /**
     * Setter for the selected item in treeView.
     * Called upon when the user selects a TreeItem<File></> in TreeView
     *
     * @param f File selected by user
     */
    public void setItem(File f){
        if (target != null){
            target.getTreeView().getSelectionModel().getSelectedItem().setValue(f);
        }}

    /**
     * Return the user selected file in treeView, frequently caled upon by other panels to edit/display/etc.
     * selected image/folder file.
     *
     * @return File User selected file in TreeView.
     */
    public File getSelectedFile(){
            if(target != null){
                return target.getTreeView().getSelectionModel().getSelectedItem().getValue();
            }
            else{
                return null;
            }}
}