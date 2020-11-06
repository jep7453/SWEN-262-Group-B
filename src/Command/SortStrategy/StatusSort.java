package Command.SortStrategy;

import Command.Command;
import Command.SortStrategy.SortStrategy;
import Library.Book;

import java.util.ArrayList;

public class StatusSort implements SortStrategy {
    /**
     * Sorts a list of books by removing any unavailable ones
     * @param books list of books in no order
     * @return list of books in no order, but only ones with available copies
     */
    @Override
    public ArrayList<Book> sort(ArrayList<Book> books) {
        ArrayList<Book> removeBooks = new ArrayList<>();
        for(Book book :books){
            if(book.getCopiesAvailable()<1) {
                removeBooks.add(book);
            }
        }
        books.removeAll(removeBooks);
        return books;
    }
}
