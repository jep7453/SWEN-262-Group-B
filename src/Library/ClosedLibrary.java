package Library;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ClosedLibrary implements LibraryState, Serializable {

    private final Library library;

    /**
     * Constructor for CLosedLibrary object
     * @param library
     */
    ClosedLibrary(Library library) { this.library=library;}

    /**
     * returns false as books cannot be checked out when closed
     * @return false
     */
    @Override
    public boolean borrowBook() {
        return false;
    }

    /**
     * returns false as visits cannot be started when closed
     * @return false
     */
    @Override
    public boolean startVisit() {
        return false;
    }

    /**
     * ends visits and recording their length when the library is closed
     */
    @Override
    public void checkVisits() {
        GregorianCalendar closingTime = (GregorianCalendar) library.getPresentDate().clone();
        closingTime.set(Calendar.HOUR,7);
        closingTime.set(Calendar.AM_PM,1);
        closingTime.set(Calendar.MINUTE,0);
        closingTime.set(Calendar.SECOND,0);
        closingTime.set(Calendar.MILLISECOND,0);
        for(Visitor visitor:library.getRegisteredVisitors().values()) {

            if(visitor.isCurrentlyInLibrary()) {
                visitor.setCurrentlyInLibrary(false);
                long length = closingTime.getTime().getTime() - visitor.getStartTime().getTime();
                int seconds = (int) length / 1000;
                library.addVisit(seconds);
                int secondsDuration = seconds % 60;
                int minutes = seconds / 60;
                int minutesDuration = minutes % 60;
                int hours = minutes / 60;
                System.out.println("depart," + String.valueOf(visitor.getVisitorID()) + "," + closingTime.getTime() + "," + hours + ":" + minutesDuration + ":" + secondsDuration);
            }
        }
    }
}
