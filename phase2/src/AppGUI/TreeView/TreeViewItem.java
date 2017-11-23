package AppGUI.TreeView;


import AppGUI.PopUpWindow.DialogBox;
import javafx.scene.control.TreeItem;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;

public class TreeViewItem {

    /**
     * Takes the dir directory as the TreeView Item and displays its children file in the treeView.
     * @param dir Directory
     * @return
     */
    public TreeItem<File> generateTreeItem(File dir) {
        DialogBox warning = new DialogBox("Warning", "The folder you are choosing is empty!");
        try {
            TreeItem<File> directory = new TreeItem<>(dir);
            File[] files = dir.listFiles();
            if (files == null) {
                warning.display();
            } else {
                for (File f : files) {
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
            }
            return directory;
        } catch (Exception e) {
            warning.display();
        }
        return null;
    }

}
