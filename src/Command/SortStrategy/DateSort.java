package Command.SortStrategy;

import Command.Command;
import Command.SortStrategy.SortStrategy;
import Library.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DateSort implements SortStrategy {
    /**
     * Sorts a list of books by date
     * @param books list of books in no order
     * @return list of books sorted by date
     */
    @Override
    public ArrayList<Book> sort(ArrayList<Book> books) {
        Collections.sort(books,Collections.reverseOrder(Comparator.comparing(Book::getDate)));
        return books;
    }
}

