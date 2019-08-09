package AppComponents;

import AppGUI.MainContainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

/**
 * Keeps track of and manipulates all Tags and associated information for this application.
 * Interacts with Tag (initializes and manages), ImageManager, and MainContainer(is initialized and
 * manipulated by). Implements Serializable so information can be saved after ending a session of
 * the application.
 */
public class TagManager implements Serializable {

    // An empty ArrayList of Tags to keep a list of all Tags added by the user.
    private ArrayList<Tag> listOfTags = new ArrayList<>(0);

    // A list that visually displays TagManager's listOfTags to the user.
    private ObservableList<Tag> observableTagList = FXCollections.observableList(listOfTags);

    /**
     * Checks and returns whether a tag already exists in listOfTags
     *
     * @param tagName the tag to be determined whether it already exists in listOfTags.Sm
     * @return boolean
     */
    public boolean tagExists(String tagName) {
        String name = tagName.toLowerCase();
        Tag tag = new Tag(name);
        return !listOfTags.isEmpty() && listOfTags.contains(tag);
    }

    /**
     * Creates and adds Tag(s) to the listOfTags if there is no Tag(s) with the tag name in the
     * listOfTags.
     *
     * @param tagNameList an ArrayList of Strings of tag names that users would like to add to the
     *                    listOfTags
     */
    public void tmAddTagWithoutImage(ArrayList<String> tagNameList) {
        for (String tagName : tagNameList) {
            String name = tagName.toLowerCase();
            if (!tagExists(name) && !name.equals("")) {
                Tag tag = new Tag(name);
                // Tag name string already converted to lower case here to
                // figure out if a tag already exists, Tag constructor also
                // changes tag name to lower case, may be redundant code?
                observableTagList.add(tag);
            }
            // should anything be done to inform user if a tag already exists?
        }
    }

    /**
     * Creates new Tag(s) if tag does not exist in listOfTags, gets Tag(s) and adds the Tag(s) to
     * listOfTags if it already exists in listOfTags. Returns ArrayList of Tags
     * listOfTagsToAttachToImage.
     *
     * @param image       the image to create tags for and associate the tags to.
     * @param tagNameList the list of string of tag names to add or create.
     * @return ArrayList<Tag></Tag>
     */
    ArrayList<Tag> tmAddTagWithImage(ImageData image, ArrayList<String> tagNameList) {
        ArrayList<Tag> listOfTagsToAttachToImage = new ArrayList<>(0);
        for (String tagName : tagNameList) {
            String name = tagName.toLowerCase();
            if (!tagExists(name) && !name.equals("")) {
                Tag tag = new Tag(name);
                tag.addImage(image);
                observableTagList.add(tag);
                listOfTagsToAttachToImage.add(tag);
            } else if (tagExists(name)) {
                Tag tag = getTag(name);
                tag.addImage(image);
                listOfTagsToAttachToImage.add(getTag(name));
            }
        }
        return listOfTagsToAttachToImage;
    }

    /**
     * Removes the Tag with the given String tagName from listOfTags if the Tag exists and removes the
     * tag from all images that has the tag attached to it.
     *
     * @param tagName the String of the Tag to be removed from listOfTags.
     * @return ArrayList<ImageData></ImageData>
     */
    ArrayList<ImageData> removeTag(String tagName) {
        if (tagExists(tagName)) {
            Tag tag = getTag(tagName);
            // getTag() may return null
            ArrayList<ImageData> listOfImagesWithTag = tag.getAssociatedImages();
            MainContainer.getMasterLog().deleteTag(tag.getTagName());
            observableTagList.remove(tag);
            return listOfImagesWithTag;
        }
        return null;
    }

    public void cleanUnusedTag() {
        ArrayList<Tag> temp = new ArrayList<>(observableTagList.subList(0, observableTagList.size()));
        for (Tag t : temp) {
            if (t.getAssociatedImages().size() == 0) {
                MainContainer.getMasterLog().deleteTag(t.getTagName());
                observableTagList.remove(t);
            }
        }
    }

    /**
     * Sets the listOfTags and observableTagList.
     *
     * @param list ArrayList of Tags to be set as.
     */
    void setListOfTags(ArrayList<Tag> list) {
        listOfTags = list;
        observableTagList = FXCollections.observableList(listOfTags);
    }

    /**
     * Returns the listOfTags.
     *
     * @return current existing tags
     */
    ArrayList<Tag> getListOfTags() {

        return listOfTags;
    }

    /**
     * @return ObservableList of Tags for displaying the currently existing list of tags to the users.
     */
    public ObservableList getObservableTagList() {

        return observableTagList;
    }

    /**
     * Takes a String tagName and returns the Tag with the tagName in listOfTags if a Tag with that
     * tagName exists.
     *
     * @param tagName the name of the tag to search for in listOfTags.
     * @return the Tag with the tagName if one exists, otherwise returns null.
     */
    public Tag getTag(String tagName) {
        String name = tagName.toLowerCase();
        Tag tag = new Tag(name);
        if (!listOfTags.isEmpty() && tagExists(name) && listOfTags.contains(tag)) {
            int i = listOfTags.indexOf(tag);
            return listOfTags.get(i);
        }
        return null;
    }
}
