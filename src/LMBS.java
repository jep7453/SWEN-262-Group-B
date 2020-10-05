import Command.Command;
import Library.Library;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * @author Jonathan Pofcher
 */
public class LMBS
{
    private static Library library;
    private static final CommandInterpretter interpretter = new CommandInterpretter();


    public static void main(String[] args)throws CloneNotSupportedException
    {
        library = new Library();

        System.out.println("Library Book Managment System\nPlease enter a command:");
        while(true) {
            Scanner input = new Scanner(System.in);
            String request = input.nextLine();

            Command command = interpretter.interpret(request, library);
            command.execute();
        }
    }
    



}