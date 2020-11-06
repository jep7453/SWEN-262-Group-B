package Library;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Visitor implements Serializable {

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private int visitorID;
    private Date visitDate;
    private Date startTime;
    private double fines;
    private double returnedFines;
    private double paidFines;
    private int borrowedBooks;
    private boolean currentlyInLibrary;

    /**
     * Constructor of Visitor object
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     * @param visitorID
     */
    public Visitor(String firstName, String lastName, String address, String phoneNumber, int visitorID) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.visitorID=visitorID;
        this.visitDate = new Date();
        this.startTime = null;
        this.fines=0;
        this.returnedFines=0;
        this.paidFines=0;
        this.borrowedBooks=0;
        this.currentlyInLibrary = false;
    }

    /**
     *
     * @return the unique ID of this visitor
     */
    public int getVisitorID(){
        return visitorID;
    }

    /**
     *
     * @return amount of fines the visitor owes
     */
    public double getFines() { return  fines+returnedFines;}

    /**
     * Adds to the fines this visitor owes on currently borrowed books
     * @param fine
     */
    public void addFines(double fine) {
        fines+=fine;
    }

    /**
     * Adds fines when an overdue book is returned
     * @param fine
     */
    public void returnedFinedBook(double fine) {
        returnedFines+=fine;
        fines-=fine;
    }

    /**
     * Removes fines this visitor owes
     * @param pay
     */
    public void removeFines(double pay) {
        paidFines+=pay;
        if(returnedFines>0 && pay<returnedFines)
            returnedFines-=pay;
        else if(returnedFines>0) {
            pay-=returnedFines;
            returnedFines=0;
            fines-=pay;
        }
        else
            fines-=pay;
    }

    /**
     * Sets borrowed book fines to zero
     */
    public void clearFines() { fines= 0;}

    /**
     *
     * @return amount of books borrowed
     */
    public double getBorrowedBooks() { return  borrowedBooks;}

    /**
     * Adds to the number of books this visitor this borrowed
     * @param books
     */
    public void addBorrowedBooks(int books) {
        borrowedBooks+=books;
    }

    /**
     * Lowers the number of books this visitor this borrowed
     * @param books
     */
    public void removeBorrowedBooks(int books) {
        borrowedBooks-=books;
    }

    /**
     *
     * @return if the visitor is visiting
     */
    public boolean isCurrentlyInLibrary(){return currentlyInLibrary;}

    /**
     * Sets whether the visitor is visiting or not
     * @param tOrF true or false
     */
    public void setCurrentlyInLibrary(boolean tOrF){currentlyInLibrary = tOrF;}

    /**
     * Sets the start time of a visit for the visitor
     * @param date date of start time
     */
    public void setStartTime(Date date){startTime = date;}

    /**
     *
     * @return start time of a visit
     */
    public Date getStartTime(){return startTime;}



    //Please Work
}
