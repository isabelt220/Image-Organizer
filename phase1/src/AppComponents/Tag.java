package AppComponents;

import java.io.Serializable;
import java.util.ArrayList;

public class Tag implements Serializable {

    private String name;
    private ArrayList<ImageData> associatedImages;

    public Tag(String names) {
        this.name = names.toLowerCase();
        this.associatedImages = new ArrayList<>();
    }

    void addImage(ImageData image) {
        this.associatedImages.add(image);
    }

    void removeImage(ImageData image) {
        this.associatedImages.remove(image);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Tag) && ((Tag) obj).name.equals(this.name);
    }

    public String getTagName() {

        return name;
    }

    ArrayList<ImageData> getAssociatedImages() {
        return associatedImages;
    }
}
