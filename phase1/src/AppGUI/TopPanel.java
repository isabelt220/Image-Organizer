package AppGUI;


import AppGUI.PopUpWindow.DialogBox;

import java.io.IOException;

public class TopPanel {

    public void addNewTag() {
        MainContainer.getTreeViewController().addTagClick();
    }

    public void deleteExistingTag() {
        MainContainer.getTreeViewController().deleteTagClick();
    }

    public void openImageTagEditor() throws Exception {
        try{
       MainContainer.getTreeViewController().openImageTagEditor();}
       catch (NullPointerException e){
            DialogBox warning = new DialogBox("Warning","Please choose an image");
            warning.display();
       }
    }

    public void openChooseDirectory() throws IOException{
        MainContainer.getTreeViewController().openFolder();
    }

    public void saveTagsAndExitApp() {
        MainContainer.getAppTagManager().saveTagsToFile("tagConfig.txt");
        MainContainer.getMain().closeApplication();
    }

    public void openNameLog() throws Exception {
        MainContainer.getTreeViewController().openNameLogPopUp();
    }

}
