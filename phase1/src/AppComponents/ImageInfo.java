package AppComponents;

import java.util.ArrayList;
import java.util.HashMap;

public class ImageInfo {


    private String Location;
    private String Name;
    private ArrayList<Tag> TagList;
    private HashMap NameLog;
    private static int IdCounter;
    private int ID;

    public ImageInfo(String name, String location) {
        setImageName(name);
        setImageLocation(location);
        setImageID(IdCounter);
        IdCounter++;
    }
//
//    public String printLog(){
//
//    }

    private void setImageID(int IdCounter){
        ID = IdCounter;

    }

    public int getImageID(){
        return ID;
    }

    public void setImageLocation(String location){
        Location = location;
    }

    public String setImageName(ArrayList<Tag> tags) {
        String compressedName = "";
        for (int i = 0; i < tags.size(); i++) {
            compressedName += tags.get(i).getTagName();
        }
        return compressedName;
    }

    public void setImageName(String name) {
        Name = name;
    }

    public String getImageLocation() {
        return Location;
    }

    public String getImageName() {
        return Name;
    }

    @Override
    public boolean equals(Object other){
        return (other instanceof ImageInfo) && (Location.equals(((ImageInfo) other).getImageLocation())) &&
                (ID == ((ImageInfo) other).getImageID());

    }
}
