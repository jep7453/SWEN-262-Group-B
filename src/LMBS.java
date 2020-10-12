import Command.Command;
import Library.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * @author Jonathan Pofcher
 */
public class LMBS
{

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        //creates the first day observer and gives it to the library constructor
        GregorianCalendar start = new GregorianCalendar();
        start.setTime(new Date());
        Day startDayObv = new Day(start);
        Library library = new Library(startDayObv);
        CommandInterpretter interpreter = new CommandInterpretter(startDayObv);

        System.out.println("Library Book Managment System\nPlease enter a command:");
        while(true) {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String request = input.readLine();

            Command command = interpreter.interpret(library,request);
            try {
                command.execute();
            }
            catch (NullPointerException e){
                System.out.println("Incorrect Command");
            }
        }

    }

}