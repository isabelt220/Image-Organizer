package AppGUI.PopUpWindow;

import AppComponents.ImageData;
import AppGUI.MainContainer;
import AppGUI.TreeView.OperatingMenuController;
import Observers.TreeViewObserver;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Initialized by ImageTagEditor for tag modification on a selected image in treeView.
 * Extends Operating MenuController because it has the similar usages and function as Operating Menu Controller (adding and deleting tags of selected image).
 * The only method is for the display of the image view of the selected image (the only thing that opMenu does not have).
 */
public class ImageTagEditorController extends OperatingMenuController {

    /** Image view placement of image that the ImageTagEditor is currently editing.*/
    @FXML
    private ImageView myImageView = new ImageView();

    /**
     * Sets the image view of selected image file.
     *
     * @param treeViewObserver initialized by MainGUI and observes the tree view panel that this ImageTagEditor is communicating with.
     */
    void setView(TreeViewObserver treeViewObserver){
        Image image = new Image(treeViewObserver.getSelectedFile().toURI().toString());
        myImageView.setImage(image);
        ImageData i = MainContainer.getAppImageManager().getImage(treeViewObserver.getSelectedFile().toPath().toString());
        if(i==null){
            i = new ImageData(treeViewObserver.getSelectedFile().toPath().toString());
        }
        setOperatingImage(i);
    }

//    /**
//     * Initializes the image view by taking the selected image File and sets it in the myImageView of the fxml.
//     *
//     * @param location URL
//     * @param r ResourceBundle
//     */
//    @Override
//    public void initialize(URL location, ResourceBundle r) {
//        super.initialize(location, r);
//
//    }

}
