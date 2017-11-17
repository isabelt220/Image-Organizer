package AppGUI.CenterPanel;

import AppComponents.ImageData;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.ImageView;

public class TableListElement {
    private String name;
    private ImageView picture;

    public String getName() {
        return name;
    }

    public ImageView getPicture() {
        return picture;
    }

    public TableListElement(String name){
        this.name=name;
    }

    public void setTag(String name) {
        this.name = name;
    }

    public void setPicture(ImageView picture) {
        this.picture = picture;
    }
}