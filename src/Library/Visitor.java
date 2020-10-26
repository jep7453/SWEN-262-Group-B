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

    public int getVisitorID(){
        return visitorID;
    }
    public double getFines() { return  fines+returnedFines;}
    public void addFines(double fine) {
        fines+=fine;
    }
    public void returnedFinedBook(double fine) {
        returnedFines+=fine;
        fines-=fine;
    }
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
    public void clearFines() { fines= 0;}
    public double getBorrowedBooks() { return  borrowedBooks;}
    public void addBorrowedBooks(int books) {
        borrowedBooks+=books;
    }
    public void removeBorrowedBooks(int books) {
        borrowedBooks-=books;
    }
    public boolean isCurrentlyInLibrary(){return currentlyInLibrary;}
    public void setCurrentlyInLibrary(boolean tOrF){currentlyInLibrary = tOrF;}
    public void setStartTime(Date date){startTime = date;}
    public Date getStartTime(){return startTime;}



    //Please Work
}
