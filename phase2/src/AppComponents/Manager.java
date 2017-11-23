package AppComponents;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Parent class Manager.
 */
public class Manager implements Serializable, Observable {


    @Override
    public void removeListener(InvalidationListener listener) {

    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    private ArrayList<Object> objectList = new ArrayList<>();
}
