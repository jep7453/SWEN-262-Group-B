package Command;

import Library.Book;

import java.util.ArrayList;

public interface SortStrategy {
    ArrayList<Book> sort(ArrayList<Book> books);
}