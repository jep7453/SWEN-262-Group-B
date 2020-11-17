package Command;

import Command.SortStrategy.DateSort;
import Command.SortStrategy.SortStrategy;
import Command.SortStrategy.StatusSort;
import Command.SortStrategy.TitleSort;
import Library.*;

import java.util.ArrayList;

public class LibraryBookSearch implements Command {

    private ArrayList<String> search;
    private Library library;

    /**
     * Constructor for LibraryBookSearch commands
     * @param library
     * @param search String of specified criteria for the search
     */
    public LibraryBookSearch(Library library, ArrayList<String> search) {
        this.search = search;
        this.library=library;
    }

    /**
     * Command execute function
     * Parses the search criteria against the list of books in the library catalog, saving and printing those that fit
     */
    public String execute() {
        ArrayList<Book> bookLibrary = new ArrayList<>(library.getBookCollection().values());
        ArrayList<Book> searchResults = new ArrayList<Book>();
        SortStrategy strategy = null;
        for(Book book:bookLibrary) {
            Boolean valid = true;
            int parameterNum = 0;
            for(String parameter : search) {
                if(!parameter.equals("*")) {
                    if(parameterNum==0)
                        if(!parameter.equals(book.getTitle())) {
                            valid=false; }
                    if(parameterNum==1)
                        if(!parameter.equals(book.getAuthorsString())) {
                            valid=false; }
                    if(parameterNum==2)
                        if(!parameter.equals(book.getISBN())) {
                            valid=false; }
                    if(parameterNum==3)
                        if(!parameter.equals(book.getPublisher())) {
                            valid=false; }
                    if(parameterNum==3)
                        if(!parameter.equals(book.getPublisher())) {
                            valid=false; }
                    if(parameterNum==4 && strategy==null) {
                        if (parameter.equals("title"))
                            strategy = new TitleSort();
                        if (parameter.equals("publish-date"))
                            strategy = new DateSort();
                        if (parameter.equals("book-status"))
                            strategy = new StatusSort();
                    }
                }
                parameterNum++;
            }
            if(valid) {
                searchResults.add(book);
            }

            if(strategy!=null)
                searchResults = strategy.sort(searchResults);
        }
        library.setLibrarySearch(searchResults);
        System.out.println("info,"+searchResults.size());
        String returnString = "info,"+searchResults.size() +"\n";
        for(Book foundBook: searchResults) {
            returnString = returnString +( searchResults.indexOf(foundBook) + 1) + "," + Integer.valueOf(foundBook.getCopiesAvailable()) + "," + foundBook + '\n';
            System.out.println(searchResults.indexOf(foundBook) + 1 + "," + Integer.valueOf(foundBook.getCopiesAvailable()) + "," + foundBook);
        }
        return returnString;

    }

}