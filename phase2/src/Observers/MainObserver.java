package Observers;

import AppGUI.MainGUI;

public class MainObserver extends Observer{
    private MainGUI main;

    public void setMain(MainGUI main) {
        this.main = main;
    }

    public MainGUI getMain() {
        return main;
    }

    public void setPanel(String panel){
      if(panel.equals("center")){
          main.showCenterView();
      }else if(panel.equals("folder")){
          main.showFolderPanel();
      }else if(panel.equals("OpMenu")){
          main.showOperatingMenu();
      }else if(panel.equals("Tree")){
          main.showTreeView();
      }

    }
}
