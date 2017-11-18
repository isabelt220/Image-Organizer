package AppGUI;


public class TopPanel {

    public void addNewTag() {
        MainContainer.getTreeViewController().addTagClick();
    }

    public void deleteExistingTag() {
        MainContainer.getTreeViewController().deleteTagClick();
    }

    public void openImageTagEditor() throws Exception {
       MainContainer.getTreeViewController().openImageTagEditor();
    }

    public void openChooseDirectory() {
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
