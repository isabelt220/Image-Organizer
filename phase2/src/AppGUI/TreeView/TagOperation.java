package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.Tag;
import AppGUI.MainContainer;
import Observers.CenterObserver;
import Observers.TreeViewObserver;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;

/**
 * Helper class to TreeViewController and contains all tag operations directly
 */
public class TagOperation {

    public void addTagToImage(ListView listView, CenterObserver centerObserver, TreeViewObserver treeViewObserver) {
        ObservableList<Tag> tagList = listView.getSelectionModel().getSelectedItems();
        String location = centerObserver.getTarget().getSelectedItemLocation();
        if (tagList != null && location != null) {
            ImageData currentImage = MainContainer.getAppImageManager().getImage(location);
            ArrayList<String> addList = new ArrayList<>();
            for (Tag t : tagList) {
                addList.add(t.getTagName());
            }
            MainContainer.getAppImageManager().imAddTagWithImage(currentImage, addList);
            centerObserver.update(currentImage.getLocation());
            treeViewObserver.update();
        }
    }

    public void deleteTagClick(ListView<Tag> listView) {
        ObservableList<Tag> selectedItems = listView.getSelectionModel().getSelectedItems();
        ArrayList<Tag> selectedCopies = new ArrayList<>(selectedItems.subList(0, selectedItems.size()));

        for (Tag t : selectedCopies) {
            MainContainer.getAppImageManager().removeTagFromAppAndImages(t.getTagName());
        }


    }

    public void CleanTagClick(){
        MainContainer.getAppTagManager().cleanUnusedTag();
    }
}
