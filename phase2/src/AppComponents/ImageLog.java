package AppComponents;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ImageLog implements Serializable{

    //nameLog is a ordered hashmap with a timestamp of each tag modification paired to the image name at that timestamp.
    private LinkedHashMap<String, String> nameLog;
    //for testing purposes only
    private String lastChangeTime;

    private LinkedHashMap<String, ArrayList<String>> tagLog;

    public LinkedHashMap<String, ArrayList<String>> getTagLog() {
        return tagLog;
    }

    public ImageLog(String name, ArrayList<String> tags){
        nameLog = new LinkedHashMap<>();
        tagLog = new LinkedHashMap<>();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        if(tags.size() == 0){
            nameLog.put(time.toString(), "Initially named : "+ name);
            tagLog.put(time.toString(), new ArrayList<String>());
        }
        else{
            String temp = convertTagNameListToString(tags);
            nameLog.put(time.toString(), "Initially named : " + name + ". Tags: " + temp);
            tagLog.put(time.toString(), tags);
        }
    }


    public void addEntry(String coreName, ArrayList<Tag> newTagArrayList, ArrayList<Tag>oldTagArrayList){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ArrayList<String> revertTargetList = convertTagListToStringList(oldTagArrayList);
        tagLog.put(time.toString(), revertTargetList);
        String newName = convertTagListToString(newTagArrayList);
        String oldName = convertTagListToString(oldTagArrayList);
        nameLog.put(time.toString(), "Name: "+coreName+". Tags: ["+oldName+"] --> ["+newName+"]");


    }

    public LinkedHashMap<String, String> getNameLog() {
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

    public ArrayList<String> convertTagListToStringList(ArrayList<Tag> tagArrayList){
        ArrayList<String> result = new ArrayList<>();
            for(Tag tag : tagArrayList){
                result.add(tag.getTagName());
            }
        return result;
    }

    public String convertTagListToString(ArrayList<Tag> tagArrayList){
        String result = "";
        for(Tag tag : tagArrayList){
            result += (", " +tag.getTagName());
        }
        if (result.length() == 0){
            return result;
        }
        return result.substring(2);

    }

    public String convertTagNameListToString(ArrayList<String> tagNameArrayList){
        String result = "";
        for(String tagName : tagNameArrayList){
            result += (", " +tagName);
        }
        if (result.length() == 0){
            return result;
        }
        return result.substring(2);

    }

    @Override
    public boolean equals(Object other){
        return (other instanceof ImageLog) && nameLog.equals(((ImageLog) other).getNameLog());
    }
}
