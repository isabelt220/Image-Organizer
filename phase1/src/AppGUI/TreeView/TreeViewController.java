package AppGUI.TreeView;

import AppComponents.Tag;
import AppComponents.TagManager;
import AppGUI.MainContainer;
import AppGUI.MainGUI;
import AppGUI.PopUpWindow.DialogBox;
import AppGUI.PopUpWindow.NameLogPopUp;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class TreeViewController implements Initializable{
    @FXML
    public Button editButton;
    public ContextMenu contextMenu;
    @FXML
    TreeView<File> treeView = new TreeView<>();
    @FXML
    ListView<Tag> listView = new ListView<>();
    @FXML
    HBox hBox = new HBox();
    @FXML
    TextField addTagField = new TextField();
    public static File selectedImage;
    public boolean imageClicked;

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
        File choice =  dc.showDialog(MainContainer.getMain().getMainStage().getOwner());
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
            MainContainer.getAppImageManager().removeTagFromPic(t.getTagName());
            TreeViewItem listHelper = new TreeViewItem();
            File newRoot = treeView.getRoot().getValue();
            treeView.setRoot(listHelper.generateTreeItem(newRoot));
            listView.getItems().remove(t);
        }

    }


    public void addTagClick(){
        hBox.setVisible(!hBox.isVisible());
    }


    public void treeItemClick() throws IOException{
        TreeItem<File> currentNode = treeView.getSelectionModel().getSelectedItem();
        MainContainer.getMiddleWindowController().setPanel(currentNode.getValue().toPath().toString());
        try {
            MainContainer.getMain().showCenterView();
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }

        if(!currentNode.getValue().isDirectory()){
                if(currentNode!=null && currentNode.getValue()!=null){
                    treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
                        if(t.getButton() == MouseButton.SECONDARY) {
                            contextMenu.show(treeView, t.getScreenX() , t.getScreenY());
                        } else if(t.getButton() == MouseButton.PRIMARY && t.getClickCount() == 2) {
                            try {
                                MainContainer.getMain().showOperatingMenu();
                            } catch (IOException e) {
                                System.err.println("Caught IOException: " + e.getMessage());
                            }
                        }

                    });
                }
        } else {
            MainContainer.getMain().showFolderPanel();
            MainContainer.getFolderPanelController().setPanel(currentNode.getValue().toPath().toString());
        }
    }





    public void openImageTagEditor() throws Exception{
        File currentFile = treeView.getSelectionModel().getSelectedItem().getValue();
        if(currentFile!=null && currentFile.isDirectory()){
            DialogBox alertBox = new DialogBox("Info","Cannot rename a folder");
            alertBox.display();
        }
        else if(currentFile != null){
            selectedImage = currentFile;
            ImageTagEditor imageTagEditor = new ImageTagEditor();
            imageTagEditor.display();}
    }

    public void removeFile(){
        File selectedFile = treeView.getSelectionModel().getSelectedItem().getValue();
        DirectoryChooser dc = new DirectoryChooser();
        File choice = dc.showDialog(MainContainer.getMain().getMainStage().getOwner());
       if (choice != null) {
            try{
                String name = selectedFile.getName();
                Path targetPath = Paths.get(choice.toPath().toString()+File.separator+name);
            Files.move(selectedFile.toPath(),targetPath , REPLACE_EXISTING);}
            catch (IOException e){
                DialogBox Warning = new DialogBox("Warning","Remove Failed");
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

    public void openNameLogPopUp() throws Exception{
        File currentFile = treeView.getSelectionModel().getSelectedItem().getValue();
        if(currentFile!=null && currentFile.isDirectory()){
            DialogBox alertBox = new DialogBox("Info","Cannot rename a folder");
            alertBox.display();
        }
        else if(currentFile != null){
            selectedImage = currentFile;
            NameLogPopUp nameLogPopUp = new NameLogPopUp();
            nameLogPopUp.display();}
    }

}