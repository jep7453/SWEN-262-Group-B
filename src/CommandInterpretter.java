import Command.*;

public class CommandInterpretter {


    public CommandInterpretter() {

    }

    public static Command interpret(String request) {
        Command command;
        String[] parts = request.split(",");
        switch (parts[0]) {
            case "register":
               command = new RegisterVisitor(parts[1],parts[2],parts[3],parts[4]);
               return command;
            default:
                return null;
        }
    }
}
