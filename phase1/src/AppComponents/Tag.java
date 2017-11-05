package AppComponents;

import java.util.ArrayList;

public class Tag {

    private String name;
    private ArrayList<ImageInfo> associatedImages;

    public void setTagName(String name) {
        this.name = name;
    }

    public void setAssociatedImages(ArrayList<ImageInfo> associatedImages) {
        this.associatedImages = associatedImages;
    }

    public String getTagName() {

        return name;
    }

    public ArrayList<ImageInfo> getAssociatedImages() {
        return associatedImages;
    }
}
