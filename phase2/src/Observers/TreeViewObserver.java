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



    public void setItem(File f){
        if (target != null){
            target.getTreeView().getSelectionModel().getSelectedItem().setValue(f);
        }}

    public File getSelectedFile(){
            if(target != null){
                return target.getTreeView().getSelectionModel().getSelectedItem().getValue();
            }
            else{
                return null;
            }}
}