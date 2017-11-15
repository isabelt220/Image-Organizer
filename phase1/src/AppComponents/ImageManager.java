package AppComponents;

import java.util.ArrayList;

/*
Keeps track of all images and the info for each image
*/
public class ImageManager {

    private ArrayList<ImageData> imageList;

    public ImageManager() {
        imageList = new ArrayList<>();
    }

    public void addImage(ImageData newImage) {
        imageList.add(newImage);
    }

    public ArrayList<ImageData> getImageList() {
        return imageList;
    }
}
