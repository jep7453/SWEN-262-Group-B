package Command;

import Library.*;
import java.util.ArrayList;

/**
 * @author Zuri Shaw
 */

public class LibraryReport implements Command {

    private final Library currentLibrary;
    private final ArrayList<Day> reportDays;

    /**
     * Constructor for LibraryBookSearch commands
     * @param library
     * @param reportDays list of Day object report was requested for
     */
    public LibraryReport(Library library, ArrayList<Day> reportDays){
        this.reportDays = reportDays;
        currentLibrary = library;
    }

    /**
     * Returns the amount of purchased books for the report
     * @return amount of books purchased
     */
    private String getPurchasedString(){
        int purchasedBooks = 0;
        for(int i = 0; i < reportDays.size(); i++){
            Day day = reportDays.get(i);
            purchasedBooks += day.getBooksPurchased();
        }
        return "Number of Books Purchased:" + purchasedBooks + '\n';
    }

    /**
     * Returns the amount of registered visitors for the report
     * @return amount of registered visitors
     */
    private String getVisitorString(){
        int registeredVisitors = 0;
        for(int i = 0; i < reportDays.size(); i++){
            Day day = reportDays.get(i);
            registeredVisitors += day.getRegisteredVisitors();
        }
        return "Number of Visitors: " + registeredVisitors + '\n';
    }

    /**
     * Returns the amount of fines collected for the report
     * @return amount of fines collected
     */
    private String getBankString(){
        int totalBank = 0;
        for(int i = 0; i < reportDays.size(); i++){
            Day day = reportDays.get(i);
            totalBank += day.getTotalBankAccount();
        }
        return "Fines Collected: " + totalBank + '\n';
    }

    /**
     * Returns the amount of outstanding fines for the report
     * @return amount of outstanding fines
     */
    private String getOutstandingString(){
        int totalOutstanding = 0;
        for(Visitor visitor: currentLibrary.getRegisteredVisitors().values()) {
            totalOutstanding+=visitor.getFines();
        }
        return "Fines Outstanding: " + totalOutstanding + '\n';
    }

    /**
     * Returns the amount of books the the library catalog for the report
     * @return amount of books the the library catalog
     */
    private String getCollectionString(){
        int booksOwned = 0;
        for(Book book:currentLibrary.getBookCollection().values()) {
            booksOwned+=book.getCopies();
        }
        return "Number of Books: " + booksOwned + '\n';
    }

    /**
     * Returns current date for the report
     * @return current date
     */
    private String getDateString(){
        return "Current Date: " + currentLibrary.getPresentDate().getTime().toString() + '\n';
    }

    /**
     * Returns the average length of visit for the report
     * @return average length of visit
     */
    private String getVisitAverage(){
        int seconds=0;
        int visits=0;
        for(int i = 0; i < reportDays.size(); i++){
            Day day = reportDays.get(i);
            seconds += day.getVisitSeconds();
            visits +=day.getVisitAmount();
        }
        if(visits!=0) {
            seconds = seconds / (visits);
        }
        int secondsDuration = seconds%60;
        int minutes = seconds/60;
        int minutesDuration= minutes%60;
        int hours = minutes/60;
        return "Average Length of Visit:" + hours+":"+minutesDuration+":"+secondsDuration + '\n';
    }

    /**
     * Command execute function
     * Prints out the statistics recorded by the library from the specified days
     */
    public void execute(){
        System.out.println(currentLibrary.getHistory().size());
        String reportString = "";
        reportString += getDateString();
        reportString += getCollectionString();
        reportString += getVisitorString();
        reportString += getVisitAverage();
        reportString += getPurchasedString();
        reportString += getBankString();
        reportString += getOutstandingString();



        System.out.println(reportString);
    }
}
