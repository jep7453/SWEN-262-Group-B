import Command.Command;
import Library.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Jonathan Pofcher
 */
public class LMBS
{

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        Library library = new Library();
        CommandInterpretter interpreter = new CommandInterpretter();

        System.out.println("Library Book Managment System\nPlease enter a command:");
        while(true) {
            Scanner input = new Scanner(System.in);
            String request = input.nextLine();

            Command command = interpreter.interpret(library,request);
            command.execute();
            System.out.println();
        }

    }

}