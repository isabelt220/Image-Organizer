package AppGUI.TreeView;


import javafx.scene.image.Image;

public class TreeViewImage extends Image {
    private String url;

    public TreeViewImage(String url2){
        super(url2);
        this.url = url2;
    }
    public String getUrl() {
        return this.url;
    }
}