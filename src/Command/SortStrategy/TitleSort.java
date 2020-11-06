package Command.SortStrategy;

import Command.Command;
import Command.SortStrategy.SortStrategy;
import Library.*;
import java.util.*;
import java.util.ArrayList;

public class TitleSort implements SortStrategy {
    /**
     * Sorts a list of books by their titles
     * @param books list of books in no order
     * @return list of books sorted in alphabetical order of title
     */
    @Override
    public ArrayList<Book> sort(ArrayList<Book> books) {
        Collections.sort(books, Comparator.comparing(Book::getTitle));
        return books;
    }
}
