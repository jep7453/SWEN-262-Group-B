import Command.*;
import Library.Library;

import java.util.ArrayList;

public class CommandInterpretter {


    public CommandInterpretter() {

    }

    public static Command interpret(Library library, String request) {
        Command command;
        String[] parts = request.split(",");
        switch (parts[0]) {
            case "register":
               command = new RegisterVisitor(library,parts[1],parts[2],parts[3],parts[4]);
               return command;
            case "purchase":
                ArrayList<String> books = new ArrayList<>();
                int iter = 0;
                for(String part: parts) {
                    if(iter>1) {
                        books.add(part);
                    }
                    iter++;
                }
                command = new PurchaseBook(library,Integer.valueOf(parts[1]),books);
                return command;
            default:
                return null;
        }
    }
}
