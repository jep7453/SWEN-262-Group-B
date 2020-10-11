package Command.SortStrategy;

import Command.Command;
import Library.Book;

import java.util.ArrayList;

public interface SortStrategy {
    ArrayList<Book> sort(ArrayList<Book> books);
}
