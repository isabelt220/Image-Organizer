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

public class ImageData implements Serializable{

    // String Location is the complete path of the file. eg. C://User/phase1/Christmas.png
    private ImageLocation imageLocation;
    //String coreName is the substring of location after path and before extension,it is set in the constructor and does not change. eg. Christmas
    private String coreName;
    //tagList is the ArrayList of tags that are associated with the image
    private ArrayList<Tag> tagList = new ArrayList<>();
    //nameLog is a ordered hashmap with a timestamp of each tag modification paired to the image name at that timestamp.
    private ImageLog imageLog;

    /**
     * Sets up an ImageData Object, initializes the value name, coreName, type, tagList, and adds the initial entry to
     * nameLog.
     * @param location String
     */
    public ImageData(String location) {
        this.imageLocation = new ImageLocation(location);
        coreName = this.imageLocation.analyzeNameForCore();
        ArrayList<String> temp = this.imageLocation.analyzeNameForTags();
        MainContainer.getAppImageManager().imAddTagWithImage(this, temp);
        imageLog = new ImageLog(coreName, temp);

    }


    /**
     * Getter for nameLog, returns it in the form of a LinkedHashMap, is generally called upon by classes that implements
     * the display of a history of changes made to an ImageData.
     * @return LinkedHashMap<String,String>
     */
    public LinkedHashMap<String, String> getNameLog(){
        return imageLog.getNameLog();
    }

    /**
     * Getter for name, returns it in the form of String, is used to obtain the current name of the ImageData.
     * @return String
     */
    public String getName(){return imageLocation.getName();}

