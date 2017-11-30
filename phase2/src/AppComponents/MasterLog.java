package AppComponents;

import java.io.Serializable;
import java.sql.Timestamp;

import java.util.LinkedHashMap;

/**
 * Master log of all image and tag modifications.
 */
public class MasterLog implements Serializable {

    /**
     * Ordered log that will keep track of all tag and image modifications
     * Maps key timestamp to value description.
     */
    private LinkedHashMap<String, String> log = new LinkedHashMap<>();

    /**
     * Called by ImageManager TagManager, and the initialization of ImageData, Tag, and revertLog.
     * @param time String timestamp of tag and image modification
     * @param description String description of modification
     */
     void addEntry(String time, String description) {
        log.put(time, description);
    }

    /**
     * Called to add log entry when a new tag is initialized
     * @param tagName String name of new Tag
     */
     void innitTag(String tagName) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        log.put(time.toString(), "New Tag: " + tagName);
    }

    /**
     * Called to add log entry with appropriate description when a new image is imported.
     *
     * @param currentTagNames String the tags the image contains (could be empty) when it is first imported
     * @param imagePath String path of the image file
     */
     void innitImage(String imagePath, String currentTagNames) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        log.put(time.toString(), "Found Image: " + imagePath + "  Tags: [" + currentTagNames + "]");
    }

    /**
     * Called to add log entry with appropriate description when an image was associated with the tag.
     *
     * @param tagName String name of tag that was added to the image
     * @param imageName String path of the image file
     */
     void addedImageToTag(String tagName, String imageName) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        addEntry(time.toString(), "Tag "+tagName +" was added to "+imageName);
    }

    /**
     * Called to add log entry with appropriate description when an image was un-associated with the tag.
     *
     * @param tagName String name of tag that was added to the image
     * @param imageName String path of the image file
     */
     void deletedImageFromTag(String tagName, String imageName) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        addEntry(time.toString(), "Tag "+ tagName + " was deleted from " +imageName);
    }

    /**
     * Called to add log entry with appropriate description when an tag was deleted from TagManager.
     *
     * @param tagName String name of tag that was added to the image
     */
    void deleteTag(String tagName) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        addEntry(time.toString(), "Tag " + tagName +" was deleted from all records.");
    }

    public void setLog(LinkedHashMap<String, String> masterLog) {
        log = masterLog;
    }

    /**
     * Public getter for master log.
     * Used to display master log.
     *
     * @return this log
     */
    public LinkedHashMap<String, String> getLog() {
        return log;
    }



}
