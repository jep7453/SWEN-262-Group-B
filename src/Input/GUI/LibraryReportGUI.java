package Input.GUI;

import Command.Command;
import Input.CommandInterpretter;
import Library.Library;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class LibraryReportGUI {
    GridPane gridPane;
    Library library;
    String daysString;
    CommandInterpretter interpretter;
    public LibraryReportGUI(GridPane gridPane, Library library, CommandInterpretter interpretter) {
        this.gridPane = gridPane;
        this.library = library;
        this.interpretter = interpretter;
    }
    public GridPane libraryReportGridPane() {
        GridPane outputGridPane = gridPane;
        gridPane.getChildren().clear();

        Button commandPage = new Button("Return to command page");
        commandPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GUILMBS.commandDisplay(outputGridPane, library, interpretter);
            }
        });

        Label days = new Label("Days to report back to (leave blank for the start): ");
        TextArea daysValue = new TextArea();
        daysValue.setPrefWidth(300);
        daysValue.setPrefHeight(50);

        Label output = new Label("");

        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                daysString = daysValue.getText();
                handleLibraryReport(output);
            }
        });



        outputGridPane.add(commandPage, 0, 0);
        outputGridPane.add(days, 0, 1);
        outputGridPane.add(daysValue, 1, 1);
        outputGridPane.add(submit, 0, 2);
        outputGridPane.add(output, 0, 3);

        return outputGridPane;
    }

    public void handleLibraryReport(Label output) {
        String request;
        if(daysString.equals("")) {
            request = "report;";
        }
        else {
            request = "report" + ',' + daysString + ';';
        }
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
