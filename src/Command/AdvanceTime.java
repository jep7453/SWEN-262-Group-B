package Command;
import Library.*;

import java.util.*;

/**
 * @author Zuri Shaw
 */

public class AdvanceTime implements Command {

    private final GregorianCalendar presentDate;
    private GregorianCalendar simulatedDate;
    private final Library library;

    /**
     * Constructor for AdvanceTime command
     * @param present Current date
     * @param advancement User requested advancement
     * @param library
     */
    public AdvanceTime(GregorianCalendar present, String advancement, Library library){//advancement should be "[days] [hours]" Ex. "5,10"
        this.library = library;
        this.presentDate = present;
        presentDate.setLenient(true);
        String[] advancementValue = advancement.split(",");
        if(advancementValue.length == 1) {//if they only gave us a value for days and not hours
            setSimulatedDate(advancementValue[0], null);
        }
        else{
            setSimulatedDate(advancementValue[0],advancementValue[1]);
        }
        simulatedDate.setLenient(true);
    }

    /**
     * Sets the current date forward the request time
     * @param dayStr amount of days to advance
     * @param hourStr amount of hours to advance
     */
    public void setSimulatedDate(String dayStr, String hourStr){
        try {
            int days = Integer.valueOf(dayStr);
            this.simulatedDate = presentDate;
            simulatedDate.add(Calendar.DAY_OF_MONTH, days);
            if (hourStr != null) {
                int hours = Integer.valueOf(hourStr);
                simulatedDate.add(Calendar.HOUR, hours);
            }
        }
        catch (NumberFormatException e){
            System.out.println("The days and hours field should be a number not a string" + '\n'
            + "Ex: advance,5,10");
        }
    }

    /**
     * Command execute function
     * Sets the library current date to the advanced one, and accounts for overdue books and library closing
     */
    public String execute(){
        if(library.getPresentDate().get(Calendar.DAY_OF_MONTH)!=simulatedDate.get(Calendar.DAY_OF_MONTH)) {
            Day simulation = new Day(this.simulatedDate);
            library.setSimulatedTime(simulation);
        }
        else {
            library.setSimulatedTime(simulatedDate);
        }
        library.updateState();
        library.checkVisits();
        ArrayList<CheckedBook> rentedBooks = library.getRentedBooks();
        int overdue = 0;
        library.clearFines();
        for( CheckedBook book : rentedBooks){
            book.calculateFines(library.getPresentDate());
            library.getRegisteredVisitors().get(book.getID()).addFines(book.getFine());
            Date returnDate = book.getDueDate().getTime();
            if(returnDate.compareTo(library.getPresentDate().getTime()) < 0){
                overdue++;
            }
        }
        String returnString="Current Day:" + library.getPresentDate().getTime().toString()+'\n'+"Overdue Book Count: " + overdue + '\n';
        System.out.println("Current Day:" + library.getPresentDate().getTime().toString());
        System.out.println("Overdue Book Count: " + overdue + '\n');
        return returnString;
    }
}
