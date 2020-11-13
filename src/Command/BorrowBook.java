package Command;

import Library.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BorrowBook implements Command {
    private ArrayList<Integer> books;
    private Library library;
    private int id;
    private GregorianCalendar dueDate;
    private GregorianCalendar currentDate;

    /**
     * Constructor for BorrowBook command
     * @param library
     * @param id
     * @param books list of books to borrow by ID from previous LibraryBookSearch
     */
    public BorrowBook(Library library, int id, ArrayList<Integer> books) {
        this.id = id;
        this.books = books;
        this.library = library;
        this.currentDate = library.getPresentDate();
        this.dueDate= (GregorianCalendar) currentDate.clone();
        dueDate.add(Calendar.DAY_OF_MONTH,7);
    }

    /**
     * Command execute function
     * Creates a CheckedBook for the requested books to borrow, adds it to the library and updates the user
     */
    public void execute() {
        ArrayList<Book> librarySearch = library.getLibrarySearch();
        ArrayList<Integer> invalidBooks = new ArrayList<>();
        if(!library.borrowBook()) {
            System.out.println("borrow,library-closed;");
        }
        else {
            for (Integer book : books) {
                if (book > librarySearch.size()) {
                    invalidBooks.add(book);
                } else if (librarySearch.get(book - 1).getCopiesAvailable() < 1) {
                    invalidBooks.add(book);
                }
            }
            if (!library.getRegisteredVisitors().containsKey(id)) {
                System.out.println("borrow,invalid-visitor-id;");
            } else if (library.getRegisteredVisitors().get(id).getFines() != 0) {
                System.out.println("borrow,outstanding-fine," + library.getRegisteredVisitors().get(id).getFines() + ";");
            } else if (library.getRegisteredVisitors().get(id).getBorrowedBooks() >= 5) {
                System.out.println("borrow,book-limit-exceeded;");
            } else if (invalidBooks.size() != 0) {
                System.out.print("borrow,invalid-book-id");
                for (Integer book : invalidBooks) {
                    System.out.print("," + book);
                }
            } else {
                for (Integer book : books) {
                    Book borrowedBook = librarySearch.get(book - 1);
                    borrowedBook.borrowCopy(1);
                    CheckedBook checkedOutBook = new CheckedBook(borrowedBook, id, dueDate);
                    library.getRentedBooks().add(checkedOutBook);
                    library.getRegisteredVisitors().get(id).addBorrowedBooks(1);
                }
                System.out.println("borrow," + dueDate.getTime());
            }
        }

    }
}