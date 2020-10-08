package Library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class Library implements Cloneable{

    private ArrayList<Book> storeSearch;
    private static ArrayList<Visitor> registeredVisitors;
    public int totalBankAccount;
    private HashMap<String, Book> bookCollection;
    private HashMap<String,Book> rentedBooks;
    private HashMap<String,Book> storeCollection;
    private GregorianCalendar presentDate;
    private ArrayList<Day> history;
    private GregorianCalendar currentDate;

    public Library() throws IOException {
        GregorianCalendar startDate = new GregorianCalendar();
        startDate.setTime(new Date());
        this.currentDate = startDate;
        this.registeredVisitors = new ArrayList<>();
        this.totalBankAccount = 0;
        this.bookCollection = new HashMap<>();
        this.storeCollection = new HashMap<>();
        this.rentedBooks = new HashMap<>();
        this.presentDate = new GregorianCalendar();
        this.history = new ArrayList<>();
        presentDate.setTime(new Date());
        BufferedReader books = new BufferedReader(new FileReader("src/books.txt"));
        String book;
        while ((book = books.readLine()) != null)
            this.fillBookStore(book);
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
    public void returnBook(Book book) {
        book.setReturnDate(null);
        this.rentedBooks.remove(book.getTitle());
        this.bookCollection.put(book.getTitle(), book);
    }

    public void addBook(Book book) {
        bookCollection.put(book.getTitle(), book);
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
        storeCollection.put(title, book);
    }
    public ArrayList<Day> getHistory(){
        return this.history;
    }
    public void addToHistory(Day day){
        this.history.add(day);
    }
    public void setSimulatedTime(GregorianCalendar simulatedTime){
        this.currentDate = simulatedTime;
    }
    public ArrayList<Book> getStoreSearch() {
        return storeSearch;
    }
    public void setStoreSearch(ArrayList<Book> searchResults) {
        storeSearch = searchResults;
    }
    public HashMap<String, Book> getRentedBooks(){
        return this.rentedBooks;
    }
    public HashMap<String, Book> getBookCollection(){
        return this.bookCollection;
    }
    public HashMap<String, Book> getStoreCollection(){
        return this.storeCollection;
    }
    public GregorianCalendar getPresentDate(){
        return this.presentDate;
    }
    public ArrayList<Visitor> getRegisteredVisitors(){
        return registeredVisitors;
    }
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
