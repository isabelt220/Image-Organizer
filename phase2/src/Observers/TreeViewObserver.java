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
        if (target!=null&&target.getTreeView().getRoot() != null) {
            TreeViewItem listHelper = new TreeViewItem();
            target.getTreeView().setRoot(listHelper.generateTreeItem(target.getTreeView().getRoot().getValue()));
        }
    }
}