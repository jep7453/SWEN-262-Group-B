package Input.GUI;

import Input.CommandInterpretter;
import Library.Library;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BorrowedBooksSearchGUI {
    GridPane gridPane;
    Library library;
    CommandInterpretter interpretter;
    public BorrowedBooksSearchGUI(GridPane gridPane, Library library, CommandInterpretter interpretter) {
        this.gridPane = gridPane;
        this.library = library;
        this.interpretter = interpretter;
    }

    public GridPane borrowedBooksSearchGridPane() {
        GridPane outputGridPane = gridPane;

        outputGridPane.getChildren().clear();


        return outputGridPane;
    }
}
