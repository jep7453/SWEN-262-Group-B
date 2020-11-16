package Command;

import Library.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PurchaseBook implements Command {

    private int quantity;
    private ArrayList<Integer> books;
    private Library library;

    /**
     * Constructor for PurchaseBook commands
     * @param library
     * @param quantity amount of each book to buy
     * @param books list of books to buy by ID from previous BookStoreSearch
     */
    public PurchaseBook(Library library, int quantity, ArrayList<Integer> books) {
        this.quantity = quantity;
        this.books = books;
        this.library=library;
    }

    /**
     * Command execute function
     * Adds books to the library catalog from the specified ID from the last BookStoreSearch
     */
    public String execute() {
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
        String returnSting ="purchase,success,"+purchased.size();
        System.out.println("purchase,success,"+purchased.size());
        for(Book purchasedBook: purchased) {
            returnSting = returnSting + purchasedBook + "," + String.valueOf(quantity)+ '\n';
            System.out.println(purchasedBook + "," + String.valueOf(quantity));
        }
        return returnSting;
    }
}
