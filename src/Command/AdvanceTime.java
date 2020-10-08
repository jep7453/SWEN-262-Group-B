package Command;
import Library.Library;
import Library.Book;

import java.text.DateFormat;
import java.util.*;

/**
 * @author Zuri Shaw
 */

public class AdvanceTime implements Command {

    private final GregorianCalendar presentDate;
    private GregorianCalendar simulatedDate;
    private final Library library;

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

    public void setSimulatedDate(String dayStr, String hourStr){
        try {
            int days = Integer.valueOf(dayStr);
            this.simulatedDate = presentDate;
            simulatedDate.set(Calendar.DAY_OF_MONTH, presentDate.get(Calendar.DAY_OF_MONTH) + days);
            if (hourStr != null) {
                int hours = Integer.valueOf(hourStr);
                simulatedDate.set(Calendar.HOUR, presentDate.get(Calendar.HOUR) + hours);
            }
        }
        catch (NumberFormatException e){
            System.out.println("The days and hours field should be a number not a string" + '\n'
            + "Ex: advance,5,10");
        }
    }

    public void execute() throws CloneNotSupportedException{
        library.setSimulatedTime(this.simulatedDate);

        HashMap<String, Book> rentedBooks = library.getRentedBooks();
        int overdue = 0;
        for( Book book : rentedBooks.values()){
            Date returnDate = book.getReturnDate().getTime();
            if(returnDate.compareTo(library.getPresentDate().getTime()) < 0){
                overdue++;
            }
        }
        System.out.println("Overdue Book Count: " + overdue + '\n');
    }
}
