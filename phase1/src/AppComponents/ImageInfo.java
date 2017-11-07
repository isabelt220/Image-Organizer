package AppComponents;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class ImageInfo {


    private String location;
    private String name;
    private ArrayList<Tag> tagList;
//  String: timestamp, String: name of image, or location
    private HashMap<String, String> nameLog;
    private static int idCounter;
    private int id;

    public ImageInfo(String name, String location) {
        setImageName(name);
        setImageLocation(location);
        setImageID(idCounter);
        idCounter++;
    }


    public String printLog(){
        StringBuilder log = new StringBuilder();
        for (String time: nameLog.keySet()){
            log.append(time + "---" + nameLog.get(time));
            log.append(System.getProperty("line.separator"));
        }
        return log.toString();

    }

    private void setImageID(int IdCounter){
        id = IdCounter;

    }

    public int getImageID(){
        return id;
    }

    public void setImageLocation(String imageLocation){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        nameLog.put(time.toString(), "location change" + location + "-->" + imageLocation);
        location = imageLocation;
    }

    public void setImageName(ArrayList<Tag> tags) {
        StringBuilder compressedName = new StringBuilder(name);
        for (int i = 0; i < tags.size(); i++) {
            compressedName.append(" @" + tags.get(i).getTagName());
        }
        Timestamp time = new Timestamp(System.currentTimeMillis());
        // "Changed name to compressedName"
        nameLog.put(time.toString(), "tag change" + name + "-->" + compressedName.toString());
        name = compressedName.toString();
    }

    public void setImageName(String tagname) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        nameLog.put(time.toString(), "tag change" + name + "-->" + tagname);
        name = tagname;
    }

    public String getImageLocation() {
        return location;
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
