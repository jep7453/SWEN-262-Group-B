package Library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class Library implements Cloneable{

    private ArrayList<Book> librarySearchCache;
    private ArrayList<Book> storeSearchCache;
    private HashMap<Integer,Visitor> registeredVisitors;
    public int totalBankAccount;
    private HashMap<String, Book> bookCollection;
    private ArrayList<CheckedBook> rentedBooks;
    private ArrayList<CheckedBook> borrowedSearchCache;
    private int borrowedSearchUser;
    private HashMap<String,Book> storeCollection;
    private GregorianCalendar presentDate;
    private ArrayList<Day> history;
    private LibraryState state;
    private ArrayList<Integer> visitAverageSec;

    public Library(Day startDay) throws IOException {
        this.registeredVisitors = new HashMap<>();
        this.totalBankAccount = 0;
        this.bookCollection = new HashMap<>();
        this.storeCollection = new HashMap<>();
        this.rentedBooks = new ArrayList<>();
        this.presentDate = new GregorianCalendar();
        this.history = new ArrayList<>();
        this.visitAverageSec = new ArrayList<>();
        presentDate.setTime(new Date());
        history.add(startDay);
        BufferedReader books = new BufferedReader(new FileReader("src/books.txt"));
        String book;
        while ((book = books.readLine()) != null)
            this.fillBookStore(book);
        updateState();
    }

    public int visitorsLength() {
        if(registeredVisitors ==null)
            return 0;
        return registeredVisitors.size();
    }

    public void clearFines() {
        for (Visitor visitor:registeredVisitors.values()) {
            visitor.clearFines();
        }
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

    public boolean borrowBook() {
        return state.borrowBook();
    }

    public void updateState() {
        if(presentDate.get(Calendar.HOUR)+(presentDate.get(Calendar.AM_PM)*12)>=8&&presentDate.get(Calendar.HOUR)+(presentDate.get(Calendar.AM_PM)*12)<19) {
            state = new OpenLibrary(this);
        }
        else {
            state = new ClosedLibrary(this);
        }
    }

    public void checkVisits() {
        state.checkVisits();
    }

    public boolean startVisit() {
        return state.startVisit();
    }

    public ArrayList<Day> getHistory(){

        return this.history;
    }
    public void addToHistory(Day day){
        this.history.add(day);
    }
    public void setSimulatedTime(Day newDayObj){
        this.presentDate = newDayObj.getCalender();
        addToHistory(newDayObj);
    }
    public void setSimulatedTime(GregorianCalendar cal){
        this.presentDate = cal;
    }

    public void clearVisits() {
        visitAverageSec.clear();
    }

    public void addVisit(int length) {
        visitAverageSec.add(length);
    }

    public int getVisitInSeconds() {
        int average = 0;
        for (int visit : visitAverageSec) {
            average += visit;
        }
        return average;

    }

    public int getVisitNum() {
        return visitAverageSec.size();
    }


    public int getBorrowedSearchUser() {return borrowedSearchUser;}
    public void setBorrowedSearchUser(int user) { borrowedSearchUser =user;}
    public ArrayList<CheckedBook> getBorrowedSearchCache() {
        return borrowedSearchCache;
    }
    public void setBorrowedSearch(ArrayList<CheckedBook> searchResults) {
        borrowedSearchCache = searchResults;
    }
    public ArrayList<Book> getStoreSearch() {
        return storeSearchCache;
    }
    public void setStoreSearch(ArrayList<Book> searchResults) {
        storeSearchCache = searchResults;
    }
    public void setLibrarySearch(ArrayList<Book> searchResults) {
        librarySearchCache = searchResults;
    }
    public ArrayList<Book> getLibrarySearch() {
        return librarySearchCache;
    }
    public ArrayList<CheckedBook> getRentedBooks(){
        return this.rentedBooks;
    }
    public HashMap<String, Book> getBookCollection(){
        return this.bookCollection;
    }
    public HashMap<String, Book> getStoreCollection(){
        return this.storeCollection;
    }
    public GregorianCalendar getPresentDate(){
        return presentDate;
    }
    public HashMap<Integer,Visitor> getRegisteredVisitors(){
        return registeredVisitors;
    }
}
