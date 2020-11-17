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

public class PurchaseBookGUI {
    GridPane gridPane;
    Library library;
    String quantityString;
    String idsString;
    CommandInterpretter interpretter;
    public PurchaseBookGUI(GridPane gridPane, Library library, CommandInterpretter interpretter) {
        this.gridPane = gridPane;
        this.library = library;
        this.interpretter = interpretter;
    }

    public GridPane purchaseBookGrid() {
        GridPane outputGridPane = gridPane;
        gridPane.getChildren().clear();

        Button commandPage = new Button("Return to command page");
        commandPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GUILMBS.commandDisplay(outputGridPane, library, interpretter);
            }
        });

        Label quantity = new Label("Quantity: ");
        TextArea quantityValue = new TextArea();
        quantityValue.setPrefWidth(300);
        quantityValue.setPrefHeight(50);

        Label ids = new Label("Book IDs (comma separated list if multiple): ");
        TextArea idsValue = new TextArea();
        idsValue.setPrefSize(200, 10);
        idsValue.setPrefHeight(50);

        Label output = new Label("");

        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                quantityString = quantityValue.getText();
                idsString = idsValue.getText();
                handlePurchaseBook(output);
            }
        });



        outputGridPane.add(commandPage, 0, 0);
        outputGridPane.add(quantity, 0, 1);
        outputGridPane.add(quantityValue, 1, 1);
        outputGridPane.add(ids, 0, 2);
        outputGridPane.add(idsValue, 1, 2);
        outputGridPane.add(submit, 0, 4);
        outputGridPane.add(output, 0, 5);

        return outputGridPane;
    }

    public void handlePurchaseBook(Label output) {
        String request = "buy" + ',' + quantityString + ',' + idsString + ';';
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
