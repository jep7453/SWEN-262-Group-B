package Command;

import Library.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PurchaseBook implements Command {

    private int quantity;
    private ArrayList<String> books;
    private Library library;


    public PurchaseBook(Library library, int quantity, ArrayList<String> books) {
        this.quantity = quantity;
        this.books = books;
        this.library=library;
    }

    public void execute() {
        ArrayList<Book> bookLibrary = library.getBooks();
        ArrayList<Book> bookStore = library.getBookStore();
        for(String book : books) {
            for(Book storeBook : bookStore) {
                if (storeBook.getISBN().equals(book)) {
                    storeBook.addCopies(quantity);
                    if(!bookLibrary.contains(storeBook)) {
                        bookLibrary.add(storeBook);
                    }
                }
            }

        }
    }
}
