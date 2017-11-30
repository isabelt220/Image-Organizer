package AppComponents;

import AppGUI.MainContainer;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
Keeps track of and manipulates all ImageData and associated information for this application.
Interacts with ImageData (initializes and manages), TagManager, and MainContainer(is initialized and manipulated by).
 Implements Serializable so information can be saved after ending a session of the application.
*/
public class ImageManager implements Serializable {

    // Initializes an empty ArrayList of ImageData objects that this ImageManager contains
    private ArrayList<ImageData> imageList = new ArrayList<>();


    /**
     * Takes a newImage ImageData, checks for existence and adds it to this imageList.
     *
     * @param newImage ImageData
     */
    void addImage(ImageData newImage) {

        if (!(imageList.contains(newImage))){
            imageList.add(newImage);}
    }

    void setImageList(ArrayList<ImageData> list) {
        imageList = list;
    }

    /**
     * Getter for imageList, both the method and the instances are static so that it can be called before object initialization
     * to avoid Exceptions, this method can be used to iterate through all ImageData objects created.
     *
     * @return ArrayList<ImageData>
     */
    public ArrayList<ImageData> getImageList() {
        return imageList;
    }

    /**
     * Iterates through this imageList and finds the ImageData currImage,
     * Calls the tmAddTagWithImage in TagManager which instantiates tags and associates them with currImage, and obtaining a ArrayList of Tags.
     * Then it calls addTags method in ImageData and uses the ArrayList to add the Tag to the currImage's tagList, and associates the
     * tag with the ImageData if it hasn't already been associated.
     * The currImage in the imageList is finally returned.
     * This method is core to all tag adding activities in the GUI.
     *
     * @param currImage ImageData
     * @param tagNameList ArrayList<String></>
     * @return ImageData
     */
    public ImageData imAddTagWithImage(ImageData currImage, ArrayList<String> tagNameList) {
        for (ImageData i : imageList) {
            if (i.equals(currImage)) {
                ArrayList<Tag> newTags = MainContainer.getAppTagManager().tmAddTagWithImage(currImage, tagNameList);
                i.addTags(newTags);
                return i;
            }
        }
        return imAddTagNewImage(currImage, tagNameList);
    }

    /**
     * Iterates through this imageList and finds the ImageData currImage,
     * Calls the setImageTags in ImageData which sets the tagList given as the tagList of the currImage, which in turn
     * calls tmAddTagWithImage in TagManager to associate currImage with the tags in tagList if they hadn't been
     * associated already.
     * The currImage in the imageList is finally returned.
     * This method is core to all tag changing activities in the GUI.
     *
     * @param currImage ImageData
     * @param tagList ArrayList<String></>
     * @return ImageData
     */
    public ImageData imSetImageTags(ImageData currImage, ArrayList<Tag> tagList) {
        for(Tag t: currImage.getImageTags()){
            if(! tagList.contains(t)){
                t.getAssociatedImages().remove(currImage);
            }
        }
        ArrayList<Tag> finalList = new ArrayList<>();
        for (ImageData i : imageList) {
            if (i.equals(currImage)) {
                for(int j =0;j<tagList.size();j++){
                    String tagName = tagList.get(j).getTagName();
                    if(MainContainer.getAppTagManager().tagExists(tagName)){
                        finalList.add(MainContainer.getAppTagManager().getTag(tagName));
                    }else{
                        finalList.add(tagList.get(j));
                        MainContainer.getAppTagManager().getListOfTags().add(tagList.get(j));
                    }
                }
                i.setImageTags(finalList);
                return i;
            }
        }
        return null;
    }

    /**
     * Obtains an ImageData by creating a ImageData with location as its location, then iterates through the
     * imageList to see if there is an already existing ImageData, returning it if true, else returns the newly initialized
     * ImageData.
     *
     * @param location String
     * @return ImageData
     */
    public ImageData getImage(String location) {
        if (!imageList.isEmpty() && imageExists(location)) {
            for (ImageData i : imageList) {
                if (location.equals(i.getLocation())) {
                    return i;
                }
            }
        }
        return null;
    }

    /**
     * Checks for existence of an ImageData with specified location within imageList.
     * NOTE: uses for-loop instead of contains method for direct comparison of location of image file.
     *
     * @param location String of the absolute path of a image file
     * @return boolean whether this image file has an ImageData attached to it.
     */
    public boolean imageExists(String location) {
        if (!imageList.isEmpty()) {
            for (ImageData image : imageList) {
                if (location.equals(image.getLocation())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks for existence of currImage in imageList and calls imAddTagWithImage instead, otherwise it
     * adds currImage to imageList and calls tmAddTagWithImage in TagManager to associate currImage with the tags
     * in tagList if they hadn't been associated already.
     * Then the addTags method in ImageData is called to populate the tagList of the ImageData currImage.
     *
     * @param currImage ImageData
     * @param tagNameList ArrayList<String></>
     * @return ImageData
     */
    private ImageData imAddTagNewImage(ImageData currImage, ArrayList<String> tagNameList) {
        if(imageList.contains(currImage)){
            return imAddTagWithImage(currImage, tagNameList);
        }
        else{
            imageList.add(currImage);
            ArrayList<Tag> newTags = MainContainer.getAppTagManager().tmAddTagWithImage(currImage, tagNameList);
            currImage.addTags(newTags);
            return currImage;
        }
    }

    /**
     * Instantiates a Tag with tagName, adds it to tagList to avoid Exceptions when deleting the Tag,
     * Calls the removeTag in TagManager which removes the tag from the list of tags and returns the associated images of that tag.
     * which is taken and iterated through for each ImageData to call upon its deleteTags method to delete the tagList from itself.
     * This method can be used to compeletely delete a tag from all records and images.
     *
     * @param tagName String
     */
    public void removeTagFromAppAndImages(String tagName) {
        Tag tag = new Tag(tagName);
        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(tag);
        ArrayList<ImageData> associatedImages = MainContainer.getAppTagManager().removeTag(tagName);
        if (associatedImages != null) {
            CopyOnWriteArrayList<ImageData> newList = new CopyOnWriteArrayList<>();
            for (ImageData image : associatedImages) {
                newList.add(image);
            }
            for(ImageData im: newList){
                im.deleteTags(tagList);
            }
        }
    }
    // TODO: not sure why removeTagFromAppAndImages() need to create a new tag

    /**
     * Calls the deleteTags method in ImageData to remove the tag from the tagList of targetImage,
     * then calls the removeAssociatedImagesFromTags in TagManager which in turn calls the removeImage method
     * in Tag to remove association of the ImageData object from the Tag.
     *
     * @param tags ArrayList<Tag></>
     * @param targetImage ImageData
     */
    public void removeTagFromPic(ArrayList<Tag> tags, ImageData targetImage) {
        targetImage.deleteTags(tags);
    }
    // TODO: change docstring? removeAssociatedImagesFromTags is not used but deleteTag in Tag is

}
