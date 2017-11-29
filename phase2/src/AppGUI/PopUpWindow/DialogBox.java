package AppGUI.PopUpWindow;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** Parent class for all alert pop-ups when there is an error in the application*/
public class DialogBox {

    /**The main stage for the pop up dialog box */
    private Stage window = new Stage();

    /**
     * Constructor for this DialogBox. Initializes the title and the message.
     *
     * @param title   String descriptive title of the error
     * @param message String message indicating further user action and/or describes the error.
     */
    public DialogBox(String title, String message) {
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(250);

        Label label = new Label();
        label.setText(message);

        Button button = new Button("close");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                window.close();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
    }

    /**Display this DialogBox.*/
    public void display() {
        window.showAndWait();
    }
}
