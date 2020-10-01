package Library;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashMap;

import Command.Time;

public class Library {

    private static ArrayList<Visitor> registeredVisitors;
    public Time currentTime;
    private int totalBookCount;
    private int totalVisitorCount;
    public int totalBankAccount;
    //private HashMap<String, Book> bookCollection;
    //private HashMap<String,Book> rentedBooks;

    //public int getCollectionCount(){return bookCollection.size();}
    //public int getTotalVisitorCount(){return totalVisitorCount;}
    //public int getRentedCount(){return rentedBooks.size();}


    public int getTotalBankAccount(){return totalBankAccount;}

    public Library() {
        this.registeredVisitors = new ArrayList<>();
        this.totalBankAccount = 0;
        this.totalBookCount = 0;
        this.totalVisitorCount = 0;
        //this.bookCollection = new ArrayList<>();
        //this.rentedBooks = new ArrayList<>();
    }

    public static int visitorsLength() {
        if(registeredVisitors ==null)
            return 0;
        return registeredVisitors.size();
    }
    /*
    public void checkoutBook(Book book){
        this.bookCollection.remove(book.title);
        this.rentedBooks.add(book.title, book);
    }
    public void returnBook(Book book){
        this.rentedBooks.remove(book.title);
        this.bookCollection.add(book.title, book);
    }
     */

    public static ArrayList<Visitor> getRegisteredVisitors(){
        return registeredVisitors;
    }
    //public ArrayList<Book> getRentedBooks(){return rentedBooks;}
    //public ArrayList<Book> getBookCollection(){return bookCollection;}
    public Object clone() throws CloneNotSupportedException{ return super.clone(); }

}
