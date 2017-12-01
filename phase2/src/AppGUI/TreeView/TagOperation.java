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
 * Helper class to TreeViewController and contains all tag operations that directly modifies Tags in TagManager.
 */
class TagOperation {

    /**
     * Takes the location of the target of centerObserver and the selected Tag(s) in list view and uses ImageManager to
     * associate the tags with the target ImageData.
     *
     * @param listView         List of all tags in TagManager (all tags that was instantiated through any way and was not deleted by the user)
     * @param centerObserver   the observer of the current middle window
     * @param treeViewObserver the observer of the current Tree
     */
    void addTagToImage(ListView listView, CenterObserver centerObserver, TreeViewObserver treeViewObserver) {
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

    /**
     * Takes the selected Tags in list view and deletes it from all all associated images.
     *
     * @param listView List of all tags in TagManager (all tags that was instantiated through any way and was not deleted by the user)
     */
    void deleteTagClick(ListView<Tag> listView) {
        ObservableList<Tag> selectedItems = listView.getSelectionModel().getSelectedItems();
        ArrayList<Tag> selectedCopies = new ArrayList<>(selectedItems.subList(0, selectedItems.size()));

        for (Tag t : selectedCopies) {
            MainContainer.getAppImageManager().removeTagFromAppAndImages(t.getTagName());
        }
    }

    /**
     * Delete tags from given image
     *
     * @param tags  Tags we need to delete
     * @param image The image we are operating on
     */
    void deleteTagFromImage(ArrayList<Tag> tags, ImageData image) {
        MainContainer.getAppImageManager().removeTagFromPic(tags, image);
    }

    /**
     * Deletes tags that is not associated with any images from TagManager
     */
    void CleanTagClick() {

        MainContainer.getAppTagManager().cleanUnusedTag();
    }

}