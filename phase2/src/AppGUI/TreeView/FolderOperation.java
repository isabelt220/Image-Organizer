package AppGUI.TreeView;

import AppGUI.PopUpWindow.DialogBox;
import Observers.MainObserver;
import Observers.TreeViewObserver;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Helper class for TreeViewController, contains methods for file manipulations
 */
 class FolderOperation {

    /**
     * Takes the selected file in treeView and moves the file to the location selected in mainObserver, altering
     * the ImageData if the specified file has one.
     *
     * @param treeViewObserver observer of the current treeView
     * @param mainObserver observer of the current mainView
     */
     void moveFile(TreeViewObserver treeViewObserver, MainObserver mainObserver) {

        File selectedFile = treeViewObserver.getSelectedFile();
        DirectoryChooser dc = new DirectoryChooser();
        File choice = dc.showDialog(mainObserver.getMain().getMainStage().getOwner());
        if (choice != null) {
            try {
                String name = selectedFile.getName();
                Path targetPath = Paths.get(choice.toPath().toString() + File.separator + name);
                Files.move(selectedFile.toPath(), targetPath, REPLACE_EXISTING);
                if (!selectedFile.getParentFile().toPath().toString().equals(choice.toPath().toString())) {
                    treeViewObserver.update();
                }
            }
            catch (IOException e) {
                DialogBox warning = new DialogBox("Warning", "Remove Failed");
                warning.display();
            }
        }

    }

    /**
     * Takes the chosen file in directory selector and generates a treeView of the file and the sub files.
     * Sets the tree view in center panel
     *
     * @param mainObserver observer of the current mainView
     * @param treeView File
     */
     void openFolder(MainObserver mainObserver, TreeView<File> treeView){
        DirectoryChooser dc = new DirectoryChooser();
        File choice = dc.showDialog(mainObserver.getMain().getMainStage().getOwner());
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
            mainObserver.setPanel("folder");
        }
    }

}
