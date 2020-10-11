
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


    public ReturnBook(Library library, int id, ArrayList<Integer> books) {
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
        ArrayList<Book> returned = new ArrayList<>();
        ArrayList<Integer> invalidBooks = new ArrayList<>();
        for(Integer book : books) {
            if(book>librarySearch.size()) {
                invalidBooks.add(book);
            }
        }

        if(library.getRegisteredVisitors().get(id).getFines()!=0) {
            System.out.println("return,overdue,"+library.getRegisteredVisitors().get(id).getFines()+","+library.getRegisteredVisitors().get(id).getBorrowedBooks()";");
        }

        else if(!library.getRegisteredVisitors().containsKey(id)) {
            System.out.println("borrow,invalid-vsitor-id;");
        }

        else if(invalidBooks.size()!=0) {
            System.out.print("return,invalid-book-id;");
            for(Integer book: invalidBooks) {
                System.out.print("," +book);
            }
        }
        else {
            for (Integer book : books) {
                //Add book back to library
                Book borrowedBook = librarySearch.get(book - 1);
                borrowedBook.borrowCopy(1);
                CheckedBook checkedOutBook = new CheckedBook(borrowedBook, id, dueDate);
                library.getRentedBooks().put(borrowedBook.getTitle(), checkedOutBook);
                library.getRegisteredVisitors().get(id).addBorrowedBooks(1);
            }
            System.out.println("return,success;");
        }

    }
}