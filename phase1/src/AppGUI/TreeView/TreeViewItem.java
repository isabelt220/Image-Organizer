package AppGUI.TreeView;


import javafx.scene.control.*;
import javax.activation.MimetypesFileTypeMap;
import java.io.File;

public class TreeViewItem {

    protected TreeItem<File> generateTreeItem(File dir){

        TreeItem<File> directory = new TreeItem<>(dir);
        for(File f: dir.listFiles()){
            if(f.isDirectory()){
                directory.getChildren().add(generateTreeItem(f));
            } 
            else{
                String mimeType = new MimetypesFileTypeMap().getContentType(f);
                String type = mimeType.split("/")[0];
                if(type.equals("image")){
                    directory.getChildren().add(new TreeItem<>(f));
                }
            }
        }
        return directory;
    }

}
