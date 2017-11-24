package Observers;

import AppGUI.CenterPanel.MiddleWindowController;

public class CenterObserver extends Observer{
    private MiddleWindowController target;

    public void setTarget(MiddleWindowController t) {
        this.target =t;
    }

    public void update(){
        if(target != null && target.getTargetFile() != null){
            target.setPanel();
        }
    }
}