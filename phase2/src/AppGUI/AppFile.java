package AppGUI;

import Observers.Observer;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AppFile {
    private File currentFile;
    private ArrayList<Observer> observerList = new ArrayList<>();


    public void addObserver(Observer o){
        observerList.add(o);
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public String getLocation(){
        return currentFile.getPath();
    }

    public String getUrl(){
        return currentFile.toURI().toString();
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
        for(Observer o: observerList){
            o.update();
        }
    }
}