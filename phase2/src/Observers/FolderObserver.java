package Observers;

import AppGUI.CenterPanel.FolderPanelController;

public class FolderObserver extends Observer{
    private FolderPanelController target;

    public void setTarget(FolderPanelController t) {
        this.target =t;
    }

    @Override
    public void update() {
        if(target !=null){
            target.setPanel();
        }
    }
}