import Command.*;
import Library.Library;

import java.util.ArrayList;

public class CommandInterpretter {


    public CommandInterpretter() {

    }

    public static Command interpret(Library library, String request) {
        Command command;
        String[] parts = request.split(",");
        switch (parts[0]) {
            case "register":
               command = new RegisterVisitor(library,parts[1],parts[2],parts[3],parts[4]);
               return command;
            case "purchase":
                ArrayList<Integer> books = new ArrayList<>();
                int iter = 0;
                for(String part: parts) {
                    if(iter>1) {
                        books.add(Integer.valueOf(part));
                    }
                    iter++;
                }
                command = new PurchaseBook(library,Integer.valueOf(parts[1]),books);
                return command;
            case "search":
                ArrayList<String> search = new ArrayList<>();
                iter = 0;
                String authors = "";
                boolean inAuthors = false;
                for(String part: parts) {
                    if(iter>0) {
                        if(part.charAt(0)==('{')) {
                            authors = part.substring(1);
                            if(part.charAt(part.length()-1)!=('}'))
                                inAuthors = true;
                            else
                                search.add(part);
                        }
                        else if(inAuthors) {
                            if(part.charAt(part.length()-1)!=('}')) {
                                authors=authors + part;
                            }
                            else {
                                authors=authors + part.substring(0,part.length()-2);
                                inAuthors=false;
                                search.add(authors);
                            }

                        }
                        else
                            search.add(part);
                    }
                    iter++;
                }
                command = new BookStoreSearch(library,search);
                return command;
            case "info":
                ArrayList<String> info = new ArrayList<>();
                iter = 0;
                authors = "";
                inAuthors = false;
                for(String part: parts) {
                    if(iter>0) {
                        if(part.charAt(0)==('{')) {
                            authors = part.substring(1);
                            if(part.charAt(part.length()-1)!=('}'))
                                inAuthors = true;
                            else
                                info.add(part);
                        }
                        else if(inAuthors) {
                            if(part.charAt(part.length()-1)!=('}')) {
                                authors=authors + part;
                            }
                            else {
                                authors=authors + part.substring(0,part.length()-2);
                                inAuthors=false;
                                info.add(authors);
                            }

                        }
                        else
                            info.add(part);
                    }
                    iter++;
                }
                command = new LibraryBookSearch(library,info);
                return command;
            default:
                return null;
        }
    }
}
