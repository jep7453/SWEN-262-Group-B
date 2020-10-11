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
    private double fines;
    private int borrowedBooks;

    public Visitor(String firstName, String lastName, String address, String phoneNumber, int visitorID) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.visitorID=visitorID;
        this.visitDate = new Date();
        fines=0;
        borrowedBooks=0;
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

}
