package AppComponents;

import AppGUI.MainContainer;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class ImageLocation implements Serializable{

    // String Location is the complete path of the file. eg. C://User/phase1/Christmas.png
    private String location = "";
    // String path is a substring of location and is the path of the image file that this ImageData is attached to. eg. C://User/phase1/
    private String path;
    // String path is a substring of location and is the type of the image file that this ImageData is attached to. eg. .png, .img, .jpg
    private String type;
    //String name is the substring of location after path and before extension, it can be changed with the editing of tags. eg. Christmas @2017 @Mistletoe
    private String name = "";


    public ImageLocation(String pathofImage){
        location = pathofImage;
        File imageFile = new File(location);
        String temp = imageFile.getName();
        name = temp.substring(0,temp.lastIndexOf("."));
        int x = location.lastIndexOf(File.separator)+1;
        path = location.substring(0,x);
        int i = imageFile.getName().lastIndexOf('.');
        type = imageFile.getName().substring(i+1);
    }

    /**
     * Used when a ImageData is first created, reads through the name of the image, checking for "@"
     * symbols to find tags, then returns the core name independent og the tags
     * @return the coreName of the Image
     */
    public String analyzeNameForCore(){
        if (!(name.contains("@"))){
            return name;
        }
        else{
            int x = name.indexOf(" @");
            String core = name.substring(0, x);
            return core;
        }

    }

    /**
     * Used when a ImageData is first created, reads through the name of the image, checking for "@"
     * symbols to find tags, then records these tags.
     * @return the coreName of the Image
     */
    public ArrayList<String> analyzeNameForTags(){
        if (!(name.contains("@"))){
            return new ArrayList<String>();
        }
        else{
            int x = name.indexOf(" @");
            String unsortedTags = name.substring(x);
            String[] tags = unsortedTags.split(" @");
            ArrayList<String> tagNames= new ArrayList<String>(Arrays.asList(tags));
            return tagNames;
        }

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        File imageFile = new File(location);
        String temp = imageFile.getName();
        name = temp.substring(0,temp.lastIndexOf("."));
        int x = location.lastIndexOf(File.separator)+1;
        path = location.substring(0,x);
        int i = imageFile.getName().lastIndexOf('.');
        type = imageFile.getName().substring(i+1);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        location = path + name + "."+type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        location = path + name + "."+type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        location = path + name + "."+type;
    }

    @Override
    public boolean equals(Object other){
        return (other instanceof ImageLocation) && (location.equals(((ImageLocation) other).getLocation()));
    }
}
