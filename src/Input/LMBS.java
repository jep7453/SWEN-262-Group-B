package Input;

import Command.Command;
import Library.*;

import java.io.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.io.IOException;

/**
 * @author Jonathan Pofcher
 */
public class LMBS {

    public static void main(String[] args) throws IOException{


        //creates the first day observer and gives it to the library constructor
        String serializeFileName = "library.ser";
        GregorianCalendar start = new GregorianCalendar();
        start.setTime(new Date());
        Day startDayObv = new Day(start);


        Library library =null;
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream ;
        try
        {
            fileInputStream = new FileInputStream(serializeFileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            library = (Library) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println("File not found: "+fnfe.getMessage());
            //Close all I/O streams
            //Handle the exception here
            library = new Library(startDayObv);
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
            //Close all I/O streams
            //Handle the exception here
        }
        catch(ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
            //Close all I/O streams
            //Handle the exception here
        }







        CommandInterpretter commandInterpreter = new CommandInterpretter(startDayObv);

        System.out.println("Library Book Managment System\nPlease enter a command:");
        while(true) {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String request = input.readLine();

            Command command = commandInterpreter.interpret(library,request);
            try {
                command.execute();
                FileOutputStream fileOutputStream;
                ObjectOutputStream objectOutputStream;
                try
                {
                    fileOutputStream = new FileOutputStream(serializeFileName);
                    objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    //The object is being persisted here
                    objectOutputStream.writeObject(library);
                    objectOutputStream.close();
                }
                catch(IOException ioe)
                {
                    //Close all I/O streams
                    ioe.printStackTrace();
                    //Handle the exception here
                }

            }
            catch (NullPointerException e){
                System.out.println("Incorrect Command");
            }
        }
    }

}