package AppComponents;

import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/*
Keeps track of all images and the info for each image
*/
public class ImageManager {

    private static ArrayList<ImageData> imageList = new ArrayList<>();

    public ImageManager() {
        Path currentRelativePath = Paths.get("");
        String filePath = currentRelativePath.toAbsolutePath().toString();
        filePath += "/imageConfig.txt";

        File file = new File(filePath);
        if (file.exists() && file.length() != 0) {
            readImagesFromFile(filePath);
        } else {
            try{
                file.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void readImagesFromFile(String filePath) {
        try {
            FileInputStream is = new FileInputStream(filePath);
            ObjectInputStream os = new ObjectInputStream(is);

            int num = os.readInt();

            for (int i=0; i < num; i++) {
                ImageData image = (ImageData) os.readObject();
                imageList.add(image);
            }

            os.close();
            is.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveTagsToFile(String filePath) {
        try {
            FileOutputStream fs = new FileOutputStream(filePath);
            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeInt(imageList.size());

            for (ImageData image : imageList) {
                os.writeObject(image);
            }

            os.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void addImage(ImageData newImage) {
        imageList.add(newImage);
    }

    public ArrayList<ImageData> getImageList() {
        return imageList;
    }

    public ImageData imAddTagWithImage(ImageData currImage, ArrayList<String> tagNameList) {
        for (ImageData i : imageList) {
            if (i.equals(currImage)) {
                ArrayList<Tag> newTags = TagManager.tmAddTagWithImage(tagNameList);
                i.setImageTags(newTags);
                return i;
            }
        }

        return imAddTagNewImage(currImage, tagNameList);
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

    private ImageData imAddTagNewImage(ImageData currImage, ArrayList<String> tagNameList) {
        imageList.add(currImage);
        ArrayList<Tag> newTags = TagManager.tmAddTagWithImage(tagNameList);
        currImage.addTags(newTags);
        return currImage;
    }
}
