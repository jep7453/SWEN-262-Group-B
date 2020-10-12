package Command;

import Library.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BorrowedBooksSearch implements Command {

    private int id;
    private Library library;
    private Visitor visitor;
    private ArrayList<CheckedBook> rentedBooks;


    public BorrowedBooksSearch(Library library,int id) {
        this.id = id;
        this.library=library;
        this.rentedBooks=library.getRentedBooks();
        this.visitor=library.getRegisteredVisitors().get(id);
    }

    public void execute() {
        if (!library.getRegisteredVisitors().containsValue(visitor)) {
            System.out.println("borrowed,invalid-visitor-id;");
        }
        else {
            ArrayList<CheckedBook> searchResults = new ArrayList<>();
            for (CheckedBook book : rentedBooks) {
                if (book.getID() == id) {
                    searchResults.add(book);
                }
            }
            library.setBorrowedSearch(searchResults);
            library.setBorrowedSearchUser(id);
            System.out.println("borrowed,"+searchResults.size());
            for(CheckedBook book :searchResults) {
                GregorianCalendar checkOutDate = (GregorianCalendar) book.getDueDate().clone();
                checkOutDate.add(Calendar.DAY_OF_MONTH,-7);
                System.out.println(searchResults.indexOf(book)+1+","+book.getBook().getISBN()+"," +
                        book.getBook().getTitle()+","+checkOutDate.getTime());
            }

        }

    }

}