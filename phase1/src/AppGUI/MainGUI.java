package AppGUI;

import javafx.application.Application;
import javafx.stage.Stage;

/*
Starts the GUI for the app
*/
public class MainGUI extends Application {

    @Override
    public void start(Stage stage) {
        AppFrame mainFrame = new AppFrame("Main Window");
        mainFrame.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}

