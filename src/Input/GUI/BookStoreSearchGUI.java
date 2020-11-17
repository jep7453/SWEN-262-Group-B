package Input.GUI;

import Command.Command;
import Input.CommandInterpretter;
import Library.Library;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BookStoreSearchGUI {
    GridPane gridPane;
    String titleString;
    String authorsString;
    String isbnString;
    String publisherString;
    String sortOrderString;
    Library library;
    CommandInterpretter interpretter;

    public BookStoreSearchGUI(GridPane gridPane, Library library, CommandInterpretter interpretter) {
        this.gridPane = gridPane;
        this.library = library;
        this.interpretter = interpretter;
    }

    public GridPane bookStoreSearchGridPane() {
        GridPane outputGridPane = gridPane;
        gridPane.getChildren().clear();

        Button commandPage = new Button("Return to command page");
        commandPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GUILMBS.commandDisplay(outputGridPane, library, interpretter);
            }
        });

        Label instruction = new Label("Leave a * if unspecified");

        Label title = new Label("Title: ");
        TextArea titleValue = new TextArea();
        titleValue.setPrefWidth(300);
        titleValue.setPrefHeight(50);

        Label authors = new Label("Authors (in brackets) (comma separated if multiple): ");
        TextArea authorsValue = new TextArea();
        authorsValue.setPrefWidth(300);
        authorsValue.setPrefHeight(50);

        Label isbn = new Label("ISBN: ");
        TextArea isbnValue = new TextArea();
        isbnValue.setPrefWidth(300);
        isbnValue.setPrefHeight(50);

        Label publisher = new Label("Publisher: ");
        TextArea publisherValue = new TextArea();
        publisherValue.setPrefWidth(300);
        publisherValue.setPrefHeight(50);

        Label sortOrder = new Label("Sort Order: ");
        TextArea sortOrderValue = new TextArea();
        sortOrderValue.setPrefWidth(300);
        sortOrderValue.setPrefHeight(50);

        Label output = new Label("");

        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                titleString = titleValue.getText();
                authorsString = authorsValue.getText();
                isbnString = isbnValue.getText();
                publisherString = publisherValue.getText();
                sortOrderString = sortOrderValue.getText();
                handleBookStoreSearch(output);
            }
        });



        outputGridPane.add(commandPage, 0, 0);
        outputGridPane.add(instruction, 0, 1);
        outputGridPane.add(title, 0, 2);
        outputGridPane.add(titleValue, 1, 2);
        outputGridPane.add(authors, 0, 3);
        outputGridPane.add(authorsValue, 1, 3);
        outputGridPane.add(isbn, 0, 4);
        outputGridPane.add(isbnValue, 1, 4);
        outputGridPane.add(publisher, 0, 5);
        outputGridPane.add(publisherValue, 1, 5);
        outputGridPane.add(sortOrder, 0, 6);
        outputGridPane.add(sortOrderValue, 1, 6);
        outputGridPane.add(submit, 0, 7);
        outputGridPane.add(output, 0, 8);

        return outputGridPane;
    }

    public void handleBookStoreSearch(Label output) {
        String request = "search" + ',' + titleString + ',' + authorsString + ',' + isbnString + ',' + publisherString + ',' + sortOrderString + ';';
        Command command = interpretter.interpret(library, request);
        output.setText(command.execute());
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream("library.ser");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            //The object is being persisted here
            objectOutputStream.writeObject(library);
            objectOutputStream.close();
        } catch (IOException ioe) {
            //Close all I/O streams
            ioe.printStackTrace();
            //Handle the exception here
        }

    }
}
