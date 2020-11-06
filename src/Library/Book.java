package Library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Book implements Serializable {

    private  String title;
    private  ArrayList<String> authors;
    private  String ISBN;
    private  String publisher;
    private  String date;
    private  int pages;
    private int copies;
    private int copiesAvailable;
    private GregorianCalendar returnDate;

    /**
     * Constructor for book object
     * @param title
     * @param authors
     * @param ISBN
     * @param publisher
     * @param date
     * @param pages
     */
    public Book(String title, ArrayList<String> authors, String ISBN, String publisher, String date, int pages) {
        this.title=title;
        this.authors=authors;
        this.publisher=publisher;
        this.ISBN=ISBN;
        this.date=date;
        this.pages=pages;
        this.copies=0;
        this.copiesAvailable=0;
        this.returnDate = null;
    }

    /**
     *
     * @return the ISBN #
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return the publisher of the book
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     *
     * @return the published date of the book
     */
    public String getDate() {  return date; }

    /**
     *
     * @return # of pages in the book
     */
    public int getPages() {  return pages; }

    /**
     *
     * @return list of authors for the book
     */
    public ArrayList<String> getAuthors() { return authors;}

    /**
     * Turns the list of authors into a string
     * @return authors of the book as one string
     */
    public String getAuthorsString() {
        String authorsString = "";
        for(String author:authors) {
            authorsString = authorsString + author;
        }
        return "{"+ authorsString + "}";
    }

    /**
     * Adds a copy of the book
     * @param amount of copies to add
     */
    public void addCopies(int amount) {
        copies+=amount;
        copiesAvailable+=amount;
    }

    /**
     * Removes a copy from the available ones
     * @param amount of copies to borrow
     */
    public void borrowCopy(int amount) {
        copiesAvailable-=amount;
    }

    /**
     * Adds a copy to the available ones
     * @param amount of copies to return
     */
    public void returnCopy(int amount) {
        copiesAvailable+=amount;
    }

    /**
     *
     * @return amount of available copies of the book
     */
    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    /**
     *
     * @return amount of total copies of the book
     */
    public int getCopies() {
        return copies;
    }


    /**
     *
     * @return information of the book as a string
     */
    @Override
    public String toString() {
        String book = ISBN + "," + title + "," + getAuthorsString() + "," + publisher + "," + date + "," + pages;
        return book;
    }

    /**
     *
     * @param obj
     * @return if the input object is equal to this book
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Book other = (Book) obj;
        if ((this.title == other.getTitle() && this.publisher == other.getPublisher() && this.pages == other.getPages() &&
                this.date == other.getDate() && this.ISBN == other.getISBN() && this.authors == other.getAuthors())) {
            return true;
        } else {
            return false;
        }

    }
}
