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
    public void execute(){
        String reportString = "";
        reportString = reportString + getVisitorString();
        reportString = reportString + getBookString();
        reportString = reportString + getBankString();
        reportString = reportString + getRentedString();
        reportString = reportString + getCollectionString();
        System.out.println(reportString);
    }
}
