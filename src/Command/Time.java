package Command;
import Library.Library;
import Library.Book;

import java.text.DateFormat;
import java.util.*;

/**
 * @author Zuri Shaw
 */

public class Time implements Command {

    private final GregorianCalendar presentDate;
    private final GregorianCalendar simulatedDate;
    private final Library library;

    public Time(GregorianCalendar present, String advancement, Library library){//advancement should be "[value] [time]" Ex. "5 hour"
        // do not include the "s"
        this.library = library;
        this.presentDate = present;
        presentDate.setLenient(true);
        this.simulatedDate = setSimulatedDate(advancement.split(" ")[1], advancement.split(" ")[0]);
        simulatedDate.setLenient(true);
    }

    public GregorianCalendar setSimulatedDate(String advancement, String advancementValue){
        int advancementInt = Integer.parseInt(advancementValue);
        switch (advancement) {
            case "minutes"://separate the string "hh:mm:ss" add the time then add it back to newTime
               simulatedDate.set(Calendar.MINUTE, presentDate.get(Calendar.MINUTE) + advancementInt);
               return simulatedDate;
            case "hour":
                simulatedDate.set(Calendar.HOUR, presentDate.get(Calendar.HOUR) + advancementInt);
                return simulatedDate;
            case "day":
                simulatedDate.set(Calendar.DAY_OF_MONTH, presentDate.get(Calendar.DAY_OF_MONTH) + advancementInt);
                return simulatedDate;
            case "month":
                simulatedDate.set(Calendar.MONTH, presentDate.get(Calendar.MONTH) + advancementInt);
                return simulatedDate;
            default:
                return null;
        }
    }

    public void execute() throws CloneNotSupportedException{
        Library simulatedLibrary = (Library) library.clone();

        HashMap<String, Book> rentedBooks = library.getRentedBooks();
        int overdue = 0;
        for( Book book : rentedBooks.values()){
            Date returnDate = book.getReturnDate().getTime();
            if(returnDate.compareTo(simulatedLibrary.getPresentDate().getTime()) < 0){
                overdue++;
            }
        }
        LibraryReport simulatedReport = new LibraryReport(simulatedLibrary);
        simulatedReport.execute();
        System.out.println("Overdue Book Count: " + overdue + '\n');
    }
}
