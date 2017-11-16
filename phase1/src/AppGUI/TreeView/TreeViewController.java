package AppGUI.TreeView;

import AppComponents.Tag;
import AppComponents.TagManager;
import AppGUI.PopUpWindow.DialogBox;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TreeViewController implements Initializable{
    @FXML
    TreeView<File> treeView = new TreeView<>();
    @FXML
    ListView<Tag> listView = new ListView<>();
    @FXML
    HBox hBox = new HBox();
    @FXML
    TextField addTagField = new TextField();
    static Image selectedImage;

    public void initialize(URL location, ResourceBundle r){
        ArrayList<Tag> tag = TagManager.getListOfTags();
        for(Tag t: tag){
            listView.getItems().add(t);
        }
        listView.setCellFactory(new Callback<ListView<Tag>, ListCell<Tag>>(){
            @Override
            public ListCell<Tag> call(ListView<Tag> param) {
                return new ListCell<Tag>() {
                    @Override
                    protected void updateItem(Tag item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getTagName()); }
                    }
                };
            }
        });

        addTagField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String text = addTagField.getText();
                if (keyEvent.getCode() == KeyCode.ENTER && !TagManager.tagExists(text))  {
                    ArrayList<String > tag = new ArrayList<>();
                    tag.add(text);
                    Tag newTag = new Tag(text);
                    TagManager.tmAddTagWithoutImage(tag);
                    listView.getItems().add(0,newTag);
                    addTagField.setText("");
                }
                else if(TagManager.tagExists(text)){
                    DialogBox dialogBox = new DialogBox("Info","Tag already exist");
                    dialogBox.display();
                }
            }
        });
    }

    public void openFolder() {
        DirectoryChooser dc = new DirectoryChooser();
        File choice = dc.showDialog(treeView.getScene().getWindow());
        if (choice != null) {
            TreeViewItem listHelper = new TreeViewItem();
            treeView.setRoot(listHelper.generateTreeItem(choice));
            treeView.setCellFactory(new Callback<TreeView<File>, TreeCell<File>>() {

                public TreeCell<File> call(TreeView<File> file) {
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

    public void addTagClick(){
        hBox.setVisible(!hBox.isVisible());
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