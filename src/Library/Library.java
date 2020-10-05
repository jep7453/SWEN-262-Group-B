package Library;

import java.util.*;

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
    public void fillBookStore(String bookString) {
        String[] bookSections = bookString.split(",");
        int index =0;
        int checkIndex =2;
        String ISBN = bookSections[index];
        index++;
        String nextSection = bookSections[index];
        String title = "";
        while(nextSection.charAt(nextSection.length()-1)!='"') {
            title += nextSection+ ",";
            index++;
            checkIndex++;
            nextSection = bookSections[index];
        }
        title +=nextSection;
        index++;
        ArrayList<String> authors = new ArrayList<>();
        nextSection = bookSections[index];
        while(nextSection.charAt(nextSection.length()-1)!=('}')) {
            if(index==checkIndex)
                authors.add(nextSection.substring(1));
            else
                authors.add(nextSection);
            index++;
            nextSection = bookSections[index];
        }
        if(index==checkIndex)
            authors.add(nextSection.substring(1,nextSection.length()-1));
        else
            authors.add(nextSection.substring(0,nextSection.length()-1));
        index++;
        nextSection = bookSections[index];
        String publisher = "";
        while(nextSection.charAt(nextSection.length()-1)!='"') {
            publisher += nextSection;
            index++;
            nextSection = bookSections[index];
        }
        publisher += nextSection;
        String date = bookSections[index+1];
        int pages = Integer.valueOf(bookSections[index+2]);
        Book book = new Book(title, authors, ISBN, publisher, date, pages);
        bookStore.add(book);
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
