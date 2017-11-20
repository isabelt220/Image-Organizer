package AppComponents;

import AppGUI.MainContainer;
import AppGUI.PopUpWindow.DialogBox;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

public class AppDataSerializer {

    /**
     * Creates AppDataConfig.txt file for reading and writing ArrayLists of
     * Tags and Images from TagManager and ImageManager when the program is
     * ran for the first time or if a previously existing file has been
     * deleted. Reads AppDataConfig.txt if the file exists and contains data.
     */
    public AppDataSerializer() {
            Path currentRelativePath = Paths.get("");
            String filePath = currentRelativePath.toAbsolutePath().toString();
            filePath += "/AppDataConfig.txt";

            File file = new File(filePath);
            if (file.exists() && file.length() != 0) {
                readDataFromFile(filePath);
            } else {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    /**
     * Reads ArrayList of Tags and ArrayList of Images from AppDataConfig.txt
     * and sets the lists as listOfTags in TagManager and imageList in ImageManager
     * respectively.
     *
     * @param filePath the file path of AppDataConfig.txt
     */
    private void readDataFromFile(String filePath) {
        try {
            FileInputStream is = new FileInputStream(filePath);
            ObjectInputStream os = new ObjectInputStream(is);

            ArrayList<Tag> tagManagerListOfTags = (ArrayList<Tag>) os.readObject();
            MainContainer.getAppTagManager().setListOfTags(tagManagerListOfTags);

            ArrayList<ImageData> imageManagerListOfImages = (ArrayList<ImageData>) os.readObject();
            MainContainer.getAppImageManager().setImageList(imageManagerListOfImages);

            os.close();
            is.close();

        } catch (Exception e) {
            DialogBox warning = new DialogBox("Warning", "Failed to read the save file.");
            warning.display();
        }
    }

    /**
     * Saves listOfTags from TagManager and imageList from ImageManager to
     * the file AppDataConfig.txt.
     *
     * @param filePath the file path of AppDataConfig.txt
     */
    public void saveDataToFile(String filePath) {
        try {
            FileOutputStream fs = new FileOutputStream(filePath);
            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeObject(MainContainer.getAppTagManager().getListOfTags());
            os.writeObject(MainContainer.getAppImageManager().getImageList());

            os.close();

        } catch (IOException e) {
            DialogBox warning = new DialogBox("Warning", "Failed to save");
            warning.display();
        }
    }

    }