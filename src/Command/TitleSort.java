package Command;

import Library.*;
import java.util.*;
import java.util.ArrayList;

public class TitleSort implements SortStrategy{
    @Override
    public ArrayList<Book> sort(ArrayList<Book> books) {
        Collections.sort(books, Comparator.comparing(Book::getTitle));
        return books;
    }
}
