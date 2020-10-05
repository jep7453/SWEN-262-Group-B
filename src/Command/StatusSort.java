package Command;

import Library.Book;

import java.util.ArrayList;

public class StatusSort implements SortStrategy {
    @Override
    public ArrayList<Book> sort(ArrayList<Book> books) {
        for(Book book :books){
            if(book.getCopiesAvailable()<1) {
                books.remove(book);
            }
        }
        return books;
    }
}