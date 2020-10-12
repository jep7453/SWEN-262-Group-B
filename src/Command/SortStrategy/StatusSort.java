package Command.SortStrategy;

import Command.Command;
import Command.SortStrategy.SortStrategy;
import Library.Book;

import java.util.ArrayList;

public class StatusSort implements SortStrategy {
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
