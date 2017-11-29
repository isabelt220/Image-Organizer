package Observers;

import AppGUI.CenterPanel.MiddleWindowController;

/** SubClass of Observer, observes an MiddleWindow Controller
 */
public class CenterObserver extends Observer{

    /** The MiddleWindowController that this CenterObserver observes*/
    private MiddleWindowController target;

    public void update(String location){

        target.setPanel(location);
    }

    public void refresh(){
        if(target.getSelectedItemLocation()!=null){
        target.setPanel(target.getSelectedItemLocation());}
    }
    public MiddleWindowController getTarget() {

        return target;
    }

    public void setTarget(MiddleWindowController t) {

        this.target =t;
    }


}