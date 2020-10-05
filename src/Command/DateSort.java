package Command;

import Library.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DateSort implements SortStrategy {
    @Override
    public ArrayList<Book> sort(ArrayList<Book> books) {
        Collections.sort(books,Collections.reverseOrder(Comparator.comparing(Book::getDate)));
        return books;
    }
}