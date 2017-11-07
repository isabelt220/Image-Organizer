package AppGUI;

import javafx.stage.Stage;
import javafx.scene.Scene;

public class AppFrame {

    private Stage appFrame;

    public AppFrame(String frameName) {

        // Create Stage with frame name
        appFrame = new Stage();
        appFrame.setTitle(frameName);
        appFrame.sizeToScene();
    }

    public void show() {
        appFrame.show();
    }
}
