package AppComponents;

import AppGUI.MainContainer;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Tag object has a unique name, and list of associatedImages of ImageData. It stores and
 * manipulates this list, interacting with TagManager, and ImageData.
 */
public class Tag implements Serializable {

    // String name is unique to a tag and is used as the identifier of a Tag object.
    private String name;

    // ArrayList<ImageData> is a list of ImageData objects this Tag is associated with, can be used to
    // add/search/delete Tag and ImageData objects.
    private ArrayList<ImageData> associatedImages;

    /**
     * Constructor for a Tag object, initializes the name as the lowercase of the parameter names, and
     * generates an empty ArrayList.
     *
     * @param names String
     */
    public Tag(String names) {
        this.name = names.toLowerCase();
        this.associatedImages = new ArrayList<>();
        MainContainer.getMasterLog().innitTag(this.name);
    }

    /**
     * Takes an image ImageData, checks for existence and adds it to the associatedImage list, and
     * thus adds association to said image.
     *
     * @param image ImageData
     */
    void addImage(ImageData image) {
        if (!(associatedImages.contains(image))) {
            MainContainer.getMasterLog().addedImageToTag(name, image.getLocation());
            this.associatedImages.add(image);
        }
    }

    /**
     * Takes an image ImageData and deletes it from the associatedImage list, and thus deleting
     * association to said image.
     *
     * @param image ImageData
     */
    void removeImage(ImageData image) {
        if (associatedImages.contains(image)) {
            MainContainer.getMasterLog().deletedImageFromTag(name, image.getLocation());
            this.associatedImages.remove(image);
        }
    }

    /**
     * Overrides equals method in Object, check if obj Object is an Tag and if it has the same name as
     * this Tag object (name of Tag objects should always be unique).
     *
     * @param obj Object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Tag) && ((Tag) obj).name.equals(this.name);
    }

    /**
     * Getter for name, returns it in the form of String, is used to obtain the name of the Tag.
     *
     * @return String the name of this Tag.
     */
    public String getTagName() {

        return name;
    }

    /**
     * Getter for associatedImages list, returns an ArrayList of ImageData objects that this a is
     * associated with, can be used to search images with a certain tag.
     *
     * @return ArrayList<ImageData> the list of associated ImageData.
     */
    public ArrayList<ImageData> getAssociatedImages() {

        return associatedImages;
    }
}
