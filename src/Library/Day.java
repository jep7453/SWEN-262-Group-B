package Library;

import java.io.Serializable;
import java.util.*;
import Library.*;

/**
 * @author :Zuri Shaw
 */
public class Day implements Serializable {

    private final GregorianCalendar currentDay;
    private int registeredVisitors;
    private int totalBankAccount;
    private int booksPurchased;
    private int visitSeconds;
    private int visitAmount;

    /**
     * Constructor for Day object
     * @param date of the day this object is for
     */
    public Day(GregorianCalendar date){

        this.currentDay = (GregorianCalendar) date.clone();
    }

    /**
     * Adds statistical information to the day
     * @param request
     * @param library
     * @return whether request was valid or not
     */
    public int updateDay(String[] request,Library library){
        switch (request[0]){
            case "depart":
            case "advance":
                this.visitSeconds+=library.getVisitInSeconds();
                this.visitAmount+=library.getVisitNum();
                library.clearVisits();
                return 1;
            case "register":
                this.registeredVisitors++;
                return 1;
            case "buy":
                int booksBought=Integer.valueOf(request[1])*(request.length-2);
                this.booksPurchased+=booksBought;
                return 1;
            case "pay":
                this.totalBankAccount += Integer.valueOf(request[2]);
                return 1;
            default:
                //failed to identify request
                return 0;
        }
    }

    /**
     *
     * @return amount of visitors registered on this day
     */
    public int getRegisteredVisitors(){
        return this.registeredVisitors;
    }

    /**
     *
     * @return amount of fines due on this day
     */
    public int getTotalBankAccount(){
        return this.totalBankAccount;
    }

    /**
     *
     * @return amount of books purchased on this day
     */
    public int getBooksPurchased(){
        return this.booksPurchased;
    }

    /**
     *
     * @return total seconds of visits on this day
     */
    public int getVisitSeconds(){
        return this.visitSeconds;
    }

    /**
     *
     * @return number of visits on this day
     */
    public int getVisitAmount(){
        return this.visitAmount;
    }

    /**
     *
     * @return the date of this day
     */
    public GregorianCalendar getCalender(){return this.currentDay;}

}
