package Input.GUI;

import Input.CommandInterpretter;
import Library.Library;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class AdvanceTimeGUI {
    GridPane gridPane;
    Library library;
    CommandInterpretter interpretter;

    public AdvanceTimeGUI(GridPane gridPane, Library library, CommandInterpretter interpretter) {
        this.gridPane = gridPane;
        this.library = library;
        this.interpretter = interpretter;
    }

    public GridPane advanceTimeGridPane() {
        GridPane outputGridPane = gridPane;

        outputGridPane.getChildren().clear();


        return outputGridPane;
    }
}
