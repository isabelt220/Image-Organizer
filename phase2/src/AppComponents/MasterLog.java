package AppComponents;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Master log of all image and tag modifications.
 */
public class MasterLog implements Serializable {
    private LinkedHashMap<String, String> log = new LinkedHashMap<>();

    /**
     * Called by ImageManager TagManager, and the initialization of ImageData, Tag, and revertLog.
     * @param time String timestamp of tag and image modification
     * @param description String description of modification
     */
    public void addEntry(String time, String description){
        log.put(time, description);
    }

    /**
     * Called to add log entry when a new tag is initialized
     * @param tagName String name of new Tag
     */
    public void innitTag(String tagName){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        log.put(time.toString(), "New Tag: " + tagName);
    }

    /**
     * Called to add log entry when a new image is imported.
     *
     *
     * @param imagePath String path of the image file
     */
    public void innitImage(String imagePath, String currentTagNames){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        log.put(time.toString(), "Found Image: " + imagePath + "  Tags: [" + currentTagNames + "]");
    }

    public void addedImageToTag(String tagName, String imageName){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        addEntry(time.toString(), "Tag "+tagName +" was added to "+imageName);
    }

    public void deletedImageFromTag(String tagName, String imageName){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        addEntry(time.toString(), "Tag "+ tagName + " was deleted from " +imageName);
    }

    public void deleteTag(String tagName){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        addEntry(time.toString(), "Tag " + tagName +" was deleted from all records.");
    }

    /**
     * Public getter for master log.
     * Used to display master log.
     *
     * @return this log
     */
    public LinkedHashMap<String, String> getLog(){

        return log;
    }



}
