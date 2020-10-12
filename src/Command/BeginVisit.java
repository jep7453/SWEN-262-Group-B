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
public class BeginVisit implements Command {

    private Library library;
    private int id;
    private Visitor visitor;
    private GregorianCalendar cal;
    private Date startTime;

        public BeginVisit(Library library, int id) {
        this.library = library;
        this.id = id;
        cal = library.getPresentDate();
        startTime = cal.getTime();
        }

        public void execute() {
        HashMap<Integer,Visitor> visitors = library.getRegisteredVisitors();
        visitor = visitors.get(id);
        if(visitor == null)
            System.out.println("arrive," + "invalid-id;");
        else if(visitor.isCurrentlyInLibrary())
            System.out.println("arrive," + "duplicate;");
        else if(!library.startVisit()) {
            System.out.println("arrive," + "library-closed;");
        }
        else {
            visitor.setCurrentlyInLibrary(true);
            visitor.setStartTime(startTime);
            System.out.println("arrive," + String.valueOf(visitor.getVisitorID()) + "," + startTime + ";");
            }
        }
}

