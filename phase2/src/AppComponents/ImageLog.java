package AppComponents;

import AppGUI.MainContainer;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
/**
 * Helper class for ImageData and is therefore always initialized by and attached to a ImageData,
 * stores information regarding all the modifications of tags of the ImageData, and can be used to
 * revert back to tags of a certain time point
 */
public class ImageLog implements Serializable {

  // nameLog is a ordered hashmap with a timestamp of each tag modification mapped to the image name
  // at that timestamp.
  private LinkedHashMap<String, String> nameLog;
  // tagLog is a ordered hashmap with a timestamp of each tag modification mapped to the array list
  // of associated tag names prior to the tag modification.
  private LinkedHashMap<String, ArrayList<String>> tagLog;
  //    //for testing purposes only
  //    private String lastChangeTime;

  /**
   * Constructor of a new ImageLog object, is always called upon by an ImageData object. Initializes
   * the two logs, and adds the first entry recording the name, whether if the image file comes with
   * pre-existing tags, and their specific tag names.
   *
   * @param imagePath String location of the ImageData's imageLocation
   * @param name String original name of image file
   * @param tags possibly empty list of tag names the the ImageData is associated with.
   */
  public ImageLog(String imagePath, String name, ArrayList<String> tags) {
    nameLog = new LinkedHashMap<>();
    tagLog = new LinkedHashMap<>();
    Timestamp time = new Timestamp(System.currentTimeMillis());
    if (tags.size() == 0) {
      nameLog.put(time.toString(), "Initially named : " + name);
      tagLog.put(time.toString(), new ArrayList<String>());
      MainContainer.getMasterLog().innitImage(imagePath, "");
    } else {
      String temp = convertTagNameListToString(tags);
      nameLog.put(time.toString(), "Initially named : " + name + " Tags: " + temp);
      tagLog.put(time.toString(), tags);
      MainContainer.getMasterLog().innitImage(imagePath, temp);
    }
  }

  /**
   * Called upon whenever there is a new tag modification to the ImageData, takes the old and new
   * tagList of the ImageData before and after the tag modification, calls a helper method to
   * convert them to an ArrayLists of tagNames, and adds them to the two logs in the specified
   * format.
   *
   * @param location String location of the ImageData's imageLocation
   * @param coreName unchanging coreName of the ImageData
   * @param newTagArrayList new tagList of the ImageData
   * @param oldTagArrayList old tagList of the ImageData
   */
  public void addEntry(
      String location,
      String coreName,
      ArrayList<Tag> newTagArrayList,
      ArrayList<Tag> oldTagArrayList) {
    Timestamp time = new Timestamp(System.currentTimeMillis());
    ArrayList<String> revertTargetList = convertTagListToStringList(oldTagArrayList);
    tagLog.put(time.toString(), revertTargetList);
    String newName = convertTagListToString(newTagArrayList);
    String oldName = convertTagListToString(oldTagArrayList);
    nameLog.put(
        time.toString(), "Name: " + coreName + " Tags: [" + oldName + "] --> [" + newName + "]");
    if (!oldName.equals(newName)) {
      MainContainer.getMasterLog()
          .addEntry(
              time.toString(),
              "Image: " + location + " Tags: [" + oldName + "] --> [" + newName + "]");
    }
  }

  /**
   * Helper method that converts an ArrayList of tags to a corresponding ArrayList of its tagNames.
   *
   * @param tagArrayList ArrayList of tags to be converted
   * @return ArrayList<String> the ArrayList of tagNames
   */
  private ArrayList<String> convertTagListToStringList(ArrayList<Tag> tagArrayList) {
    ArrayList<String> result = new ArrayList<>();
    for (Tag tag : tagArrayList) {
      result.add(tag.getTagName());
    }
    return result;
  }

  /**
   * Helper method that converts an ArrayList of tags to a corresponding String concatenation its
   * tagNames.
   *
   * @param tagArrayList ArrayList of tags to be converted
   * @return String concatenation tagNames
   */
  private String convertTagListToString(ArrayList<Tag> tagArrayList) {
    String result = "";
    for (Tag tag : tagArrayList) {
      result += (", " + tag.getTagName());
    }
    if (result.equals("")) {
      return result;
    }
    return result.substring(2);
  }

  /**
   * Helper method that converts an ArrayList of String tagNameArrayList to a corresponding String
   * concatenation its tagNames.
   *
   * @param tagNameArrayList ArrayList of tags to be converted
   * @return String concatenation tagNames
   */
  private String convertTagNameListToString(ArrayList<String> tagNameArrayList) {
    String result = "";
    for (String tagName : tagNameArrayList) {
        if (!tagName.equals("")){
            result += (", " + tagName);}
    }
    if (result.equals("")) {
      return result;
    }
    return result.substring(2);
  }

  /**
   * Checks if the other is an ImageLog with the same nameLog as this ImageLog, nameLogs should be
   * unique.
   *
   * @param other object of comparison
   * @return boolean whether if the other Object is conceptually equal to this ImageLog
   */
  @Override
  public boolean equals(Object other) {
    return (other instanceof ImageLog) && nameLog.equals(((ImageLog) other).getNameLog());
  }

  /**
   * Getter for tagLog, used to revert back to a certain time point of tags for this ImageData.
   *
   * @return LinkedHashMap<String,ArrayList<String>> tagLog of this ImageLog
   */
  LinkedHashMap<String, ArrayList<String>> getTagLog() {

    return tagLog;
  }

  /**
   * Getter for nameLog, used to view the history of tag modification of this ImageData.
   *
   * @return LinkedHashMap<String,String> nmeLog of this ImageLog
   */
  LinkedHashMap<String, String> getNameLog() {

    return nameLog;
  }

  //    public void innit(String name, ArrayList<String> tags){
  //        Timestamp time = new Timestamp(System.currentTimeMillis());
  //        if(tags.size() == 0){
  //            nameLog.put(time.toString(), "Initially named : "+ name);
  //            tagLog.put(time.toString(), new ArrayList<String>());
  //        }
  //        else{
  //            String temp = convertTagNameListToString(tags);
  //            nameLog.put(time.toString(), "Initially named : " + name + ". Tags: " + temp);
  //            tagLog.put(time.toString(), tags);
  //        }
  //    }
}
