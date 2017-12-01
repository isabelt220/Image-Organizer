package Observers;

import AppComponents.ImageData;
import AppGUI.TreeView.OperatingMenuController;

/**
 * SubClass of Observer, observes an OperatingMenu Controller
 */
public class OpMenuObserver extends Observer {

    /**
     * The OperatingMenuController that this OpMenuObserver observes
     */
    private OperatingMenuController target;


    /**
     * Updates the operating image of the OperatingMenu controlled by the OperatingMenuController that this OpMenuObserver observes to the
     * image file with the ImageData specified in the parameter
     *
     * @param image ImageData of the image file under operation
     */
    public void update(ImageData image) {

        target.setOperatingImage(image);
        target.displayLists();
    }

    /**
     * Setter for the target OperatingMenuController that this OperatingMenuObserver observes.
     *
     * @param target OperatingMenuController to observe.
     */
    public void setTarget(OperatingMenuController target) {

        this.target = target;
    }

}
