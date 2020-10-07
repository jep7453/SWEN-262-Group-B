package Command;

import Library.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PurchaseBook implements Command {

    private int quantity;
    private ArrayList<Integer> books;
    private Library library;


    public PurchaseBook(Library library, int quantity, ArrayList<Integer> books) {
        this.quantity = quantity;
        this.books = books;
        this.library=library;
    }

    public void execute() {
        ArrayList<Book> bookLibrary = new ArrayList<>(library.getBookCollection().values());
        ArrayList<Book> bookStore = library.getStoreSearch();
        ArrayList<Book> purchased= new ArrayList<Book>();
        for(Integer book : books) {
            Book storeBook = bookStore.get(book-1);
            purchased.add(storeBook);
            storeBook.addCopies(quantity);
            if(!bookLibrary.contains(storeBook)) {
                library.getBookCollection().put(storeBook.getTitle(),storeBook);
            }
        }
        System.out.println("purchase,success,"+purchased.size());
        for(Book purchasedBook: purchased)
            System.out.println(purchasedBook+","+ String.valueOf(quantity));
    }
}
