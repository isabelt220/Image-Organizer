package AppGUI.TreeView;

import AppComponents.Tag;
import AppComponents.TagManager;
import AppGUI.CenterPanel.MiddleWindowController;
import AppGUI.MainGUI;
import AppGUI.PopUpWindow.DialogBox;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.text.html.ImageView;
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
    public Button editButton;
    @FXML
    TreeView<File> treeView = new TreeView<>();
    @FXML
    ListView<Tag> listView = new ListView<>();
    @FXML
    HBox hBox = new HBox();
    @FXML
    TextField addTagField = new TextField();
    public static TreeViewImage selectedImage;

    public void initialize(URL location, ResourceBundle r){
        listView.setItems(TagManager.getObservableTagList());
        listView.setCellFactory(new Callback<ListView<Tag>, ListCell<Tag>>(){
            @Override
            public ListCell<Tag> call(ListView<Tag> param) {
                return new ListCell<Tag>() {
                    @Override
                    protected void updateItem(Tag item, boolean empty) {
                        super.updateItem(item, empty);
                        setText((empty || item == null) ? "" : item.getTagName());
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
                    TagManager.tmAddTagWithoutImage(tag);
                    addTagField.setText("");
                }
                else if(keyEvent.getCode() == KeyCode.ENTER && TagManager.tagExists(text)){
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
    public void deleteTagClick(){
        ObservableList<Tag> selectedItems= listView.getSelectionModel().getSelectedItems();
        for(Tag t: selectedItems){
            TagManager.removeTag(t.getTagName());
            listView.getItems().remove(t);
        }

    }


    public void addTagClick(){
        hBox.setVisible(!hBox.isVisible());
    }

    public void treeItemClick(){
        TreeItem<File> currentNode = treeView.getSelectionModel().getSelectedItem();
        if(currentNode!=null && currentNode.getValue()!=null){
            if(!currentNode.getValue().isDirectory()){
                MainGUI.middleWindowController.setCenterPanel(currentNode);
            }
        }
    }


    public void openImageTagEditor() throws Exception{
        File currentFile = treeView.getSelectionModel().getSelectedItem().getValue();
        if(currentFile!=null && currentFile.isDirectory()){
            DialogBox alertBox = new DialogBox("Info","Cannot rename a folder");
            alertBox.display();
        }
        else if(currentFile != null){
            selectedImage = new TreeViewImage(currentFile.toURI().toString());
            System.out.println(currentFile.toURI().toString());
            ImageTagEditor imageTagEditor = new ImageTagEditor();
            imageTagEditor.display();}
    }

    public void deleteFile(){
        TreeItem<File> targetNode = treeView.getSelectionModel().getSelectedItem();
        File target = targetNode.getValue();
        if(target == null){
            DialogBox alertBox = new DialogBox("Alert","File does not exist! Please refresh");
            alertBox.display();
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
                DialogBox alertBox = new DialogBox("Alert","Sorry!Deletion Failed!");
                alertBox.display();
            }
        }
    }
//    @FXML
//    public void switchToEditPane(ActionEvent event) throws IOException{
//        Parent otherPane = FXMLLoader.load(getClass().getResource("OperatingMenu.fxml"));
//        Scene scene = new Scene(otherPane);
//        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        appStage.setScene(scene);
//        appStage.show();
//    }
}