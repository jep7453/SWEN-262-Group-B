package Command;

import Library.Library;
import Library.Day;
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
        return "Books Purchased: " + purchasedBooks + '\n';
    }
    private String getVisitorString(){
        int registeredVisitors = 0;
        for(int i = 0; i < reportDays.size(); i++){
            Day day = reportDays.get(i);
            registeredVisitors += day.getRegisteredVisitors();
        }
        return " Registered Visitor Count: " + registeredVisitors + '\n';
    }
    private String getBankString(){
        int totalBank = 0;
        for(int i = 0; i < reportDays.size(); i++){
            Day day = reportDays.get(i);
            totalBank += day.getTotalBankAccount();
        }
        return "Money: " + totalBank + '\n';
    }
    private String getRentedString(){
        int rentedBooks = 0;
        for(int i = 0; i < reportDays.size(); i++){
            Day day = reportDays.get(i);
            rentedBooks += day.getRentedBooks();
        }
        return "Amount of Rented Books: " + rentedBooks + '\n';
    }
    private String getReturnedBooksString(){
        int returnedBooks = 0;
        for(int i = 0; i < reportDays.size(); i++){
            Day day = reportDays.get(i);
            returnedBooks += day.getBooksReturned();
        }
        return "Amount of Returned Books: " + returnedBooks + '\n';
    }
    private String getCollectionString(){
        return "Current Collection Size: " + currentLibrary.getBookCollection().size() + '\n';
    }
    private String getDateString(){
        return "Current Date: " + currentLibrary.getPresentDate().getTime().toString() + '\n';
    }
    public void execute(){
        String reportString = "";
        reportString += getDateString();
        reportString += getVisitorString();
        reportString += getBankString();
        reportString += getRentedString();
        reportString += getCollectionString();
        reportString += getPurchasedString();
        reportString += getReturnedBooksString();
        System.out.println(reportString);
    }
}
