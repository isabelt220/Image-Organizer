package AppComponents;

import AppGUI.MainContainer;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/*
Keeps track of all images and the info for each image
*/
public class ImageManager {

    // Initializes an empty ArrayList of ImageData object that this ImageManager contains
    private static ArrayList<ImageData> imageList = new ArrayList<>();

    /**
     * Constructor for this ImageManager, initializes the serialization of ImageData by creating a imageConfig.txt
     * Used to save changes to tags of ImageData objects that it contains.
     */
    public ImageManager() {
        Path currentRelativePath = Paths.get("");
        String filePath = currentRelativePath.toAbsolutePath().toString();
        filePath += "/imageConfig.txt";

        File file = new File(filePath);
        if (file.exists() && file.length() != 0) {
            readImagesFromFile(filePath);
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Takes a String filePath and iterates through all the image files under the directory of filePath, creating an
     * ImageData object for each file and adding th ImageData to this imageList.
     *
     * @param filePath String
     */
    private void readImagesFromFile(String filePath) {
        try {
            FileInputStream is = new FileInputStream(filePath);
            ObjectInputStream os = new ObjectInputStream(is);

            int num = os.readInt();

            for (int i = 0; i < num; i++) {
                ImageData image = (ImageData) os.readObject();
                imageList.add(image);
            }

            os.close();
            is.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Contrast to readImagesFromFile, takes ImageData from imaeList and writes it in the filePath.
     *
     * @param filePath String
     */
    public void saveImagesToFile(String filePath) {
        try {
            FileOutputStream fs = new FileOutputStream(filePath);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeInt(imageList.size());
            for (ImageData image : imageList) {
                os.writeObject(image);
            }
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes a newImage ImageData, checks for existence and adds it to this imageList.
     *
     * @param newImage ImageData
     */
    void addImage(ImageData newImage) {

        if (!(imageList.contains(newImage))){
            imageList.add(newImage);}
    }

    /**
     * Getter for imageList, both the method and the instances are static so that it can be called before object initialization
     * to avoid Exceptions, this method can be used to iterate through all ImageData objects created.
     *
     * @return ArrayList<ImageData>
     */
    static ArrayList<ImageData> getImageList() {
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
    public static ImageData getImage(String location) {
        ImageData temp = new ImageData(location);
        for (ImageData i : imageList) {
            if (temp.equals(i)) {
                return i;
            }
        }
        return temp;
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
            return currImage;}
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
            for (ImageData image : associatedImages) {
                image.deleteTags(tagList);
            }
        }
    }


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


}
