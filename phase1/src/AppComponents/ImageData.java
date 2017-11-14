package AppComponents;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ImageData {


    private String location = "";
    private String name = "";
    private String coreName = "";
    private ArrayList<Tag> tagList;
//  String: timestamp, String: name of image, or location
    private LinkedHashMap<String, String> nameLog = new LinkedHashMap<>();
    private static int idCounter;
    private int id;
    //For testing purposes
    private String lastChangeTime;

    public ImageData(String name, String location) {
        setImageName(name);
        coreName = name;
        setImageLocation(location);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        nameLog.put(time.toString(), "Initially named : "+ name+", initially in : "  + location);
        tagList = new ArrayList<>();
        setImageID(idCounter);
        idCounter++;
    }


    public String printLog(){
        String log = "";
        for (Map.Entry<String, String> entry : nameLog.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            log += key + "---" + value;
            log += System.getProperty("line.separator");
        }
        //for (String entry: nameLog.keySet()){
        //    log += entry + "---" + nameLog.get(entry);
        //    log += System.getProperty("line.separator");
        //}
        return log;

    }

    private void setImageID(int IdCounter){
        id = IdCounter;

    }

    public int getImageID(){
        return id;
    }

    public void deleteTags(ArrayList<Tag> dTags){
//        for (int i = 0; i < dTags.size(); i++) {
//            if((tagList.contains(dTags.get(i)))){
//                tagList.remove(dTags.get(i));}
//            }
        for (Tag dTag : dTags) {
            if ((tagList.contains(dTag))) {
                tagList.remove(dTag);
            }
        }
        //goes to Isabel and Pyush
        setImageTags(tagList);

    }

    public void deleteAllTags(){
        tagList.clear();
        setImageTags(tagList);
    }

    public void addTags(ArrayList<Tag> newTags){
        for (Tag newTag : newTags) {
            if (!(tagList.contains(newTag))) {
                tagList.add(newTag);
            }
        }
//        for (int i = 0; i < newTags.size(); i++) {
//            if(!(tagList.contains(newTags.get(i)))){
//                tagList.add(newTags.get(i));
//            }
//        }
        setImageTags(tagList);
    }


    public void setImageLocation(String imageLocation){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        if (location.length() == 0){
            location = imageLocation;
            lastChangeTime = time.toString();
        }
        else{nameLog.put(time.toString(), "location change: " + location + " --> " + imageLocation);
            location = imageLocation;
            lastChangeTime = time.toString();
            location = imageLocation; }
    }

    public void setImageTags(ArrayList<Tag> tags) {
        StringBuilder compressedTags = new StringBuilder(coreName);

        for (Tag tag : tags) {
            compressedTags.append(" @" + tag.getTagName());
        }
//        for (int i = 0; i < tags.size(); i++) {
//            compressedTags.append(" @" + tags.get(i).getTagName());
//        }
        Timestamp time = new Timestamp(System.currentTimeMillis());
        setImageName(compressedTags.toString());
//            nameLog.put(time.toString(), "tag change: " + name + " --> " + compressedTags.toString());
//            name = compressedTags.toString();
//            lastChangeTime = time.toString();}

    }

    public void setImageName(String newName) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        if (name.length() == 0){
            name = newName;
            File oldName = new File(location+"");
            File addedName = new File(location+name);
            boolean flag = oldName.renameTo(addedName);
            lastChangeTime = time.toString();
            if(flag){
                //image changed successfully
            }else{
                //image rename fails
            }}
        else{nameLog.put(time.toString(), "tag change: " + name + " --> " + newName);
            File oldName = new File(location+name);
            File addedName = new File(location+newName);
            boolean flag = oldName.renameTo(addedName);
            name = newName;
            lastChangeTime = time.toString();
//            if(flag){
//                //image changed successfully
//            }else{}
//                //image rename fails
}}



    public ArrayList<Tag> getImageTags(){
        return tagList;
    }

    public String getImageLocation() {
        return location;
    }

    //For testing purposes
    public String getLastChangeTime() {
        return lastChangeTime;
    }

    public String getImageName() {
        return name;
    }

    @Override
    public boolean equals(Object other){
        return (other instanceof ImageData) && (location.equals(((ImageData) other).getImageLocation())) &&
                (id == ((ImageData) other).getImageID());

    }
}
