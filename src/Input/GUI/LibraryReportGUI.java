package Input.GUI;

import Input.CommandInterpretter;
import Library.Library;
import javafx.scene.layout.GridPane;

public class LibraryReportGUI {
    GridPane gridPane;
    Library library;
    CommandInterpretter interpretter;
    public LibraryReportGUI(GridPane gridPane, Library library, CommandInterpretter interpretter) {
        this.gridPane = gridPane;
        this.library = library;
        this.interpretter = interpretter;
    }
    public GridPane libraryReportGridPane() {
        GridPane outputGridPane = gridPane;

        outputGridPane.getChildren().clear();

        return outputGridPane;
    }
}
