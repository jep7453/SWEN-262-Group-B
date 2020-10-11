package Library;

import java.util.*;

/**
 * @author :Zuri Shaw
 */
public class Day {

    private final GregorianCalendar currentDay;
    private int registeredVisitors;
    private int totalBankAccount;
    private int rentedBooks;
    private int booksPurchased;
    private int booksReturned;

    public Day(GregorianCalendar date){
        this.currentDay = date;
    }

    public int getDate(){
        return currentDay.get(Calendar.DAY_OF_MONTH);
    }

    public int updateDay(String[] request){
        switch (request[0]){
            case "register":
                this.registeredVisitors++;
                return 1;
            case "purchase":
                this.booksPurchased++;
                return 1;
            case "rent":
                this.rentedBooks++;
                return 1;
            case "return":
                this.booksReturned++;
                return 1;
            case "Pay":
                this.totalBankAccount += Integer.valueOf(request[2]);
                return 1;
            default:
                //failed to identify request
                return 0;
        }
    }
    public int getRegisteredVisitors(){
        return this.registeredVisitors;
    }
    public int getTotalBankAccount(){
        return this.totalBankAccount;
    }
    public int getRentedBooks(){
        return this.rentedBooks;
    }
    public int getBooksPurchased(){
        return this.booksPurchased;
    }
    public int getBooksReturned(){
        return this.booksReturned;
    }
    public GregorianCalendar getCalender(){return this.currentDay;}

}
