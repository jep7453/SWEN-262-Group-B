package Library;

import java.util.ArrayList;

public class Book {

    private  String title;
    private  ArrayList<String> authors;
    private  String ISBN;
    private  String publisher;
    private  String date;
    private  int pages;
    private int copies;
    private int copiesAvailable;

    public Book(String title, ArrayList<String> authors, String ISBN, String publisher, String date, int pages) {
        this.title=title;
        this.authors=authors;
        this.publisher=publisher;
        this.ISBN=ISBN;
        this.date=date;
        this.pages=pages;
        this.copies=0;
        this.copiesAvailable=0;
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

    public void addCopies(int amount) {
        copies+=amount;
        copiesAvailable+=amount;
    }

    @Override
    public String toString() {
        String book = ISBN + "," + title + "," + authors + "," + publisher + "," + date + "," + pages;
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
