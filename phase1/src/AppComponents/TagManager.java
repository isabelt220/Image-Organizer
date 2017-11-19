package AppComponents;

import AppGUI.PopUpWindow.DialogBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TagManager {
    private ArrayList<Tag> listOfTags = new ArrayList<>(0);
    private ObservableList<Tag> observableTagList = FXCollections.observableList(new ArrayList<>());


    public TagManager() {
        Path currentRelativePath = Paths.get("");
        String filePath = currentRelativePath.toAbsolutePath().toString();
        filePath += "/tagConfig.txt";

        File file = new File(filePath);
        if (file.exists() && file.length() != 0) {
            readTagsFromFile(filePath);
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return current existing tags
     */
    public ArrayList<Tag> getListOfTags() {
        return listOfTags;
    }

    /**
     * Takes a String tagName and returns the Tag with the tagName in listOfTags
     * if a Tag with that tagName exists.
     *
     * @param tagName the name of the tag to search for in listOfTags.
     * @return the Tag with the tagName if one exists, otherwise returns null.
     */
    private Tag getTag(String tagName) {
        String name = tagName.toLowerCase();
        if (!listOfTags.isEmpty() && tagExists(name)) {
            for (Tag tag : listOfTags) {
                if (name.equals(tag.getTagName())) {
                    return tag;
                }
            }
        }
        return null;
    }

    /**
     * Checks and returns whether a tag already exists in listOfTags
     *
     * @param tagName the tag to be determined whether it already exists in listOfTags.Sm
     * @return boolean
     */
    public boolean tagExists(String tagName) {
        String name = tagName.toLowerCase();
        if (!listOfTags.isEmpty()) {
            for (Tag tag : listOfTags) {
                if (name.equals(tag.getTagName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Creates and adds Tag(s) to the listOfTags if there is no Tag(s) with
     * the tag name in the listOfTags.
     *
     * @param tagNameList an ArrayList of Strings of tag names that users
     *                    would like to add to the listOfTags
     */
    public void tmAddTagWithoutImage(ArrayList<String> tagNameList) {
        for (String tagName : tagNameList) {
            String name = tagName.toLowerCase();
            if (!tagExists(name) && !name.equals("")) {
                Tag tag = new Tag(name);
                // Tag name string already converted to lower case here to
                // figure out if a tag already exists, Tag constructor also
                // changes tag name to lower case, may be redundant code?
                listOfTags.add(tag);
                observableTagList.add(tag);
            }
            // should anything be done to inform user if a tag already exists?
        }
    }

    /**
     * Creates new Tag(s) if tag does not exist in listOfTags, gets Tag(s) and
     * adds the Tag(s) to listOfTags if it already exists in listOfTags.
     * Returns ArrayList of Tags listOfTagsToAttachToImage.
     *
     * @param image the image to create tags for and associate the tags to.
     * @param tagNameList the list of string of tag names to add or create.
     * @return
     */
    ArrayList<Tag> tmAddTagWithImage(ImageData image, ArrayList<String> tagNameList) {
        ArrayList<Tag> listOfTagsToAttachToImage = new ArrayList<>(0);
        for (String tagName : tagNameList) {
            String name = tagName.toLowerCase();
            if (!tagExists(name) && !name.equals("")) {
                Tag tag = new Tag(name);
                tag.addImage(image);
                listOfTags.add(tag);
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

    public ArrayList<ImageData> removeTag(String tagName) {
        if (tagExists(tagName)) {
            Tag tag = getTag(tagName);
            // getTag() may return null
            ArrayList<ImageData> listOfImagesWithTag = tag.getAssociatedImages();
            listOfTags.remove(tag);
            observableTagList.remove(tag);
            return listOfImagesWithTag;
        }
        return null;
    }

    public ObservableList getObservableTagList() {
        return observableTagList;
    }

    /**
     * Returns a list of image information that is labelled with tag.
     *
     * @param tagName the tag name to be searched for in images.
     * @return ArrayList<ImageInfo>
     */
    // May be redundant code as getAssociatedImages() exist in Tag class?
    public ArrayList<ImageData> getImagesWithTag(String tagName) {
        Tag tag = getTag(tagName);
        if (tag != null) {
            return tag.getAssociatedImages();
        }
        return null;
    }

    void removeAssociatedImageFromTags(ArrayList<Tag> tagList, ImageData image) {
        for (Tag tag : tagList) {
            tag.removeImage(image);
        }
    }

    void readTagsFromFile(String filePath) {
        try {
            FileInputStream is = new FileInputStream(filePath);
            ObjectInputStream os = new ObjectInputStream(is);

            int num = os.readInt();

            for (int i = 0; i < num; i++) {
                Tag tag = (Tag) os.readObject();
                listOfTags.add(tag);
                observableTagList.add(tag);
            }

            os.close();
            is.close();

        } catch (Exception e) {
            DialogBox warning = new DialogBox("Warning", "Failed to read the save file.");
            warning.display();
        }
    }

    public void saveTagsToFile(String filePath) {
        try {
            FileOutputStream fs = new FileOutputStream(filePath);
            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeInt(listOfTags.size());

            for (Tag tag : listOfTags) {
                os.writeObject(tag);
            }

            os.close();

        } catch (IOException e) {
            DialogBox warning = new DialogBox("Warning", "Failed to save");
            warning.display();
        }

    }
}
