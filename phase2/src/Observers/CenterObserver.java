package Observers;

import AppGUI.CenterPanel.MiddleWindowController;

public class CenterObserver extends Observer{
    private MiddleWindowController target;

    public MiddleWindowController getTarget() {
        return target;
    }

    public void setTarget(MiddleWindowController t) {
        this.target =t;
    }

    public void update(String location){
        target.setPanel(location);
    }
    public void refresh(){
        if(target.getSelectedItemLocation()!=null){
        target.setPanel(target.getSelectedItemLocation());}
    }
}