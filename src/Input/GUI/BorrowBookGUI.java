package Input.GUI;

import Input.CommandInterpretter;
import Library.Library;
import javafx.scene.layout.GridPane;

public class BorrowBookGUI {
    GridPane gridPane;
    Library library;
    CommandInterpretter interpretter;

    public BorrowBookGUI(GridPane gridPane, Library library, CommandInterpretter interpretter) {
        this.gridPane = gridPane;
        this.library = library;
        this.interpretter = interpretter;
    }

    public GridPane borrowBookGridPane() {
        GridPane outputGridPane = gridPane;

        outputGridPane.getChildren().clear();


        return outputGridPane;
    }
}
