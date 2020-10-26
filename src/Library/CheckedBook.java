package Library;

import java.io.Serializable;
import java.util.GregorianCalendar;


public class CheckedBook implements Serializable {
    private Book book;
    private int visitorID;
    private GregorianCalendar dueDate;
    private double fines;

    public CheckedBook(Book book, int id, GregorianCalendar dueDate) {
        this.book=book;
        this.visitorID=id;
        this.dueDate=dueDate;
        this.fines=0;
    }


    public Book getBook(){return book;}
    public int getID(){return visitorID;}
    public GregorianCalendar getDueDate() {return dueDate;}
    public double getFine(){return fines;}

    public void calculateFines(GregorianCalendar currentDate) {
        int fine = 0;
        long days = currentDate.getTime().getTime() - dueDate.getTime().getTime();
        int daysDiff =(int) (days / (1000 * 60 * 60 * 24));;
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