    /**
     * Getter for coreName, returns it in the form of String, is used to obtain the non-changing coreName of the ImageData.
     * Can be used generate new names with tag addition and deletion.
     * @return String
     */
    public String getCoreName(){return coreName;}
//
////    /**
////     *Solely for Testing Purposes
////     */
////    public String printLog(){
////        String log = "";
////        for (Map.Entry<String, String> entry : nameLog.entrySet()){
////            String key = entry.getKey();
////            String value = entry.getValue();
////            log += key + "---" + value;
////            log += System.getProperty("line.separator");
////        }
////        return log;
////
////    }
//
//
    /**
     * Iterates through the tags in dTags, checks for existence in tagList, and deletes it if it exists, removing this
     * ImageData from association of deleted tags.
     * Calls setImageTags to reset the name of this ImageData according to current Tags.
     * @param dTags ArrayList<Tag></>
     */
   public void deleteTags(ArrayList<Tag> dTags){
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
     * Deletes all tags from tagList, and removes association of this image from all Tags it was associated by
     * NOT USED BUT PLEASE DO NOT DELETE
     * May be used for additional functions in phase2
     */
    public void deleteAllTags(){
        for(Tag tag : tagList){
            tag.removeImage(this);
        }
        tagList.clear();
        setImageTags(tagList);
    }

    /**
     * Iterates through the tags in newTags, checks for existence in tagList, and adds it if it doesn't exists, adding this
     * ImageData to association of new tags.
     * Calls setImageTags to reset the name of this ImageData according to current Tags.
     * @param newTags ArrayList<Tag>
     */
    void addTags(ArrayList<Tag> newTags){
        for (Tag newTag : newTags) {
            if (!(tagList.contains(newTag))) {
                tagList.add(newTag);
                newTag.addImage(this);
            }
        }
        setImageTags(tagList);
    }


    /**
     * Takes the coreName of this ImageData, and iterates through tags to concatenate a string according to specified format.
     * Adds tags to tagList if they are not in there already
     * Calls tmAddTagWithImage in TagManager to add tag and association of this ImageData with said tag.
     * Calls setImageName to change the name to concatenated string.
     * @param tags ArrayList<Tag>
     */
    public void setImageTags(ArrayList<Tag> tags) {
        StringBuilder compressedTags = new StringBuilder(coreName);
        ArrayList<String> stringVer= new ArrayList<>();
        if (this.imageLog == null){
            this.imageLog = new ImageLog(coreName, this.imageLocation.analyzeNameForTags());
            this.imageLog.addEntry(coreName, tags, tagList);
        }
        this.imageLog.addEntry(coreName, tags, tagList);
        tagList = tags;
        for (Tag tag : tags) {
            stringVer.add(tag.getTagName());
            compressedTags.append(" @" + tag.getTagName());
        }
        MainContainer.getAppTagManager().tmAddTagWithImage(this, stringVer);
        setImageName(compressedTags.toString());

    }

    /**
     * Adds an entry in the nameLog with the current timestamp mapped to a string in the format of
     * tag change: oldName --> newName
     * Creates two files each with the location of oldName and newName, and uses the renameTo() method in
     * java File to rename the image file that this ImageData is attached to.
     * Modifies the name and location of the ImageData in accordance with the image file this ImageData is attached to.
     * @param newName String
     */
    private void setImageName(String newName) {
            File oldName = new File(imageLocation.getLocation());
            File addedName = new File(imageLocation.getPath()+newName+"."+imageLocation.getType());
            oldName.renameTo(addedName);
            imageLocation.setName(newName);

}

    /**
     * Getter for location, returns it in the form of String, can be used to create and manipulate image files
     * that this ImageData is attached to.
     * @return String
     */
    public String getLocation() {
        return imageLocation.getLocation();
    }


    /**
     * Getter for tagList, returns it in the form of ArrayList<Tag>, can be used to iterate through current tags of image.
     * NOT USED BUT PLEASE DON'T DELETE
     * @return ArrayList<Tag>
     */
    public ArrayList<Tag> getImageTags(){
        return tagList;
    }

    /**
     * Checks if this ImageData is associated with a tag of name tagName,
     * double checks if the tagList contains the target tag and if this ImageData is in the associated image list of this
     * ImageData.
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

    public ImageLocation getImageLocation() {
        return imageLocation;
    }

    public ImageLog getImageLog() {
        return imageLog;
    }

    /**
     * Overrides equals method in Object, check if other Object is an ImageData and if it has the same location
     * as this ImageData object (location of ImageData objects should always be unique).
     * @param other Object
     * @return boolean
     */
    @Override
    public boolean equals(Object other){
        return other instanceof ImageData && imageLocation.equals(((ImageData) other).getImageLocation())
//                && imageLog.equals(((ImageData) other).getImageLog())
;
    }

    public boolean containsTags(ArrayList<String> tags){
        if (tags.size() == 0){
            return false;
        }
        for (String tag: tags){
            if (!this.hasTag(tag)){
                return false;
            }
        }
        return true;
    }
}
//public class ImageData implements Serializable{
//
//    // String Location is the complete path of the file. eg. C://User/phase1/Christmas.png
//    private String location = "";
//    // String path is a substring of location and is the path of the image file that this ImageData is attached to. eg. C://User/phase1/
//    private String path;
//    // String path is a substring of location and is the type of the image file that this ImageData is attached to. eg. .png, .img, .jpg
//    private String type;
//    //String name is the substring of location after path and before extension, it can be changed with the editing of tags. eg. Christmas @2017 @Mistletoe
//    private String name = "";
//    //String coreName is the substring of location after path and before extension,it is set in the constructor and does not change. eg. Christmas
//    private String coreName = "";
//    //tagList is the ArrayList of tags that are associated with the image
//    private ArrayList<Tag> tagList;
//    //nameLog is a ordered hashmap with a timestamp of each tag modification paired to the image name at that timestamp.
//    private LinkedHashMap<String, String> nameLog = new LinkedHashMap<>();
//    //for testing purposes only
//    private String lastChangeTime;
//
//    /**
//     * Sets up an ImageData Object, initializes the value name, coreName, type, tagList, and adds the initial entry to
//     * nameLog.
//     * @param location String
//     */
//    public ImageData(String location) {
//        this.location = location;
//        File imageFile = new File(location);
//        String temp = imageFile.getName();
//        name = temp.substring(0,temp.lastIndexOf("."));
//        coreName = analyzeNameForTags(name);
//        int x = location.lastIndexOf(File.separator)+1;
//        path = location.substring(0,x);
//        int i = imageFile.getName().lastIndexOf('.');
//        type = imageFile.getName().substring(i+1);
//        Timestamp time = new Timestamp(System.currentTimeMillis());
//        nameLog.put(time.toString(), "Initially named : "+ name);
//        tagList = new ArrayList<>();
//    }
//
//    /**
//     * Used when a ImageData is first created, reads through the name of the image, checking for "@"
//     * symbols to find tags, then records these tags.
//     * @param nameOfImage String name of the initial image
//     * @return the coreName of the Image
//     */
//    public String analyzeNameForTags(String nameOfImage){
//        if (!(nameOfImage.contains("@"))){
//            return nameOfImage;
//        }
//        else{
//            int x = nameOfImage.indexOf(" @");
//            String core = nameOfImage.substring(0, x);
//            String unsortedTags = nameOfImage.substring(x);
//            String[] tags = unsortedTags.split(" @");
//            ArrayList<String> tagNames= new ArrayList<String>(Arrays.asList(tags));
//            MainContainer.getAppTagManager().tmAddTagWithImage(this, tagNames);
//            return core;
//        }
//
//    }
//
//    /**
//     * Getter for nameLog, returns it in the form of a LinkedHashMap, is generally called upon by classes that implements
//     * the display of a history of changes made to an ImageData.
//     * @return LinkedHashMap<String,String>
//     */
//    public LinkedHashMap<String, String> getNameLog(){return nameLog;}
//
//    /**
//     * Getter for name, returns it in the form of String, is used to obtain the current name of the ImageData.
//     * @return String
//     */
//    public String getName(){return name;}
//
//
//
//    /**
//     * Getter for coreName, returns it in the form of String, is used to obtain the non-changing coreName of the ImageData.
//     * Can be used generate new names with tag addition and deletion.
//     * @return String
//     */
//    public String getCoreName(){return coreName;}
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
//    /**
//     * Iterates through the tags in dTags, checks for existence in tagList, and deletes it if it exists, removing this
//     * ImageData from association of deleted tags.
//     * Calls setImageTags to reset the name of this ImageData according to current Tags.
//     * @param dTags ArrayList<Tag></>
//     */
//    public void deleteTags(ArrayList<Tag> dTags){
//        for (Tag dTag : dTags) {
//            if ((tagList.contains(dTag))) {
//                int i = tagList.indexOf(dTag);
//                tagList.get(i).removeImage(this);
//                tagList.remove(tagList.get(i));
//            }
//        }
//        setImageTags(tagList);
//
//    }
//
//    public void deleteTagsByString(ArrayList<String> dTags){
//        for (String dtag : dTags){
//            for (Tag tag: tagList){
//                if (tag.getTagName().equals(dtag)){
//                    tag.removeImage(this);
//                    tagList.remove(tag);
//                }
//            }
//        }
//        setImageTags(tagList);
//    }
//
//    /**
//     * Deletes all tags from tagList, and removes association of this image from all Tags it was associated by
//     * NOT USED BUT PLEASE DO NOT DELETE
//     * May be used for additional functions in phase2
//     */
//    public void deleteAllTags(){
//        for(Tag tag : tagList){
//            tag.removeImage(this);
//        }
//        tagList.clear();
//        setImageTags(tagList);
//    }
//
//    /**
//     * Iterates through the tags in newTags, checks for existence in tagList, and adds it if it doesn't exists, adding this
//     * ImageData to association of new tags.
//     * Calls setImageTags to reset the name of this ImageData according to current Tags.
//     * @param newTags ArrayList<Tag>
//     */
//    void addTags(ArrayList<Tag> newTags){
//        for (Tag newTag : newTags) {
//            if (!(tagList.contains(newTag))) {
//                tagList.add(newTag);
//                newTag.addImage(this);
//            }
//        }
//        setImageTags(tagList);
//    }
//
//
//    /**
//     * Takes the coreName of this ImageData, and iterates through tags to concatenate a string according to specified format.
//     * Adds tags to tagList if they are not in there already
//     * Calls tmAddTagWithImage in TagManager to add tag and association of this ImageData with said tag.
//     * Calls setImageName to change the name to concatenated string.
//     * @param tags ArrayList<Tag>
//     */
//    public void setImageTags(ArrayList<Tag> tags) {
//        StringBuilder compressedTags = new StringBuilder(coreName);
//        ArrayList<String> stringVer= new ArrayList<>();
//        tagList = tags;
//        for (Tag tag : tags) {
//            stringVer.add(tag.getTagName());
//            compressedTags.append(" @" + tag.getTagName());
//        }
//        MainContainer.getAppTagManager().tmAddTagWithImage(this, stringVer);
//        setImageName(compressedTags.toString());
//
//    }
//
//    /**
//     * Adds an entry in the nameLog with the current timestamp mapped to a string in the format of
//     * tag change: oldName --> newName
//     * Creates two files each with the location of oldName and newName, and uses the renameTo() method in
//     * java File to rename the image file that this ImageData is attached to.
//     * Modifies the name and location of the ImageData in accordance with the image file this ImageData is attached to.
//     * @param newName String
//     */
//    private void setImageName(String newName) {
//        Timestamp time = new Timestamp(System.currentTimeMillis());
//        nameLog.put(time.toString(), "tag change: " + name + " --> " + newName);
//        File oldName = new File(location);
//        File addedName = new File(path+newName+"."+type);
//        oldName.renameTo(addedName);
//        name = newName;
//        location = path+name+"."+type;
//        lastChangeTime = time.toString();
//    }
//
//    /**
//     * Getter for location, returns it in the form of String, can be used to create and manipulate image files
//     * that this ImageData is attached to.
//     * @return String
//     */
//    public String getLocation() {
//        return location;
//    }
//
//
//    /**
//     * Getter for tagList, returns it in the form of ArrayList<Tag>, can be used to iterate through current tags of image.
//     * NOT USED BUT PLEASE DON'T DELETE
//     * @return ArrayList<Tag>
//     */
//    public ArrayList<Tag> getImageTags(){
//        return tagList;
//    }
//
//    /**
//     * Checks if this ImageData is associated with a tag of name tagName,
//     * double checks if the tagList contains the target tag and if this ImageData is in the associated image list of this
//     * ImageData.
//     * @param tagName String
//     * @return boolean
//     */
//    public boolean hasTag(String tagName) {
//        Tag targetTag = new Tag(tagName);
//        for (Tag target : MainContainer.getAppTagManager().getListOfTags()) {
//            if (target.equals(targetTag)) {
//                return tagList.contains(targetTag) && target.getAssociatedImages().contains(this);
//            }
//        }
//        return false;
//    }
//
//
//    /**
//     * Getter for the lastChangeTime of the ImageData name.
//     * FOR TESTING PURPOSES PLEASE DO NOT DELETE
//     * @return String
//     */
//    public String getLastChangeTime() {
//        return lastChangeTime;
//    }
//
//
//    /**
//     * Overrides equals method in Object, check if other Object is an ImageData and if it has the same location
//     * as this ImageData object (location of ImageData objects should always be unique).
//     * @param other Object
//     * @return boolean
//     */
//    @Override
//    public boolean equals(Object other){
//        return other instanceof ImageData && location.equals(((ImageData) other).getLocation());
//    }
//
//    public boolean containsTags(ArrayList<String> tags){
//        if (tags.size() == 0){
//            return false;
//        }
//        for (String tag: tags){
//            if (!this.hasTag(tag)){
//                return false;
//            }
//        }
//        return true;
//    }
//}