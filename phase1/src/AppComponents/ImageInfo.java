package AppComponents;

import javafx.scene.control.Tab;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ImageInfo {


    private String location = "";
    private String name = "";
    private ArrayList<Tag> tagList;
//  String: timestamp, String: name of image, or location
    private HashMap<String, String> nameLog = new HashMap<>();
    private static int idCounter;
    private int id;
    //For testing purposes
    private String lastChangeTime;

    public ImageInfo(String name, String location) {
        setImageName(name);
        setImageLocation(location);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        nameLog.put(time.toString(), "Initially named : "+ name+", initially in : "  + location);
        setImageID(idCounter);
        idCounter++;
    }


    public String printLog(){
        String log = "";
        System.out.println(nameLog);
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

    public void setImageName(ArrayList<Tag> tags) {
        StringBuilder compressedName = new StringBuilder(name);
        for (int i = 0; i < tags.size(); i++) {
            compressedName.append(" @" + tags.get(i).getTagName());
        }
        Timestamp time = new Timestamp(System.currentTimeMillis());
        if (name.length() == 0){
            name = compressedName.toString();
            lastChangeTime = time.toString();
        }
        else{// "Changed name to compressedName"
            nameLog.put(time.toString(), "tag change: " + name + " --> " + compressedName.toString());
            name = compressedName.toString();
            lastChangeTime = time.toString();}

    }

    public void setImageName(String tagname) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        if (name.length() == 0){
            name = tagname;
            lastChangeTime = time.toString();}
        else{nameLog.put(time.toString(), "tag change: " + name + " --> " + tagname);

            name = tagname;
            lastChangeTime = time.toString();}
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
        return (other instanceof ImageInfo) && (location.equals(((ImageInfo) other).getImageLocation())) &&
                (id == ((ImageInfo) other).getImageID());

    }
}
