package AppComponents;

import AppGUI.MainContainer;
import AppGUI.PopUpWindow.DialogBox;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

public class AppDataSerializer {

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

    void readDataFromFile(String filePath) {
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