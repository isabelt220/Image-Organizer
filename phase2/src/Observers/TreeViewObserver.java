package Observers;

import AppGUI.MainContainer;
import AppGUI.TreeView.TreeViewController;
import AppGUI.TreeView.TreeViewItem;

import java.io.File;

public class TreeViewObserver extends Observer{
    private TreeViewController target;

    public void setTarget(TreeViewController t) {
        this.target =t;
    }

    public void update(){
        target.reSetTree();
    }

    public File getSelectedFile(){
        return target.getTreeView().getSelectionModel().getSelectedItem().getValue();
    }
}