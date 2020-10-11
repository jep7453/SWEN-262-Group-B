package Command;

import Library.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BorrowBook implements Command {
    private ArrayList<Integer> books;
    private Library library;
    private int id;
    private GregorianCalendar dueDate;
    private GregorianCalendar currentDate;


    public BorrowBook(Library library, int id, ArrayList<Integer> books) {
        this.id = id;
        this.books = books;
        this.library = library;
        this.currentDate = library.getPresentDate();
        this.dueDate= (GregorianCalendar) currentDate.clone();
        dueDate.set(Calendar.DAY_OF_MONTH,dueDate.get(Calendar.DAY_OF_MONTH+7));
    }

    public void execute() {
        ArrayList<Book> bookLibrary = new ArrayList<>(library.getBookCollection().values());
        ArrayList<Book> librarySearch = library.getLibrarySearch();
        ArrayList<Book> borrowed = new ArrayList<>();
        ArrayList<Integer> invalidBooks = new ArrayList<>();
        for(Integer book : books) {
            if(book>librarySearch.size()) {
                invalidBooks.add(book);
            }
        }
        if(!library.getRegisteredVisitors().containsKey(id)) {
            System.out.println("invalid-vsitor-id;");
        }
        else if(library.getRegisteredVisitors().get(id).getFines()!=0) {
            System.out.println("borrow,outstanding-fine,"+library.getRegisteredVisitors().get(id).getFines()+";");
        }
        else if(library.getRegisteredVisitors().get(id).getBorrowedBooks()>=5) {
            System.out.println("borrow,book-limit-exceeded;");
        }
        else if(invalidBooks.size()!=0) {
            System.out.print("borrow,invalid-book-id");
            for(Integer book: invalidBooks) {
                System.out.print("," +book);
            }
        }
        else {
            for (Integer book : books) {
                Book borrowedBook = librarySearch.get(book - 1);
                borrowedBook.borrowCopy(1);
                CheckedBook checkedOutBook = new CheckedBook(borrowedBook, id, dueDate);
                library.getRentedBooks().put(borrowedBook.getTitle(), checkedOutBook);
                library.getRegisteredVisitors().get(id).addBorrowedBooks(1);
            }
            System.out.println("borrow,"+dueDate.getTime());
        }

    }
}