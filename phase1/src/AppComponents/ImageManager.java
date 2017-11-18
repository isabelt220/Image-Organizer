package AppComponents;

import java.util.ArrayList;

/*
Keeps track of all images and the info for each image
*/
public class ImageManager {

    private static ArrayList<ImageData> imageList = new ArrayList<>();

    public ImageManager() {
    }

    public void addImage(ImageData newImage) {
        imageList.add(newImage);
    }

    public ArrayList<ImageData> getImageList() {
        return imageList;
    }

    public void imAddTagWithImage(ImageData currImage, ArrayList<String> tagNameList) {
        for (ImageData i : imageList) {
            if (i.equals(currImage)) {
                ArrayList<Tag> newTags = TagManager.tmAddTagWithImage(tagNameList);
                i.setImageTags(newTags);
                return;
            }
        }

        imAddTagNewImage(currImage, tagNameList);
    }

    public boolean ImageExist(String location){
        ImageData temp = new ImageData(location);
        return imageList.contains(temp);

    }
    public ImageData getImage(String location){
        ImageData temp = new ImageData(location);
        for(ImageData i: imageList){
            if(temp.equals(i)){
                return i;
            }
        }
        return null;
    }

    private void imAddTagNewImage(ImageData currImage, ArrayList<String> tagNameList) {
        imageList.add(currImage);
        ArrayList<Tag> newTags = TagManager.tmAddTagWithImage(tagNameList);
        currImage.addTags(newTags);
    }
}
