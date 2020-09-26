import Command.Command;
import Command.RegisterVisitor;
import Library.Library;

import java.util.Scanner;

/**
 * @author Jonathan Pofcher
 */
public class LMBS
{
    private static Library library;

    public static void main(String[] args)
    {
        library = new Library();
        System.out.println("Library Book Managment System\nPlease enter a command:");
        while(true) {
            Scanner input = new Scanner(System.in);
            String request = input.nextLine();

            Command command = CommandInterpretter.interpret(request);
            command.execute();
        }

    }



}