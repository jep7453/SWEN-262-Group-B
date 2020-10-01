import Command.*;
import Library.Library;
import Command.Time;

import java.util.Date;

public class CommandInterpretter {


    public CommandInterpretter() { }


    public Command interpret(String request, Library library) {
        Command command;
        String[] parts = request.split(",");
        switch (parts[0]) {
            case "register":
               command = new RegisterVisitor(parts[1],parts[2],parts[3],parts[4]);
               //checkMonth(parts[5]);//replace parameter with the current date
               return command;
            case "report":
                command = new LibraryReport(library);
                return command;
            default:
                return null;
        }
    }
}
