package Command;
import Library.Library;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Time implements Command {

    private GregorianCalendar presentDate;
    private GregorianCalendar simulatedDate;
    private ArrayList<Integer> fieldList = new ArrayList<>();
    private Library library;

    public Time(GregorianCalendar present, String advancement, Library library){//advancement should be "value time" Ex. "5 hour"
        // do not include the "s"
        this.library = library;
        this.presentDate = present;
        presentDate.setLenient(true);
        this.simulatedDate = present;
        simulatedDate.setLenient(true);
        this.simulatedDate = setSimulatedDate(advancement.split(" ")[1], advancement.split(" ")[0]);
    }

    public GregorianCalendar setSimulatedDate(String advancement, String advancementValue){
        int advancementInt = Integer.parseInt(advancementValue);
        switch (advancement) {
            case "minutes"://separate the string "hh:mm:ss" add the time then add it back to newTime
               simulatedDate.set(Calendar.MINUTE, Calendar.MINUTE + advancementInt);
               return simulatedDate;
            case "hour":
                simulatedDate.set(Calendar.HOUR, Calendar.HOUR + advancementInt);
                return simulatedDate;
            case "day":
                simulatedDate.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH + advancementInt);
                return simulatedDate;
            case "month":
                simulatedDate.set(Calendar.MONTH, Calendar.MONTH + advancementInt);
                return simulatedDate;
            default:
                return null;
        }
    }

    public String getDateString(){//convert to date obj because toString for georgian calender returns a complex string
        Date dateObj = simulatedDate.getTime();
        return dateObj.toString();
    }

    private String simulateLibrary(Library library){
        /*
        //ArrayList<Book> rentedBooks = library.getRentedBooks();
        simulateRented(rentedBooks);
         */
        return null;

    }
    /*
    private ArrayList<Book> simulateRented(ArrayList<Book> rentedBooks){
        int i = 0;
        ArrayList<Book> overDue = new ArrayList<>();
        Date dueDate;
        Book book;
        while(rentedBooks.get(i) != null){
            book = rentedBooks.get(i);
            dueDate = book.getReturnDate();
            if(dueDate.compareTo(simulatedDate.getTime()) < 0){
                overdue.add(book)
            }
        }
        return overDue;
    }

     */

    public void execute() throws CloneNotSupportedException{
        Library simulatedLibrary = (Library) library.clone();

        //ArrayList<Book> rentedBooks = library.getRentedBooks();

    }
}
