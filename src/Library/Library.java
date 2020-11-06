package Library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;

public class Library implements Serializable {

    private ArrayList<Book> librarySearchCache;
    private ArrayList<Book> storeSearchCache;
    private HashMap<Integer,Visitor> registeredVisitors;
    private HashMap<String, Book> bookCollection;
    private ArrayList<CheckedBook> rentedBooks;
    private ArrayList<CheckedBook> borrowedSearchCache;
    private int borrowedSearchUser;
    private HashMap<String,Book> storeCollection;
    private GregorianCalendar presentDate;
    private ArrayList<Day> history;
    private LibraryState state;
    private ArrayList<Integer> visitAverageSec;

    /**
     * Constructor for library object
     * Fills BookStore from books.txt
     * @param startDay Day object of when system first ran
     * @throws IOException
     */
    public Library(Day startDay) throws IOException {
        this.registeredVisitors = new HashMap<>();
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

    /**
     *
     * @return number of registered visitors
     */
    public int visitorsLength() {
        if(registeredVisitors ==null)
            return 0;
        return registeredVisitors.size();
    }

    /**
     * Clears fines of currently borrowed books for all visitors, to account for increasing overdue ones
     */
    public void clearFines() {
        for (Visitor visitor:registeredVisitors.values()) {
            visitor.clearFines();
        }
    }

    /**
     * parses the books.txt file into Book objects and adds it to the library
     * @param bookString
     */
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

    /**
     * Checks if visitors can borrow a book
     * @return true or false
     */
    public boolean borrowBook() {
        return state.borrowBook();
    }

    /**
     * Changes state depending on whether library should be open or not
     */
    public void updateState() {
        if(presentDate.get(Calendar.HOUR)+(presentDate.get(Calendar.AM_PM)*12)>=8&&presentDate.get(Calendar.HOUR)+(presentDate.get(Calendar.AM_PM)*12)<19) {
            state = new OpenLibrary(this);
        }
        else {
            state = new ClosedLibrary(this);
        }
    }

    /**
     * Updates visits based on library state
     */
    public void checkVisits() {
        state.checkVisits();
    }

    /**
     * Checks if visitors can start a visit
     * @return true of false
     */
    public boolean startVisit() {
        return state.startVisit();
    }

    /**
     * Gets the history of library statistics as a list of days
     * @return list of all days library has been through
     */
    public ArrayList<Day> getHistory(){

        return this.history;
    }

    /**
     * Adds a day of statistics to the library history
     * @param day
     */
    public void addToHistory(Day day){
        this.history.add(day);
    }

    /**
     * Changes the library date and adds a day to the history
     * @param newDayObj day being changed to
     */
    public void setSimulatedTime(Day newDayObj){
        this.presentDate = newDayObj.getCalender();
        addToHistory(newDayObj);
    }

    /**
     * Changes the library date
     * @param cal date being changed to
     */
    public void setSimulatedTime(GregorianCalendar cal){
        this.presentDate = cal;
    }

    /**
     * Resets visit times
     */
    public void clearVisits() {
        visitAverageSec.clear();
    }

    /**
     * Adds a visit time to list of all of them
     * @param length of visit in seconds
     */
    public void addVisit(int length) {
        visitAverageSec.add(length);
    }

    /**
     *
     * @return total visits in seconds
     */
    public int getVisitInSeconds() {
        int average = 0;
        for (int visit : visitAverageSec) {
            average += visit;
        }
        return average;

    }

    /**
     *
     * @return number of total visits
     */
    public int getVisitNum() {
        return visitAverageSec.size();
    }

    /**
     *
     * @return ID of user from previous BorrowBooksSearch for BorrowBook
     */
    public int getBorrowedSearchUser() {return borrowedSearchUser;}

    /**
     * sets ID of user in BorrowBooksSearch for BorrowBook
     * @param user ID
     */
    public void setBorrowedSearchUser(int user) { borrowedSearchUser =user;}

    /**
     *
     * @return ID cache from previous BorrowBooksSearch for ReturnBook
     */
    public ArrayList<CheckedBook> getBorrowedSearchCache() {
        return borrowedSearchCache;
    }

    /**
     * sets ID cache from previous BorrowBooksSearch for ReturnBook
     * @param searchResults list of CheckedOut books from BorrowBooksSearch
     */
    public void setBorrowedSearch(ArrayList<CheckedBook> searchResults) {
        borrowedSearchCache = searchResults;
    }

    /**
     *
     * @return ID cache from previous BookStoreSearch for PurchaseBook
     */
    public ArrayList<Book> getStoreSearch() {
        return storeSearchCache;
    }

    /**
     * sets ID cache from previous BookStoreSearch for PurchaseBook
     * @param searchResults list of books from BookStoreSearch
     */
    public void setStoreSearch(ArrayList<Book> searchResults) {
        storeSearchCache = searchResults;
    }

    /**
     * sets ID cache from previous LibraryBookSearch for BorrowBook
     * @param searchResults ID cache from previous LibraryBookSearch for BorrowBook
     */
    public void setLibrarySearch(ArrayList<Book> searchResults) {
        librarySearchCache = searchResults;
    }

    /**
     *
     * @return ID cache from previous LibraryBookSearch for BorrowBook
     */
    public ArrayList<Book> getLibrarySearch() {
        return librarySearchCache;
    }

    /**
     *
     * @return list of currently checkout books
     */
    public ArrayList<CheckedBook> getRentedBooks(){
        return this.rentedBooks;
    }

    /**
     *
     * @return list of library's book catalog
     */
    public HashMap<String, Book> getBookCollection(){
        return this.bookCollection;
    }

    /**
     *
     * @return list of book store's catalog
     */
    public HashMap<String, Book> getStoreCollection(){
        return this.storeCollection;
    }

    /**
     *
     * @return current date of simulation
     */
    public GregorianCalendar getPresentDate(){
        return presentDate;
    }

    /**
     *
     * @return registered visitors
     */
    public HashMap<Integer,Visitor> getRegisteredVisitors(){
        return registeredVisitors;
    }
}
