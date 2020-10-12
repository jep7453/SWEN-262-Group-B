package Command;

import Library.Visitor;
import Library.Library;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * @author :Garrett Rudolfs
 */
public class EndVisit implements Command {

    private Library library;
    private int id;
    private Visitor visitor;
    private GregorianCalendar cal;
    private Date endTime;
    private Date startTime;

    public EndVisit(Library library, int id) {
        this.library = library;
        this.id = id;
        cal = library.getPresentDate();
        endTime = cal.getTime();
    }

    public void execute() {
        HashMap<Integer, Visitor> visitors = library.getRegisteredVisitors();
        visitor = visitors.get(id);
        if(visitor == null) {
            System.out.println("arrive," + "invalid-id;");
        }
        else if(!visitor.isCurrentlyInLibrary()) {
            System.out.println("arrive," + "invalid-id;");
        }
        else {
            visitor.setCurrentlyInLibrary(false);
            startTime = visitor.getStartTime();
            long length =  endTime.getTime()-startTime.getTime();
            int seconds = (int)length /1000;
            library.addVisit(seconds);
            int secondsDuration = seconds%60;
            int minutes = seconds/60;
            int minutesDuration= minutes%60;
            int hours = minutes/60;
            System.out.println("depart," + String.valueOf(visitor.getVisitorID()) + "," + endTime + ","+hours+":"+minutesDuration+":"+secondsDuration);
        }
    }
}
