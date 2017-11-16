package AppComponents;

import java.util.ArrayList;

public class Tag {

    private String name;
    private ArrayList<ImageData> associatedImages;

    public Tag(String names){
        this.name=names.toLowerCase();
        this.associatedImages =  new ArrayList<ImageData>();
    }

    protected void addImage(ImageData image) {
        this.associatedImages.add(image);
    }

    protected void removeImage(ImageData image) {
        this.associatedImages.remove(image);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Tag) && ((Tag) obj).name.equals(this.name);
    }

    public void setTagName(String name) {
        this.name = name.toLowerCase();
    }

    public void setAssociatedImages(ArrayList<ImageData> associatedImages) {
        this.associatedImages = associatedImages;
    }

    public String getTagName() {

        return name;
    }

    public ArrayList<ImageData> getAssociatedImages() {
        return associatedImages;
    }
}
