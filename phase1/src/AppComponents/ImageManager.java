package AppComponents;

import java.util.ArrayList;

/*
Keeps track of all images and the info for each image
*/
public class ImageManager {

    private static ArrayList<ImageData> imageList;

    public ImageManager() {
        imageList = new ArrayList<>();
    }

    public void addImage(ImageData newImage) {
        imageList.add(newImage);
    }

    public static ArrayList<ImageData> getImageList() {
        return imageList;
    }

    public static void imAddTagWithImage(ImageData currImage, ArrayList<String> tagNameList) {
        ArrayList<Tag> newTags = TagManager.tmAddTagWithImage(tagNameList);
        currImage.addTags(newTags);
    }
}
