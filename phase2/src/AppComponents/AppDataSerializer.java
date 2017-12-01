package AppComponents;

import AppGUI.MainContainer;
import AppGUI.PopUpWindow.DialogBox;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Provides serialization for creation and manipulation of tags and images, is called upon when the
 * application is opened and closed.
 */
public class AppDataSerializer {

    /**
     * Creates AppDataConfig.txt file for reading and writing ArrayLists of Tags and Images from
     * TagManager and ImageManager when the program is ran for the first time or if a previously
     * existing file has been deleted. Reads AppDataConfig.txt if the file exists and contains data.
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
     * Reads ArrayList of Tags and ArrayList of Images from AppDataConfig.txt and sets the lists as
     * listOfTags in TagManager and imageList in ImageManager respectively.
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

            LinkedHashMap<String, String> masterLogOfChanges =
                    (LinkedHashMap<String, String>) os.readObject();
            MainContainer.getMasterLog().setLog(masterLogOfChanges);

            os.close();
            is.close();

        } catch (FileNotFoundException e) {
            DialogBox fileNotFound = new DialogBox("Error", "Cannot find file AppConfig.txt.");
            fileNotFound.display();
        } catch (IOException e) {
            DialogBox failToRead = new DialogBox("Error", "Failed to read the save file.");
            failToRead.display();
        } catch (ClassNotFoundException e) {
            DialogBox classNotFound = new DialogBox("Error", "Missing program class.");
            classNotFound.display();
        }
    }

    /**
     * Saves listOfTags from TagManager and imageList from ImageManager to the file AppDataConfig.txt.
     */
    public void saveDataToFile() {
        try {
            FileOutputStream fs = new FileOutputStream("AppDataConfig.txt");
            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeObject(MainContainer.getAppTagManager().getListOfTags());
            os.writeObject(MainContainer.getAppImageManager().getImageList());
            os.writeObject(MainContainer.getMasterLog().getLog());

            os.close();
            fs.close();

        } catch (IOException e) {
            DialogBox warning = new DialogBox("Warning", "Failed to save");
            warning.display();
        }
    }
}
