package Observers;

import AppGUI.MainContainer;
import AppGUI.TreeView.TreeViewController;
import AppGUI.TreeView.TreeViewItem;

public class TreeViewObserver extends Observer{
    private TreeViewController target;

    public void setTarget(TreeViewController t) {
        this.target =t;
    }

    public void update(){
        target.reSetTree();
    }
}