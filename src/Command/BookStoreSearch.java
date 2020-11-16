package Command;

import Command.SortStrategy.DateSort;
import Command.SortStrategy.SortStrategy;
import Command.SortStrategy.TitleSort;
import Library.*;

import java.util.ArrayList;

public class BookStoreSearch implements Command {

     private ArrayList<String> search;
     private Library library;

    /**
     * Constructor for BookStoreSearch command
     * @param library
     * @param search String of specified criteria for the search
     */
     public BookStoreSearch(Library library, ArrayList<String> search) {
         this.search = search;
         this.library=library;
     }

    /**
     * Command execute function
     * Parses the search criteria against the list of books in the bookstore catalog, saving and printing those that fit
     */
        public String execute() {
            ArrayList<Book> bookLibrary = new ArrayList<>(library.getStoreCollection().values());
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
                            if(parameter.equals("title"))
                                strategy = new TitleSort();
                            if(parameter.equals("publish-date"))
                                strategy = new DateSort();
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
            library.setStoreSearch(searchResults);
            String returnString = "search,"+searchResults.size();
            System.out.println("search,"+searchResults.size());
            for(Book foundBook: searchResults) {
                returnString = returnString + searchResults.indexOf(foundBook) + 1 + "," + foundBook + '\n';
                System.out.println(searchResults.indexOf(foundBook) + 1 + "," + foundBook);
            }
            return returnString;

            }

    }


