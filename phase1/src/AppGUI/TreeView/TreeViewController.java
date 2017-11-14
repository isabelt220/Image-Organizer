package AppGUI.TreeView;

import javafx.fxml.FXML;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class TreeViewController {
    @FXML
    TreeView<File> treeView = new TreeView<>();

    static Image selectedImage;

    public void openFolder() {
        DirectoryChooser dc = new DirectoryChooser();
        File choice = dc.showDialog(treeView.getScene().getWindow());
        if (choice != null) {
            TreeViewItem listHelper = new TreeViewItem();
            treeView.setRoot(listHelper.generateTreeItem(choice));
            treeView.setCellFactory(new Callback<TreeView<File>, TreeCell<File>>() {

                public TreeCell<File> call(TreeView<File> tv) {
                    return new TreeCell<File>() {

                        @Override
                        protected void updateItem(File item, boolean empty) {
                            super.updateItem(item, empty);

                            setText((empty || item == null) ? "" : item.getName());
                        }

                    };
                }
            });
        }
    }

    public void openImageTagEditor() throws Exception{
        File currentFile = treeView.getSelectionModel().getSelectedItem().getValue();
        if(currentFile!=null && currentFile.isDirectory()){
            System.out.println("CANNOT RENAME A FOLDER");
        }
        else if(currentFile != null){
            selectedImage = new Image(currentFile.toURI().toString());
            ImageTagEditor imageTagEditor = new ImageTagEditor();
            imageTagEditor.display();}
    }

   
}
