package AppComponents;

import java.util.ArrayList;
import java.util.HashMap;

public class ImageInfo {


    private String location;
    private String name;
    private ArrayList<Tag> tagList;
    private HashMap nameLog;
    private static int idCounter;
    private int id;

    public ImageInfo(String name, String location) {
        setImageName(name);
        setImageLocation(location);
        setImageID(idCounter);
        idCounter++;
    }
//
//    public String printLog(){
//
//    }

    private void setImageID(int IdCounter){
        id = IdCounter;

    }

    public int getImageID(){
        return id;
    }

    public void setImageLocation(String imageLocation){
        location = imageLocation;
    }

    public void setImageName(ArrayList<Tag> tags) {
        String compressedName = "";
        for (int i = 0; i < tags.size(); i++) {
            compressedName += tags.get(i).getTagName();
        }
        name = compressedName;
    }

    public void setImageName(String tagname) {
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
