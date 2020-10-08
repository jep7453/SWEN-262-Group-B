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
    private GregorianCalendar presentDate;
    private ArrayList<Day> history;

    public Library() throws IOException {
        this.history = new ArrayList<>();
        this.registeredVisitors = new ArrayList<>();
        this.totalBankAccount = 0;
        this.bookCollection = new HashMap<>();
        this.rentedBooks = new HashMap<>();
        this.presentDate = new GregorianCalendar();
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
        bookCollection.put(title, book);
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
    public GregorianCalendar getPresentDate(){
        return this.presentDate;
    }
    public int getTotalBookCount(){
        return this.bookCollection.size();
    }
    public int getTotalBankAccount(){
        return totalBankAccount;
    }
    public ArrayList<Visitor> getRegisteredVisitors(){
        return registeredVisitors;
    }
    public void setSimulatedTime(GregorianCalendar simulatedTime){
        this.presentDate = simulatedTime;
    }
    public void addToHistory(Day currentDay){
        this.history.add(currentDay);
    }
    public ArrayList<Day> getHistory(){
        return this.history;
    }
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
