package Command;

import Library.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReturnBook implements Command {
    private ArrayList<Integer> books;
    private Library library;
    private int id;
    private GregorianCalendar dueDate;
    private GregorianCalendar currentDate;

    /**
     * Constructor for ReturnBook commands
     * @param library
     * @param id
     * @param books list of books to return by ID from previous BorrowedBookSearch
     */
    public ReturnBook(Library library, int id, ArrayList<Integer> books) {
        this.id = id;
        this.books = books;
        this.library = library;
        this.currentDate = library.getPresentDate();
    }

    /**
     * Command execute function
     * Returns checked out books for a user, adding any overdue fines to them
     */
    public String execute() {
        ArrayList<CheckedBook> borrowedSearch = library.getBorrowedSearchCache();
        ArrayList<CheckedBook> overDue = new ArrayList<>();
        ArrayList<Integer> overDueIDs = new ArrayList<>();
        ArrayList<Integer> invalidBooks = new ArrayList<>();
        for(Integer book : books) {
            if(book>borrowedSearch.size()) {
                invalidBooks.add(book);
            }
        }

        if(!library.getRegisteredVisitors().containsKey(id)||id!= library.getBorrowedSearchUser()) {
            System.out.println("return,invalid-visitor-id;");
            return "return,invalid-visitor-id";
        }

        else if(invalidBooks.size()!=0) {
            String returnString ="return,invalid-book-id;";
            System.out.print("return,invalid-book-id;");
            for(Integer book: invalidBooks) {
                returnString=returnString+"," +book;
                System.out.print("," +book);
            }
            return returnString;
        }
        else {
            for (Integer book : books) {
                //Add book back to library
                CheckedBook borrowedBook = borrowedSearch.get(book - 1);
                borrowedBook.getBook().returnCopy(1);
                if(borrowedBook.getFine()!=0) {
                    overDue.add(borrowedBook);
                    overDueIDs.add(book);

                }
                library.getRentedBooks().remove(borrowedBook);
                library.getRegisteredVisitors().get(id).removeBorrowedBooks(1);
            }
            if(overDue.size()==0) {
                System.out.println("return,success;");
                return "return,success;";
            }
            else {
                int fines = 0;
                for(CheckedBook book : overDue) {
                    Visitor visitor = library.getRegisteredVisitors().get(book.getID());
                    fines+=book.getFine();
                    visitor.returnedFinedBook(book.getFine());
                }
                String returnString = "return,overdue,"+fines;
                System.out.print("return,overdue,"+fines);
                for(Integer book: overDueIDs) {
                    System.out.print("," +book);
                    returnString=returnString+"," +book;
                }
                return returnString;
            }
        }

    }
}