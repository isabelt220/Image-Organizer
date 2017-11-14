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

    public void deleteFile() throws IOException{
        TreeItem<File> targetNode = treeView.getSelectionModel().getSelectedItem();
        File target = targetNode.getValue();
        if(target == null){
            System.out.println("File does not exist");
        }
        else{
            try{
            Path directory = target.toPath();
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    if (exc == null)
                    {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                    else
                    {
                        throw exc;
                    }
                }
            });

            targetNode.getParent().getChildren().remove(targetNode);
            }
            catch (IOException e){
                System.out.println("Deletion Failed");
            }
        }
    }
}
