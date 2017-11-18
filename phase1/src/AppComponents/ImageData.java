package AppComponents;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ImageData implements Serializable{


    private String location = "";
    private String name = "";
    private String coreName = "";
    private ArrayList<Tag> tagList;
//  String: timestamp, String: name of image, or location
    private LinkedHashMap<String, String> nameLog = new LinkedHashMap<>();
//    private static int idCounter;
//    private int id;
    //For testing purposes
    private ImageView view;
    private String lastChangeTime;
    private String path;
    private String type;

    public ImageData(String location) {
        this.location = location;
        setView();
        File imageFile = new File(location);
        String temp = imageFile.getName();
        name = temp.substring(0,temp.lastIndexOf("."));
        coreName = name;
        int x = location.lastIndexOf(File.separator)+1;
        path = location.substring(0,x);
        int i = imageFile.getName().lastIndexOf('.');
        type = imageFile.getName().substring(i+1);
//        setImageLocation(location);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        nameLog.put(time.toString(), "Initially named : "+ name);
        tagList = new ArrayList<>();
//        setImageID(idCounter);
//        idCounter++;
    }

    public String getName(){return name;}

    public String getCoreName(){return coreName;}

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

//    private void setImageID(int IdCounter){
//        id = IdCounter;
//
//    }

//    public int getImageID(){
//        return id;
//    }

   void deleteTags(ArrayList<Tag> dTags){
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

    void addTags(ArrayList<Tag> newTags){
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


//    public void setImageLocation(String imageLocation){
//        Timestamp time = new Timestamp(System.currentTimeMillis());
//        if (location.length() == 0){
//            location = imageLocation;
//            lastChangeTime = time.toString();
//        }
//        else{nameLog.put(time.toString(), "location change: " + location + " --> " + imageLocation);
//            File before = new File(location);
//            boolean after = before.renameTo(new File(imageLocation + before.getName()));
//            location = imageLocation + name;
//            lastChangeTime = time.toString();
//            location = imageLocation; }
//    }

    private void setImageTags(ArrayList<Tag> tags) {
        StringBuilder compressedTags = new StringBuilder(coreName);

        for (Tag tag : tags) {
            compressedTags.append(" @" + tag.getTagName());
        }
//        for (int i = 0; i < tags.size(); i++) {
//            compressedTags.append(" @" + tags.get(i).getTagName());
//        }
        setImageName(compressedTags.toString());
//            nameLog.put(time.toString(), "tag change: " + name + " --> " + compressedTags.toString());
//            name = compressedTags.toString();
//            lastChangeTime = time.toString();}

    }

    private void setImageName(String newName) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
         nameLog.put(time.toString(), "tag change: " + name + " --> " + newName);
            File oldName = new File(location);
            File addedName = new File(path+newName+"."+type);
            oldName.renameTo(addedName);
            System.out.println(path+newName+"."+type+"FromImageData");
            name = newName;
            location = path+name+"."+type;
            lastChangeTime = time.toString();
//            if(flag){
//                //image changed successfully
//            }else{}
//                //image rename fails
}

    private void setView(){
        File imageFile = new File(location);
        Image image = new Image(imageFile.toURI().toString());
        this.view = new ImageView(image);

    }

    public String getLocation() {
        return location;
    }

    public ImageView getView() {
        return view;
    }

    public ArrayList<Tag> getImageTags(){
        return tagList;
    }

    private String getImageLocation() {
        return location;
    }

    //For testing purposes
    public String getLastChangeTime() {
        return lastChangeTime;
    }


    @Override
    public boolean equals(Object other){
        return other instanceof ImageData && location.equals(((ImageData) other).getImageLocation());
    }
}
