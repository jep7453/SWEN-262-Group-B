package Command;

import Library.*;

import java.util.ArrayList;

 public class BookStoreSearch implements Command {

     private ArrayList<String> search;
     private Library library;


     public BookStoreSearch(Library library, ArrayList<String> search) {
         this.search = search;
         this.library=library;
     }

        public void execute() {
            ArrayList<Book> bookLibrary = library.getBookStore();
            ArrayList<Book> searchResults = new ArrayList<Book>();
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
                                String lol = book.getAuthorsString();
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
                    }
                    parameterNum++;
                }
                if(valid) {
                    searchResults.add(book);
                }
            }
            library.setStoreSearch(searchResults);
            System.out.println("search,"+searchResults.size());
            for(Book foundBook: searchResults)
                System.out.println(searchResults.indexOf(foundBook)+1+",,"+foundBook);

            }

    }

