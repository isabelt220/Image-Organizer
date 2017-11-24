package Observers;

import AppComponents.ImageData;
import AppGUI.TreeView.OperatingMenuController;

public class OpMenuObserver extends Observer{
    private OperatingMenuController target;

    public void setTarget(OperatingMenuController target) {
        this.target = target;
    }

    public void update(ImageData image){
        target.setOperatingMenu(image);
    }
}
