package AppComponents;

import java.util.ArrayList;
import java.util.HashSet;

/*
Keeps track of all images and the info for each image
*/
public class ImageManager {

    private ArrayList<ImageInfo> imageList;

    public ImageManager() {
        imageList = new ArrayList<>();
    }

    public void addImage(ImageInfo newImage) {
        imageList.add(newImage);
    }

    public ArrayList<ImageInfo> getImageList() {
        return imageList;
    }
}
