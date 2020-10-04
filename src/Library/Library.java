package Library;


import java.util.*;

import Command.Time;


public class Library {

    private static ArrayList<Visitor> registeredVisitors;
    private int totalBookCount;
    private int totalVisitorCount;
    public int totalBankAccount;
    private HashMap<String, Book> bookCollection;
    private HashMap<String,Book> rentedBooks;
    private GregorianCalendar presentDate;

    public Library() {
        this.registeredVisitors = new ArrayList<>();
        this.totalBankAccount = 0;
        this.totalBookCount = 0;
        this.totalVisitorCount = 0;
        this.bookCollection = new HashMap<>();
        this.rentedBooks = new HashMap<>();
        this.presentDate = new GregorianCalendar();
        presentDate.setTime(new Date());
    }

    public static int visitorsLength() {
        if(registeredVisitors ==null)
            return 0;
        return registeredVisitors.size();
    }

    public void checkoutBook(Book book){
        GregorianCalendar returnDate = new GregorianCalendar();
        returnDate.setTime(new Date());
        returnDate.set(Calendar.MONTH, returnDate.get(Calendar.MONTH) + 1);//for now just set the return date for 1 month in advance
        book.setReturnDate(returnDate);
        this.bookCollection.remove(book.getTitle());
        this.rentedBooks.put(book.getTitle(), book);
    }
    public void returnBook(Book book){
        book.setReturnDate(null);
        this.rentedBooks.remove(book.getTitle());
        this.bookCollection.put(book.getTitle(), book);
    }

    public HashMap<String, Book> getRentedBooks(){return this.rentedBooks;}
    public HashMap<String, Book> getBookCollection(){return this.bookCollection;}
    public GregorianCalendar getPresentDate(){return this.presentDate;}
    public int getTotalBookCount(){return this.bookCollection.size();}
    public int getTotalBankAccount(){return totalBankAccount;}
    public static ArrayList<Visitor> getRegisteredVisitors(){
        return registeredVisitors;
    }
    public Object clone() throws CloneNotSupportedException{ return super.clone(); }

}
