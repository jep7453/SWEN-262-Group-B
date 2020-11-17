package Input.GUI;

import Library.Library;
import Input.CommandInterpretter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import Command.Command;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class RegisterVisitorGUI {
    GridPane gridPane;
    Library library;
    CommandInterpretter interpretter;
    String firstNameString;
    String lastNameString;
    String addressString;
    String phoneNumberString;

    public RegisterVisitorGUI(GridPane gridPane, Library library, CommandInterpretter interpretter) {
        this.gridPane = gridPane;
        this.library = library;
        this.interpretter = interpretter;
    }

    public GridPane registerVisitorGrid() {
        GridPane outputGridPane = gridPane;
        gridPane.getChildren().clear();

        Button commandPage = new Button("Return to command page");
        commandPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GUILMBS.commandDisplay(outputGridPane, library, interpretter);
            }
        });

        Label firstName = new Label("First name: ");
        TextArea firstNameValue = new TextArea();
        firstNameValue.setPrefWidth(300);
        firstNameValue.setPrefHeight(50);

        Label lastName = new Label("Last name: ");
        TextArea lastNameValue = new TextArea();
        lastNameValue.setPrefWidth(300);
        lastNameValue.setPrefHeight(50);

        Label address = new Label("Address: ");
        TextArea addressValue = new TextArea();
        addressValue.setPrefSize(200, 10);
        addressValue.setPrefHeight(50);

        Label phoneNumber = new Label("Phone number: ");
        TextArea phoneNumberValue = new TextArea();
        phoneNumberValue.setPrefWidth(300);
        phoneNumberValue.setPrefHeight(50);

        Label output = new Label("");

        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                firstNameString = firstNameValue.getText();
                lastNameString = lastNameValue.getText();
                addressString = addressValue.getText();
                phoneNumberString = phoneNumberValue.getText();
                handleRegisterVisitor(output);
            }
        });



        outputGridPane.add(commandPage, 0, 0);
        outputGridPane.add(firstName, 0, 1);
        outputGridPane.add(firstNameValue, 1, 1);
        outputGridPane.add(lastName, 0, 2);
        outputGridPane.add(lastNameValue, 1, 2);
        outputGridPane.add(address, 0, 3);
        outputGridPane.add(addressValue, 1, 3);
        outputGridPane.add(phoneNumber, 0, 4);
        outputGridPane.add(phoneNumberValue, 1, 4);
        outputGridPane.add(submit, 0, 5);
        outputGridPane.add(output, 0, 6);

        return outputGridPane;
    }

    public void handleRegisterVisitor(Label output) {
        String request = "register" + ',' + firstNameString + ',' + lastNameString + ',' + addressString + ',' + phoneNumberString + ';';
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