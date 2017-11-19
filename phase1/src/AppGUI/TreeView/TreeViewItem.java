package AppGUI.TreeView;


import AppGUI.PopUpWindow.DialogBox;
import com.sun.istack.internal.Nullable;
import javafx.scene.control.*;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;

class TreeViewItem {

    TreeItem<File> generateTreeItem(File dir) {
        try{
        TreeItem<File> directory = new TreeItem<>(dir);
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                directory.getChildren().add(generateTreeItem(f));
            } else {
                String mimeType = new MimetypesFileTypeMap().getContentType(f);
                String type = mimeType.split("/")[0];
                if (type.equals("image")) {
                    directory.getChildren().add(new TreeItem<>(f));
                }
            }
        }
        return directory;}
        catch (NullPointerException e){
            DialogBox warning = new DialogBox("Warning","The folder you are choosing is empty!");
        }
        return null;
    }

}
