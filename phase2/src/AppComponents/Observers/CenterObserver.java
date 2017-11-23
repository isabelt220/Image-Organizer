package AppComponents.Observers;

import AppGUI.MainContainer;

public class CenterObserver extends Observer{
    public void update(){
        if(MainContainer.getMiddleWindowController() != null){
            if(MainContainer.getMiddleWindowController().getSelectedItemLocation() != null){
                MainContainer.getMiddleWindowController().setPanel(MainContainer.getMiddleWindowController().getSelectedItemLocation());
            }
        }
    }
}
