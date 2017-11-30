package Observers;

import AppGUI.MainGUI;

/** SubClass of Observer, observes an Main GUI */
public class MainObserver extends Observer{

    /** The MainGUI that this MainObserver observes*/
    private MainGUI main;

    /**
     * Calls the MainGUI to terminate the current session of the application.
     */
    public void closeApplication() {

        this.main.closeApplication();
    }

    /**
     * Takes the String panel as a command, displays the corresponding panel in the CenterPanel by calling MainGUI
     * Used to alter and switch panel in MainGUI.
     *
     * @param panel String description of the panel to be shown.
     */
    public void setPanel(String panel){
      if(panel.equals("center")){
          main.showCenterView();
      }
      else if(panel.equals("folder")){
          main.showFolderPanel();
      }
      else if(panel.equals("OpMenu")){
          main.showOperatingMenu();
      }
      else if(panel.equals("Tree")){
          main.showTreeView();
      }

    }

    /**
     * Setter for the target MainGUI that this MainObserver observes.
     *
     * @param main MainGUI to observe.
     */
    public void setMain(MainGUI main) {

        this.main = main;
    }

    /**
     * Return the MainGUI that this MainObserver observes
     *
     * @return MainGUI
     */
    public MainGUI getMain() {

        return main;
    }

    public boolean isMiddleWindow(){
        return main.isMiddleWindow();
    }


}
