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

public class AdvanceTimeGUI {
    GridPane gridPane;
    Library library;
    CommandInterpretter interpretter;
    String daysString;
    String hoursString;

    public AdvanceTimeGUI(GridPane gridPane, Library library, CommandInterpretter interpretter) {
        this.gridPane = gridPane;
        this.library = library;
        this.interpretter = interpretter;
    }

    public GridPane advanceTimeGridPane() {
        GridPane outputGridPane = gridPane;
        gridPane.getChildren().clear();

        Button commandPage = new Button("Return to command page");
        commandPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GUILMBS.commandDisplay(outputGridPane, library, interpretter);
            }
        });

        Label days = new Label("Days to advance: ");
        TextArea daysValue = new TextArea();
        daysValue.setPrefWidth(300);
        daysValue.setPrefHeight(50);

        Label hours = new Label("Hours to advance: ");
        TextArea hoursValue = new TextArea();
        hoursValue.setPrefWidth(300);
        hoursValue.setPrefHeight(50);

        Label output = new Label("");

        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                daysString = daysValue.getText();
                hoursString = hoursValue.getText();
                handleAdvanceTime(output);
            }
        });



        outputGridPane.add(commandPage, 0, 0);
        outputGridPane.add(days, 0, 1);
        outputGridPane.add(daysValue, 1, 1);
        outputGridPane.add(hours, 0, 2);
        outputGridPane.add(hoursValue, 1, 2);
        outputGridPane.add(submit, 0, 3);
        outputGridPane.add(output, 0, 4);

        return outputGridPane;
    }

    public void handleAdvanceTime(Label output) {
        String request = "advance" + ',' + daysString + ',' + hoursString +';';
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
