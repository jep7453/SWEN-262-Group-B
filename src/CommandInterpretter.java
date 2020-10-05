import Command.*;
import Library.Library;
import Command.Time;

import java.util.Date;
import java.util.GregorianCalendar;

public class CommandInterpretter {


    public CommandInterpretter() { }


    public Command interpret(String request, Library library) {
        Command command;
        String[] parts = request.split(",");
        switch (parts[0]) {
            case "register":
               command = new RegisterVisitor(parts[1],parts[2],parts[3],parts[4]);
               return command;
            case "report":
                command = new LibraryReport(library);
                return command;
            case "advance":
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(new Date());
                command = new Time(calendar,parts[1],library);
                return command;
            default:
                return null;
        }
    }
}
