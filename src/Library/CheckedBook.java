package Library;

import java.util.GregorianCalendar;

public class CheckedBook {
    private Book book;
    private int visitorID;
    private GregorianCalendar dueDate;

    public CheckedBook(Book book, int id, GregorianCalendar dueDate) {
        this.book=book;
        this.visitorID=id;
        this.dueDate=dueDate;
    }


    public Book getBook(){return book;}
    public int getID(){return visitorID;}
    public GregorianCalendar getDueDate() {return dueDate;}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final CheckedBook other = (CheckedBook) obj;
        if ((this.book == other.getBook() && this.visitorID == other.getID() && this.dueDate == other.getDueDate())) {
            return true;
        } else {
            return false;
        }
    }
}