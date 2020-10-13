package Command;

import Library.*;
import java.util.ArrayList;

/**
 * @author Zuri Shaw
 */

public class LibraryReport implements Command {

    private final Library currentLibrary;
    private final ArrayList<Day> reportDays;

    public LibraryReport(Library library, ArrayList<Day> reportDays){
        this.reportDays = reportDays;
        currentLibrary = library;
    }

    private String getPurchasedString(){
        int purchasedBooks = 0;
        for(int i = 0; i < reportDays.size(); i++){
            Day day = reportDays.get(i);
            purchasedBooks += day.getBooksPurchased();
        }
        return "Number of Books Purchased:" + purchasedBooks + '\n';
    }
    private String getVisitorString(){
        int registeredVisitors = 0;
        for(int i = 0; i < reportDays.size(); i++){
            Day day = reportDays.get(i);
            registeredVisitors += day.getRegisteredVisitors();
        }
        return "Number of Visitors: " + registeredVisitors + '\n';
    }
    private String getBankString(){
        int totalBank = 0;
        for(int i = 0; i < reportDays.size(); i++){
            Day day = reportDays.get(i);
            totalBank += day.getTotalBankAccount();
        }
        return "Fines Collected: " + totalBank + '\n';
    }

    private String getOutstandingString(){
        int totalOutstanding = 0;
        for(Visitor visitor: currentLibrary.getRegisteredVisitors().values()) {
            totalOutstanding+=visitor.getFines();
        }
        return "Fines Outstanding: " + totalOutstanding + '\n';
    }

    private String getCollectionString(){
        int booksOwned = 0;
        for(Book book:currentLibrary.getBookCollection().values()) {
            booksOwned+=book.getCopies();
        }
        return "Number of Books: " + booksOwned + '\n';
    }
    private String getDateString(){
        return "Current Date: " + currentLibrary.getPresentDate().getTime().toString() + '\n';
    }

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
