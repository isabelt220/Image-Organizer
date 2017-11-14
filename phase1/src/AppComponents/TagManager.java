package AppComponents;

import java.util.ArrayList;

public class TagManager {
    private static ArrayList<Tag> listOfTags = new ArrayList<>(0);

    public TagManager() {

    }

    public static void tmAddTagWithoutImage(ArrayList<String> tagNameList) {
        for (String tagName : tagNameList) {
            String name = tagName.toLowerCase();
            if (!tagExists(name)) {
                Tag tag = new Tag(name);
                // Tag name string already converted to lower case here to
                // figure out if a tag already exists, Tag constructor also
                // changes tag name to lower case, may be redundant code?
                listOfTags.add(tag);
            }
            // should anything be done to inform user if a tag already exists?
        }
    }

    public static ArrayList<Tag> tmAddTagWithImage(ArrayList<String> tagNameList) {
        ArrayList<Tag> listOfTagsToAttachToImage = new ArrayList<>(0);
        for (String tagName : tagNameList) {
            String name = tagName.toLowerCase();
            if (!tagExists(name)) {
                Tag tag = new Tag(name);
                listOfTags.add(tag);
                listOfTagsToAttachToImage.add(tag);
            } else {
                listOfTagsToAttachToImage.add(getTag(name));
            }
        }
        return listOfTagsToAttachToImage;
    }

    public static ArrayList<ImageInfo> removeTag(String tagName) {
        if (tagExists(tagName)) {
            Tag tag = getTag(tagName);
            ArrayList<ImageInfo> listOfImagesWithTag = getImagesWithTag(tagName);
            listOfTags.remove(tag);
            return listOfImagesWithTag;
        }
        return null;
    }

    public static ArrayList<Tag> getListOfTags() {
        return listOfTags;
    }

    public static Tag getTag(String tagName) {
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
     * Returns a list of image information that is labelled with tag.
     *
     * @param tagName the tag name to be searched for in images.
     * @return ArrayList<ImageInfo>
     */
    public static ArrayList<ImageInfo> getImagesWithTag(String tagName) {
        Tag tag = getTag(tagName);
        if (tag != null) {
            return tag.getAssociatedImages();
        }
        return null;
    }

    /**
     * Checks and returns whether a tag already exists in listOfTags
     *
     * @param tagName the tag to be determined whether it already exists in listOfTags.Sm
     * @return boolean
     */
    public static boolean tagExists(String tagName) {
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

}
