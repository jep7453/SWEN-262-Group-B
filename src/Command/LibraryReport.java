package Command;
import Library.Visitor;
import Library.Library;

public class LibraryReport implements Command {
    private Library currentLibrary;
    private int bookCount;
    private int bankAccount;
    private int visitorCount;
    private int averageTimeSpent;//implementation later

    public LibraryReport(Library library){
        currentLibrary = library;
    }

    private String getVisitorString(){
       return "Visitor Count: " + currentLibrary.visitorsLength() + '\n';
    }
    /*
    private String getBookString(){
        return "Book Count: " + currentLibrary.getTotalBookCount() + '\n';
    }

     */
    private String getBankString(){return "Money: " + currentLibrary.getTotalBankAccount() + '\n';}

    public void execute(){
        String reportString = "";
        reportString = getVisitorString();
        //reportString = getBookString();
        //reportString = getBankString();
        System.out.println(reportString);
    }
}
