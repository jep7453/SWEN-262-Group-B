package Library;

import java.io.Serializable;
import java.util.GregorianCalendar;


public class CheckedBook implements Serializable {
    private Book book;
    private int visitorID;
    private GregorianCalendar dueDate;
    private double fines;

    /**
     * Constructor for the CheckedBook object
     * @param book the book being borrowed
     * @param id of the user borrowing it
     * @param dueDate due date of the book
     */
    public CheckedBook(Book book, int id, GregorianCalendar dueDate) {
        this.book=book;
        this.visitorID=id;
        this.dueDate=dueDate;
        this.fines=0;
    }

    /**
     *
     * @return book being borrowed
     */
    public Book getBook(){return book;}

    /**
     *
     * @return visitor ID borrowing the book
     */
    public int getID(){return visitorID;}

    /**
     *
     * @return due date of the book
     */
    public GregorianCalendar getDueDate() {return dueDate;}

    /**
     *
     * @return current overdue fines for the book
     */
    public double getFine(){return fines;}

    /**
     * Calculates current fines on the book based on the input date
     * @param currentDate
     */
    public void calculateFines(GregorianCalendar currentDate) {
        int fine = 0;
        long days = currentDate.getTime().getTime() - dueDate.getTime().getTime();
        int daysDiff =(int) (days / (1000 * 60 * 60 * 24));
        if(daysDiff>0) {
            fine+=10;
        }
        if(daysDiff>7) {
            fine += (((daysDiff-1)/7))*2;
        }
        if(fine>30)
            fine=30;
        fines=(double)fine;
    }

    /**
     *
     * @param obj
     * @return if the input object is equal to this
     */
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