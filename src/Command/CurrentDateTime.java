package Command;

import Library.Visitor;
import Library.Library;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CurrentDateTime implements Command {

    private GregorianCalendar date;
    private Library library;


    /**
     * Constructor for CurrentDateTime commands
     * @param library
     */
    public CurrentDateTime(Library library) {
        this.library = library;
        this.date=library.getPresentDate();

    }

    /**
     * Command execute function
     * Gets and prints the current date and time
     */
    public void execute() {
        System.out.println("datetime,"+date.getTime());
    }
}
