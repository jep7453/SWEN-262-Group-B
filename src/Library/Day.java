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

    public Day(GregorianCalendar date){

        this.currentDay = (GregorianCalendar) date.clone();
    }

    public int getDate(){
        return currentDay.get(Calendar.DAY_OF_MONTH);
    }

    public int updateDay(String[] request,Library library){
        switch (request[0]){
            case "depart":
                this.visitSeconds+=library.getVisitInSeconds();
                this.visitAmount+=library.getVisitNum();
                library.clearVisits();
                return 1;
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
    public int getRegisteredVisitors(){
        return this.registeredVisitors;
    }
    public int getTotalBankAccount(){
        return this.totalBankAccount;
    }

    public int getBooksPurchased(){
        return this.booksPurchased;
    }
    public int getVisitSeconds(){
        return this.visitSeconds;
    }
    public int getVisitAmount(){
        return this.visitAmount;
    }

    public GregorianCalendar getCalender(){return this.currentDay;}

}
