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

public class PayFineGUI {
    GridPane gridPane;
    Library library;
    String idString;
    String fineString;
    CommandInterpretter interpretter;
    public PayFineGUI(GridPane gridPane, Library library, CommandInterpretter interpretter) {
        this.gridPane = gridPane;
        this.library = library;
        this.interpretter = interpretter;
    }

    public GridPane payFineGridPane() {
        GridPane outputGridPane = gridPane;
        gridPane.getChildren().clear();

        Button commandPage = new Button("Return to command page");
        commandPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GUILMBS.commandDisplay(outputGridPane, library, interpretter);
            }
        });

        Label id = new Label("Visitor ID: ");
        TextArea idValue = new TextArea();
        idValue.setPrefWidth(300);
        idValue.setPrefHeight(50);

        Label fine = new Label("Amount of fines to pay: ");
        TextArea fineValue = new TextArea();
        fineValue.setPrefWidth(300);
        fineValue.setPrefHeight(50);

        Label output = new Label("");

        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                idString = idValue.getText();
                fineString = fineValue.getText();
                handlePayFine(output);
            }
        });



        outputGridPane.add(commandPage, 0, 0);
        outputGridPane.add(id, 0, 1);
        outputGridPane.add(idValue, 1, 1);
        outputGridPane.add(fine, 0, 2);
        outputGridPane.add(fineValue, 1, 2);
        outputGridPane.add(submit, 0, 3);
        outputGridPane.add(output, 0, 4);

        return outputGridPane;
    }

    public void handlePayFine(Label output) {
        String request = "pay" + ',' + idString + ',' + fineString + ';';
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
