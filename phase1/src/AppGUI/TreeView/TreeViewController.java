package AppGUI.TreeView;

import AppComponents.ImageData;
import AppComponents.Tag;
import AppGUI.MainContainer;
import AppGUI.PopUpWindow.DialogBox;

import AppGUI.PopUpWindow.NameLogPopUp;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public class TreeViewController implements Initializable{

    public ContextMenu contextMenu;
    @FXML
    private TreeView<File> treeView = new TreeView<>();
    @FXML
    ListView<Tag> listView = new ListView<>();
    @FXML
    HBox hBox = new HBox();
    @FXML
    TextField addTagField = new TextField();
    static File selectedImage;


    public void initialize(URL location, ResourceBundle r){
        listView.setItems(MainContainer.getAppTagManager().getObservableTagList());
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
                if (keyEvent.getCode() == KeyCode.ENTER && !MainContainer.getAppTagManager().tagExists(text))  {
                    ArrayList<String > tag = new ArrayList<>();
                    tag.add(text);
                    MainContainer.getAppTagManager().tmAddTagWithoutImage(tag);
                    addTagField.setText("");
                }
                else if(keyEvent.getCode() == KeyCode.ENTER && MainContainer.getAppTagManager().tagExists(text)){
                    DialogBox dialogBox = new DialogBox("Info","Tag already exist");
                    dialogBox.display();
                }
            }
        });

    }

    public void openFolder() throws IOException{
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
            MainContainer.getMain().showFolderPanel();
        }
    }
    public void deleteTagClick(){
        ObservableList<Tag> selectedItems= listView.getSelectionModel().getSelectedItems();
        Tag temp = null;
        for(Tag t: selectedItems){
            MainContainer.getAppImageManager().removeTagFromAppAndImages(t.getTagName());
            reSetTree();
            listView.getItems().remove(t);
            MainContainer.getMiddleWindowController().refreshTable();
        }


    }


    public void addTagClick(){
        hBox.setVisible(!hBox.isVisible());
    }


    public void treeItemClick() throws IOException{
        TreeItem<File> currentNode = treeView.getSelectionModel().getSelectedItem();
        if(currentNode!= null){
            selectedImage =currentNode.getValue();
        try {
            MainContainer.getMain().showCenterView();
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        MainContainer.getMiddleWindowController().setPanel(currentNode.getValue().toPath().toString());
        if(!currentNode.getValue().isDirectory()){
                if(currentNode.getValue()!=null){
                    treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
                        if(t.getButton() == MouseButton.SECONDARY) {
                            contextMenu.show(treeView, t.getScreenX() , t.getScreenY());
                        } else if(t.getButton() == MouseButton.PRIMARY && t.getClickCount() == 2) {
                            try {
                                MainContainer.getMain().showOperatingMenu();
                                ImageData image = MainContainer.getAppImageManager().getImage(currentNode.getValue().toPath().toString());
                                MainContainer.getOperatingMenuController().setOperatingMenu(image);
                            } catch (IOException e) {
                                System.err.println("Caught IOException: " + e.getMessage());
                            }
                        }

                    });
                }
        } else {
            MainContainer.getMiddleWindowController().setSelectedItemLocation(null);
            MainContainer.getMain().showFolderPanel();
            MainContainer.getFolderPanelController().setPanel(currentNode.getValue().toPath().toString());
        }
    }}





    public void openImageTagEditor() throws Exception{
        File currentFile = treeView.getSelectionModel().getSelectedItem().getValue();
        if(currentFile!=null && currentFile.isDirectory()){
            DialogBox alertBox = new DialogBox("Info","Cannot rename a folder");
            alertBox.display();
        }
        else if(currentFile != null){
            ImageTagEditor imageTagEditor = new ImageTagEditor();
            imageTagEditor.display();}
    }

    public void moveFile(){
        File selectedFile = treeView.getSelectionModel().getSelectedItem().getValue();
        DirectoryChooser dc = new DirectoryChooser();
        File choice = dc.showDialog(MainContainer.getMain().getMainStage().getOwner());
       if (choice != null) {
            try{
                String name = selectedFile.getName();
                Path targetPath = Paths.get(choice.toPath().toString()+File.separator+name);
            Files.move(selectedFile.toPath(),targetPath , REPLACE_EXISTING);
                if(! selectedFile.getParentFile().toPath().toString().equals(choice.toPath().toString())){
                    reSetTree();
                }}
            catch (IOException e){
                DialogBox warning = new DialogBox("Warning","Remove Failed");
                warning.display();
            }
       }

    }

    public void openNameLogPopUp() throws Exception{
        try{
            selectedImage = treeView.getSelectionModel().getSelectedItem().getValue();
            NameLogPopUp nameLogPopUp = new NameLogPopUp();
            nameLogPopUp.display();
        }catch (Exception e){
            DialogBox alertBox = new DialogBox("Warning","Please choose an Image");
            alertBox.display();
        }
    }

    void reSetTree(){
        if(MainContainer.getTreeViewController().getTreeView().getRoot()!=null){
        TreeViewItem listHelper = new TreeViewItem();
        treeView.setRoot(listHelper.generateTreeItem(treeView.getRoot().getValue()));
    }}

    public void addTagToImage(){
        Tag t = listView.getSelectionModel().getSelectedItem();
        String location = MainContainer.getMiddleWindowController().getSelectedItemLocation();
        if(t!=null && MainContainer.getMiddleWindowController().getSelectedItemLocation()!= null){
            ImageData currentImage = MainContainer.getAppImageManager().getImage(location);
            ArrayList<String> tagList = new ArrayList<>();
            tagList.add(t.getTagName());
            MainContainer.getAppImageManager().imAddTagWithImage(currentImage, tagList);
            MainContainer.getMiddleWindowController().setPanel(currentImage.getLocation());
            reSetTree();
        }
    }


    public TreeView<File> getTreeView() {
        return treeView;
    }
}

