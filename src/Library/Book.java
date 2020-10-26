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

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDate() {  return date; }

    public int getPages() {  return pages; }

    public ArrayList<String> getAuthors() { return authors;}

    public String getAuthorsString() {
        String authorsString = "";
        for(String author:authors) {
            authorsString = authorsString + author;
        }
        return "{"+ authorsString + "}";
    }

    public void addCopies(int amount) {
        copies+=amount;
        copiesAvailable+=amount;
    }

    public void borrowCopy(int amount) {
        copiesAvailable-=amount;
    }

    public void returnCopy(int amount) {
        copiesAvailable+=amount;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public int getCopies() {
        return copies;
    }

    public void setReturnDate(GregorianCalendar returnDate){this.returnDate = returnDate;}
    public GregorianCalendar getReturnDate(){return returnDate;}

    @Override
    public String toString() {
        String book = ISBN + "," + title + "," + getAuthorsString() + "," + publisher + "," + date + "," + pages;
        return book;
    }

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
