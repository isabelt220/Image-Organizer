package Observers;

import AppGUI.CenterPanel.FolderPanelController;

public class FolderObserver extends Observer{
    private FolderPanelController target;

    public void setTarget(FolderPanelController t) {
        this.target =t;
    }

    public void update(String location) {
        if(target !=null){
            target.setPanel(location);
        }
    }
}