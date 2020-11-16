package Input.GUI;

import Input.CommandInterpretter;
import Library.Library;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;


public class BeginVisitGUI {
    GridPane gridPane;
    String visitorID;
    Library library;
    CommandInterpretter interpretter;

    public BeginVisitGUI(GridPane gridPane, Library library, CommandInterpretter interpretter) {
        this.gridPane = gridPane;
        this.library = library;
        this.interpretter = interpretter;
    }

    public GridPane beginVisitGridPane() {
        GridPane outputGridPane = gridPane;

        outputGridPane.getChildren().clear();


        return outputGridPane;
    }
}
