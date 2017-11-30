package AppComponents;

import AppGUI.MainContainer;
import java.io.File;
import java.io.Serializable;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Although it is not a image file, ImageData stores the location of an image file, a list of
 * associated tags, and a log of all tag manipulation by the user. In the direct sense, an ImageData
 * object it is attached to the image file whose location it stores, and includes getters, setters,
 * and other methods that aid in the manipulation of the image file.
 */
public class ImageData implements Serializable {

  // ImageLocation is the helper class of ImageData, it stores the location, and its various
  // substring such as the name and type of the image file.
  private ImageLocation imageLocation;
  // String coreName is the substring of location after path and before extension,it is set in the
  // constructor and does not change. eg. Christmas
  private String coreName;
  // tagList is the ArrayList of tags that are associated with the image
  private ArrayList<Tag> tagList = new ArrayList<>();
  // ImageLog is the helper class of ImageData and consists of two LinkedHashMaps, one as a record
  // of all tag manipulation, and one used to revert back to a set of old tags.
  private ImageLog imageLog;

  /**
   * Sets up an ImageData Object, initializes the value name, coreName, tagList, ImageLocation, and
   * ImageLog.
   *
   * @param location String location of the image file this ImageData is attached to. eg.
   *     C:/Photos/SummerVacation/NotLeavingTheHouseForThreeMonthsStraight.jpg
   */
  public ImageData(String location) {
    this.imageLocation = new ImageLocation(location);
    coreName = this.imageLocation.analyzeNameForCore();
    ArrayList<String> temp = this.imageLocation.analyzeNameForTags();
    MainContainer.getAppImageManager().imAddTagWithImage(this, temp);
    imageLog = new ImageLog(imageLocation.getLocation(), coreName, temp);
  }

  /**
   * Iterates through the tags in dTags, checks for existence in tagList, and deletes it if it
   * exists, removing this ImageData from association of deleted tags. Calls setImageTags to reset
   * the name of this ImageData according to current Tags.
   *
   * @param dTags ArrayList<Tag></> to delete from the tagList
   */
  public void deleteTags(ArrayList<Tag> dTags) {
    ArrayList<Tag> modList = new ArrayList<>(tagList);
    modList.removeAll(dTags);
    if (this.imageLog == null) {
      this.imageLog =
          new ImageLog(
              imageLocation.getLocation(), coreName, this.imageLocation.analyzeNameForTags());
      this.imageLog.addEntry(imageLocation.getLocation(), coreName, modList, tagList);
    }
    this.imageLog.addEntry(imageLocation.getLocation(), coreName, modList, tagList);
    for (Tag dTag : dTags) {
      if ((tagList.contains(dTag))) {
        int i = tagList.indexOf(dTag);
        tagList.get(i).removeImage(this);
        tagList.remove(tagList.get(i));
      }
    }
    setImageTags(tagList);
  }

  /**
   * Deletes all tags from tagList, and removes association of this image from all Tags it was
   * associated by NOT USED BUT PLEASE DO NOT DELETE
   */
  public void deleteAllTags() {
    for (Tag tag : tagList) {
      tag.removeImage(this);
    }
    tagList.clear();
    setImageTags(tagList);
  }

  /**
   * Iterates through the tags in newTags, checks for existence in tagList, and adds it if it
   * doesn't exists, adding this ImageData to association of new tags. Calls setImageTags to reset
   * the name of this ImageData according to current Tags.
   *
   * @param newTags ArrayList<Tag> to add to tagList
   */
  void addTags(ArrayList<Tag> newTags) {

    ArrayList<Tag> modList = new ArrayList<>(tagList);
    modList.addAll(newTags);
    if (this.imageLog == null) {
      this.imageLog =
          new ImageLog(
              imageLocation.getLocation(), coreName, this.imageLocation.analyzeNameForTags());
      this.imageLog.addEntry(imageLocation.getLocation(), coreName, modList, tagList);
    }
    this.imageLog.addEntry(imageLocation.getLocation(), coreName, modList, tagList);
    for (Tag newTag : newTags) {
      if (!(tagList.contains(newTag))) {
        tagList.add(newTag);
        newTag.addImage(this);
      }
    }
    setImageTags(tagList);
  }

  /**
   * Takes the coreName of this ImageData, and iterates through tags names to concatenate a string
   * according to specified format. Adds tags to tagList if they are not in there already Calls
   * tmAddTagWithImage in TagManager to add tag and association of this ImageData with target tag.
   * Calls setImageName to change the name to concatenated string.
   *
   * @param tags ArrayList<Tag> which will become the new tagList of this ImageData
   */
  void setImageTags(ArrayList<Tag> tags) {
    if (!(tagList.size() == tags.size())) {
      if (this.imageLog == null) {
        this.imageLog =
            new ImageLog(
                imageLocation.getLocation(), coreName, this.imageLocation.analyzeNameForTags());
        this.imageLog.addEntry(imageLocation.getLocation(), coreName, tags, tagList);
      }
      this.imageLog.addEntry(imageLocation.getLocation(), coreName, tags, tagList);
    }
    StringBuilder compressedTags = new StringBuilder(coreName);
    ArrayList<String> stringVer = new ArrayList<>();
    tagList = tags;
    for (Tag tag : tags) {
      stringVer.add(tag.getTagName());
      compressedTags.append(" @" + tag.getTagName());
    }
    MainContainer.getAppTagManager().tmAddTagWithImage(this, stringVer);
    setImageName(compressedTags.toString());
  }

