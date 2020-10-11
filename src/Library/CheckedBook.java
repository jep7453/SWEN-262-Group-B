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
        this.visitorID=id;
    }


    public GregorianCalendar getdueDate(){return dueDate;}
}