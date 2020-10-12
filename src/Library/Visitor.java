package Library;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Visitor {

    private static String firstName;
    private static String lastName;
    private static String address;
    private static String phoneNumber;
    private static int visitorID;
    private static Date visitDate;
    private static Date startTime;
    private double fines;
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
        fines=0;
        borrowedBooks=0;
        currentlyInLibrary = false;
    }

    public int getVisitorID(){
        return visitorID;
    }
    public double getFines() { return  fines;}
    public void addFines(int fine) {
        fines+=fine;
    }
    public void removeFines(double pay) {
        fines-=pay;
    }
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

}
