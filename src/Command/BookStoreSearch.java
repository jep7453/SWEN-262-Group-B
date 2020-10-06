package Command;

import Library.*;

import java.util.ArrayList;
import java.util.Collection;

public class BookStoreSearch implements Command {

     private ArrayList<String> search;
     private Library library;


     public BookStoreSearch(Library library, ArrayList<String> search) {
         this.search = search;
         this.library=library;
     }

        public void execute() {
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
            System.out.println("search,"+searchResults.size());
            for(Book foundBook: searchResults)
                System.out.println(searchResults.indexOf(foundBook)+1+",,"+foundBook);

            }

    }


