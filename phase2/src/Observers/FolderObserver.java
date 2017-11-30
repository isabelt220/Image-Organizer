package Observers;

import AppGUI.CenterPanel.FolderPanelController;

/** SubClass of Observer, observes an FolderPanel Controller
 */
public class FolderObserver extends Observer{

    /** The FolderPanelController that this FolderObserver observes*/
    private FolderPanelController target;



    /**
     * Updates the FolderPanel controlled by the FolderPanelController that this FolderObserver observes to the
     * file with the location specified in the parameter
     *
     * @param location String absolute path of file
     */
    public void update(String location) {
        if(target !=null){
            target.setPanel(location);
        }
    }

    /**
     * Setter for the target SearchResultsController that this SearchResultsObserver observes.
     *
     * @param t SearchResultsController to observe.
     */
    public void setTarget(FolderPanelController t) {

        this.target =t;
    }
}