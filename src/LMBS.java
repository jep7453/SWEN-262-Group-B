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
    private static CommandInterpretter interpretter;


    public static void main(String[] args)throws CloneNotSupportedException, ParseException
    {
        library = new Library();

        GregorianCalendar newI = new GregorianCalendar();
        newI.setTime(new Date());
        System.out.println("Day of the week: " + newI.get(Calendar.DAY_OF_WEEK));
        System.out.println("Day of the Month: " + newI.get(Calendar.DAY_OF_MONTH));
        System.out.println("Day of the year: " + newI.get(Calendar.DAY_OF_YEAR));
        System.out.println("Date: " + newI.getTime().toString());


        /*
        System.out.println("Library Book Managment System\nPlease enter a command:");
        while(true) {
            Scanner input = new Scanner(System.in);
            String request = input.nextLine();

            Command command = interpretter.interpret(request, library);
            command.execute();
            System.out.println("Current Library Date: " + library.currentTime + '\n');
        }

         */

    }



}