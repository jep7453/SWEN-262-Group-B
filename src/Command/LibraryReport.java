package Command;

import Library.Library;

/**
 * @author Zuri Shaw
 */

public class LibraryReport implements Command {

    private final Library currentLibrary;

    public LibraryReport(Library library){
        currentLibrary = library;
    }

    private String getVisitorString(){
        return "Visitor Count: " + currentLibrary.getTotalBookCount() + '\n';
    }
    private String getBookString(){
        return "Book Count: " + currentLibrary.getTotalBookCount() + '\n';
    }
    private String getBankString(){
        return "Money: " + currentLibrary.getTotalBankAccount() + '\n';
    }
    private String getRentedString(){
        return "Amount of Rented Books: " + currentLibrary.getRentedBooks().size() + '\n';
    }
    private String getCollectionString(){
        return "Current Collection Size: " + currentLibrary.getBookCollection().size() + '\n';
    }
    private String getDateString(){
        return "Current Date: " + currentLibrary.getPresentDate().getTime().toString() + '\n';
    }
    public void execute(){
        String reportString = "";
        reportString += getVisitorString();
        reportString += getBookString();
        reportString += getBankString();
        reportString += getRentedString();
        reportString += getCollectionString();
        reportString += getDateString();
        System.out.println(reportString);
    }
}
