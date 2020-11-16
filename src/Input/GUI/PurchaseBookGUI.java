package Input.GUI;

import Input.CommandInterpretter;
import Library.Library;
import javafx.scene.layout.GridPane;

public class PurchaseBookGUI {
    GridPane gridPane;
    Library library;
    CommandInterpretter interpretter;
    public PurchaseBookGUI(GridPane gridPane, Library library, CommandInterpretter interpretter) {
        this.gridPane = gridPane;
        this.library = library;
        this.interpretter = interpretter;
    }

    public GridPane purchaseBookGrid() {
        GridPane outputGridPane = gridPane;

        outputGridPane.getChildren().clear();

        return outputGridPane;
    }
}