  /**
   * Creates two files each with the location of oldName and newName, and uses the renameTo() method
   * in java File to rename the image file that this ImageData is attached to. Modifies the name and
   * location of the ImageData in accordance with the image file this ImageData is attached to.
   *
   * @param newName String
   */
  void setImageName(String newName) {
    File oldName = new File(imageLocation.getLocation());
    File addedName = new File(imageLocation.getPath() + newName + "." + imageLocation.getType());
    oldName.renameTo(addedName);
    imageLocation.setName(newName);
  }

  /**
   * A version of hasTag for multiple tags, checks if the ALL tags with tagNames in the ArrayList is
   * associated with this ImageData, used for multiple tag search and manipulation(addition or
   * deletion).
   *
   * @param tags ArrayList<String> of tag names to check association for.
   * @return boolean whether or not this ImageData is all tags with the tag name of elements in
   *     ArrayList<String> tags.
   */
  public boolean containsTags(ArrayList<String> tags) {
    if (tags.size() == 0) {
      return false;
    }
    for (String tag : tags) {
      if (!this.hasTag(tag)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if this ImageData is associated with a tag of name tagName, double checks if the tagList
   * contains the target tag and if this ImageData is in the associated image list of this
   * ImageData.
   *
   * @param tagName String
   * @return boolean
   */
  public boolean hasTag(String tagName) {
    for (Tag target : MainContainer.getAppTagManager().getListOfTags()) {
      if (target.getTagName().equals(tagName)) {
        return target.getAssociatedImages().contains(this);
      }
    }
    return false;
  }

  /**
   * Overrides equals method in Object, check if other Object is an ImageData and if it has the same
   * location as this ImageData object (location of ImageData objects should always be unique).
   *
   * @param other Object
   * @return boolean
   */
  @Override
  public boolean equals(Object other) {
    return other instanceof ImageData
        && imageLocation.equals(((ImageData) other).getImageLocation());
    //                && imageLog.equals(((ImageData) other).getImageLog())
  }

  /**
   * Getter for nameLog, returns it in the form of a LinkedHashMap, the descriptive log of
   * manipulation of change as value and timestamp as key. All timestamps will also exist in
   * imageLog, mapped to the ArrayList of tagNames that the ImageData was associated with prior to
   * the manipulation Is generally called upon by classes that implements the visual display of a
   * history of changes made to an ImageData.
   *
   * @return LinkedHashMap<String,String> String timestamp to String description of manipulation.
   */
  public LinkedHashMap<String, String> getNameLog() {

    return imageLog.getNameLog();
  }

  /**
   * Getter for imageLog, the complete history of all tag modifications of this ImageData. Is
   * generally called upon by classes that modifies the visual display of a history of changes made
   * to an ImageData.
   *
   * @return ImageLog of this ImageData
   */
  public ImageLog getImageLog() {

    return imageLog;
  }

  /**
   * Getter for imageLog, returns it in the form of a LinkedHashMap, a log of an ArrayList of
   * tagNames that this ImageData is associated as value to a timestamp as key. Note the ArrayList
   * is the tag name list of the ImageData PRIOR to a certain tag change at the timestamp. All
   * timestamps will also exist in imageLog, mapped to the visual description of the tag
   * manipulation. Is generally called upon to revert back to a certain set of tags.
   *
   * @return LinkedHashMap<String, ArrayList<String>> String timestamp mapped to tag name list.
   */
  public LinkedHashMap<String, ArrayList<String>> getTagLog() {

    return imageLog.getTagLog();
  }

  /**
   * Getter for name, returns it in the form of String, is used to obtain the current name of the
   * ImageData.
   *
   * @return String current name, including coreName and all tags it is attached to.
   */
  public String getName() {

    return imageLocation.getName();
  }

  /**
   * Getter for coreName, returns it in the form of String, is used to obtain the non-changing
   * coreName of the ImageData. Can be used generate new names with tag addition and deletion.
   *
   * @return String coreName
   */
  public String getCoreName() {

    return coreName;
  }

  /**
   * Getter for location, returns it in the form of String, can be used to create and manipulate
   * image files that this ImageData is attached to.
   *
   * @return String location of the image file that this ImageData manipulates
   */
  public String getLocation() {

    return imageLocation.getLocation();
  }

  /**
   * Getter for tagList, returns it in the form of ArrayList<Tag>, can be used to iterate through
   * current tags of image.
   *
   * @return ArrayList<Tag> current associated tags.
   */
  public ArrayList<Tag> getImageTags() {

    return tagList;
  }

  /**
   * Getter for ImageLocation, used to obtain more specific information regarding the location,
   * name, path, type...etc. of this ImageData.
   *
   * @return ImageLocation helper class that stores location, name, path, type related data of this
   *     ImageData.
   */
  public ImageLocation getImageLocation() {

    return imageLocation;
  }

  //
  //
  ////    /**
  ////     * Getter for the lastChangeTime of the ImageData name.
  ////     * FOR TESTING PURPOSES PLEASE DO NOT DELETE
  ////     * @return String
  ////     */
  ////    public String getLastChangeTime() {
  ////        return lastChangeTime;
  ////    }
  //

  //
  //    /**
  //     *Solely for Testing Purposes
  //     */
  //    public String printLog(){
  //        String log = "";
  //        for (Map.Entry<String, String> entry : nameLog.entrySet()){
  //            String key = entry.getKey();
  //            String value = entry.getValue();
  //            log += key + "---" + value;
  //            log += System.getProperty("line.separator");
  //        }
  //        return log;
  //
  //    }
  //
  //

}
