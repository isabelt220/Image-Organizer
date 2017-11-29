package Observers;

/**
 * Parent class for all Observers of all panels in this application, contains a target, which is the controller of the
 * panel it observes.
 * Used to communicate information and updates between user inputs on different panes.
 * @param <T> Various types of Controllers.
 */
public class Observer<T> {

    /** The controller which this Observer observes */
    private T target;

    /**
     * Setter for this target
     * @param target Controller
     */
    public void setTarget(T target) {
        this.target = target;
    }


}
