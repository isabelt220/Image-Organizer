package AppComponents;

import java.util.ArrayList;

public class TagManager {
    private static ArrayList<Tag> listOfTags = new ArrayList<>(0);

    public TagManager() {

    }

    public void addTag(Tag tag) {
        if (!tagExists(tag)) {
            listOfTags.add(tag);
        }
    }

    public void addTag(ArrayList<Tag> tagList) {
        for (Tag tag: listOfTags) {
            if (!tagExists(tag)) {
                listOfTags.add(tag);
            }
        }
    }

    /**
     * Checks and returns whether a tag already exists in listOfTags
     *
     * @param tag the tag to be determined whether it already exists in listOfTags.Sm
     * @return boolean
     */
    private boolean tagExists(Tag tag) {
        return listOfTags.contains(tag);
    }

    /**
     * Returns a list of image information that is labelled with tag.
     *
     * @param tag the tag to be searched for in images.
     * @return ArrayList<ImageInfo>
     */
    public ArrayList<ImageInfo> searchImagesWithTag(Tag tag) {
        return tag.getAssociatedImages();
    }
}
