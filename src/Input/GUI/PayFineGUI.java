package Input.GUI;

import Input.CommandInterpretter;
import Library.Library;
import javafx.scene.layout.GridPane;

public class PayFineGUI {
    GridPane gridPane;
    Library library;
    CommandInterpretter interpretter;
    public PayFineGUI(GridPane gridPane, Library library, CommandInterpretter interpretter) {
        this.gridPane = gridPane;
        this.library = library;
        this.interpretter = interpretter;
    }

    public GridPane payFineGridPane() {
        GridPane outputGridPane = gridPane;

        outputGridPane.getChildren().clear();

        return outputGridPane;
    }
}
