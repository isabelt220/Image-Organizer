package AppGUI.CenterPanel;


import javafx.scene.image.ImageView;

public class TableListElement {
    private String name;
    private ImageView picture;
    private String coreName;

    public TableListElement(String name){
        this.name=name;
        this.coreName=name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoreName(String coreName) {
        this.coreName = coreName;
    }

    public String getCoreName() {

        return coreName;
    }

    public String getName() {
        return name;
    }

    public ImageView getPicture() {
        return picture;
    }


    public void setTag(String name) {
        this.name = name;
    }

    public void setPicture(ImageView picture) {
        this.picture = picture;
    }
}