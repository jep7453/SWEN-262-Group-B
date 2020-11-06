package Command.SortStrategy;

import Library.Book;

import java.util.ArrayList;

public interface SortStrategy {
    /**
     * Sorts a list of books
     * @param books list of unsorted books
     * @return list of books sorted in the specified manner
     */
    ArrayList<Book> sort(ArrayList<Book> books);
}
