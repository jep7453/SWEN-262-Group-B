package Library;

import java.util.*;

/**
 * @author :Zuri Shaw
 */
public class Day {

    private final GregorianCalendar currentDay;
    private int registeredVisitors;
    public int totalBankAccount;
    private int rentedBooks;
    private int booksPurchased;
    private int booksReturned;

    public Day(GregorianCalendar date){
        this.currentDay = date;
    }

    public int getDate(){
        return currentDay.get(Calendar.DAY_OF_MONTH);
    }

    public void updateDay(String[] request){
        switch (request[0]){
            case "register":
                this.registeredVisitors++;
                break;
            case "purchase":
                this.booksPurchased++;
                break;
            case "rent":
                this.rentedBooks++;
                break;
            case "return":
                this.booksReturned++;
                break;
            case "Pay":
                this.totalBankAccount += Integer.valueOf(request[2]);
                break;
            default:
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

}
