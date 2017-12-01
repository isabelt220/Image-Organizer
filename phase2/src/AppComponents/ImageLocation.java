package AppComponents;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Helper class for ImageData and is therefore always initialized by and attached to a ImageData,
 * stores information regarding the location, path, name, type of the image file that the ImageData
 * is attached to.
 */
public class ImageLocation implements Serializable {

    // String Location is the complete path of the file. eg. C://User/phase2/Christmas.png
    private String location = "";
    // String path is a substring of location and is the path of the image file that this ImageData is
    // attached to. eg. C://User/phase2/
    private String path;
    // String path is a substring of location and is the type of the image file that this ImageData is
    // attached to. eg. .png, .img, .jpg
    private String type;
    // String name is the substring of location after path and before extension, it can be changed
    // with the editing of tags. eg. Christmas @2017 @Mistletoe
    private String name = "";

    /**
     * Constructor for a new ImageLocation object. Takes the absolute path of an image file, records
     * it, then splices it in to various substrings such as type, path, and name that can be
     * individually manipulated and combined.
     *
     * @param locationOfImage String of the absolute path of the image file of the ImageData that this
     *                        imageLocation is attached to
     */
    public ImageLocation(String locationOfImage) {
        location = locationOfImage;
        File imageFile = new File(location);
        String temp = imageFile.getName();
        name = temp.substring(0, temp.lastIndexOf("."));
        int x = location.lastIndexOf(File.separator) + 1;
        path = location.substring(0, x);
        int i = imageFile.getName().lastIndexOf('.');
        type = imageFile.getName().substring(i + 1);
    }

    /**
     * Used when a ImageLocation is first created, reads through the name of the image, checking for
     * "@" symbols to find tags, then returns the core name independent of the tags
     *
     * @return the coreName of the ImageData
     */
    String analyzeNameForCore() {
        if (!(name.contains("@"))) {
            return name;
        } else {
            int x = name.indexOf(" @");
            String core = name.substring(0, x);
            return core;
        }
    }

    /**
     * Used when a ImageLocation is first created, reads through the name of the image, checking for
     * "@" symbols to find tags, then records these tags.
     *
     * @return ArrayList<String> tagNames associated (or to be associated with the ImageData)
     */
    ArrayList<String> analyzeNameForTags() {
        if (!(name.contains("@"))) {
            return new ArrayList<String>();
        } else {
            int x = name.indexOf(" @");
            String unsortedTags = name.substring(x);
            String[] tags = unsortedTags.split(" @");
            ArrayList<String> tagNames = new ArrayList<String>(Arrays.asList(tags));
            return tagNames;
        }
    }

    /**
     * Checks if the other object is an ImageLocation Object, then check if it has the same absolute
     * path as this ImageLocation. Since all ImageLocations are attached to an ImageDta which is
     * attached to an image file, and no two different image files will have the same absolute path.
     *
     * @param other Object for comparison with this ImageLocation
     * @return boolean whether or not this ImageLocation is conceptually the same as the other
     * ImageLocation
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof ImageLocation)
                && (location.equals(((ImageLocation) other).getLocation()));
    }

    /**
     * Setter for location, does not alter any image file, merely an update of the imageLocation after
     * a directory change of the image file of the ImageData.
     *
     * @param location String location of the image file this ImageData is attached to. eg.
     *                 C:/Photos/SummerVacation/NotLeavingTheHouseForThreeMonthsStraight.jpg
     */
    public void setLocation(String location) {
        this.location = location;
        File imageFile = new File(location);
        String temp = imageFile.getName();
        name = temp.substring(0, temp.lastIndexOf("."));
        int x = location.lastIndexOf(File.separator) + 1;
        path = location.substring(0, x);
        int i = imageFile.getName().lastIndexOf('.');
        type = imageFile.getName().substring(i + 1);
    }

    /**
     * Setter for name, and also changes location, used to update information after a tag change.
     *
     * @param name the new name of the ImageData, including new tags.
     */
    public void setName(String name) {
        this.name = name;
        location = path + name + "." + type;
    }

    /**
     * Getter for the path, or root directory of the image file, used to find and display file roots.
     *
     * @return String path eg.C:/Foods/
     */
    public String getPath() {

        return path;
    }

    /**
     * Getter for the type of image of the image file, various uses to check for types and display
     * purposes.
     *
     * @return String type of image file eg. .png, .jpg
     */
    String getType() {

        return type;
    }

    /**
     * Getter for the name of the image file, various uses to for checks, records, and display.
     *
     * @return String the current name of the image file. eg. SoHungry @OverSlept @needsBrunch
     */
    public String getName() {

        return name;
    }

    /**
     * Getter for the location of the image file, various uses to for checks, records, and display.
     *
     * @return the current location of the image file eg.
     * C:/Photos/SummerVacation/NotLeavingTheHouseForThreeMonthsStraight.jpg
     */
    public String getLocation() {

        return location;
    }
}
