package AppComponents;

import java.util.ArrayList;

public class Tag {

    private String name;
    private ArrayList<ImageInfo> associatedImages;

    protected void AddImage(ImageInfo image) {
        this.associatedImages.add(image);
    }

    protected void RemoveImage(ImageInfo image) {
        this.associatedImages.remove(image);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Tag) && ((Tag) obj).name.equals(this.name);
    }

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
