import Command.Command;
import Command.RegisterVisitor;
import Library.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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


    public static void main(String[] args) throws IOException {
        Library library = new Library();

        System.out.println("Library Book Managment System\nPlease enter a command:");
        while(true) {
            Scanner input = new Scanner(System.in);
            String request = input.nextLine();

            Command command = CommandInterpretter.interpret(library,request);
            command.execute();
            System.out.println();
        }

    }




}