package AppComponents.Observers;

import AppGUI.MainContainer;
import AppGUI.TreeView.TreeViewController;
import AppGUI.TreeView.TreeViewItem;

import java.util.Observable;

public class TreeViewObserver extends Observer{
    public void update(){
        if(MainContainer.getTreeViewController() != null){
            if(MainContainer.getTreeViewController().getTreeView().getRoot().getValue()!=null){
                TreeViewController controller = MainContainer.getTreeViewController();
                TreeViewItem helper = new TreeViewItem();
                controller.getTreeView().setRoot(helper.generateTreeItem(controller.getTreeView().getRoot().getValue()));
            }
        }
    }
}
