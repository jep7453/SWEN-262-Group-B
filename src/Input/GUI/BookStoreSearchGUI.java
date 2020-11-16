package Input.GUI;

import Input.CommandInterpretter;
import Library.Library;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class BookStoreSearchGUI {
    GridPane gridPane;
    String name;
    String authors;
    String isbn;
    String publisher;
    String sortOrd;
    Library library;
    CommandInterpretter interpretter;

    public BookStoreSearchGUI(GridPane gridPane, Library library, CommandInterpretter interpretter) {
        this.gridPane = gridPane;
        this.library = library;
        this.interpretter = interpretter;
    }

    public GridPane bookStoreSearchGridPane() {
        GridPane outputGridPane = gridPane;

        outputGridPane.getChildren().clear();


        return outputGridPane;

    }
}
