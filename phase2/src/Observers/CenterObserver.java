package Observers;

import AppGUI.CenterPanel.MiddleWindowController;

/** SubClass of Observer, observes an MiddleWindow Controller
 */
public class CenterObserver extends Observer{

    /** The MiddleWindowController that this CenterObserver observes*/
    private MiddleWindowController target;

    /**
     * Updates the CenterPanel controlled by the MiddleWindowController that this CenterObserver observes to the
     * file with the location specified in the parameter
     *
     * @param location String absolute path of file
     */
    public void update(String location){

        target.setPanel(location);
    }

    /**
     * Setter for the target MiddleWindowController that this CenterObserver observes.
     *
     * @param t MiddleWindowController to observe.
     */
    public void setTarget(MiddleWindowController t) {

        this.target =t;
    }

    /**
     * Getter for the target MiddleWindowObserver of this CenterObserver.
     * @return MiddleWindowController observed by this CenterObserver
     */
    public MiddleWindowController getTarget() {

        return target;
    }


}