package Command;

import Library.Visitor;
import Library.Library;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CurrentDateTime implements Command {

    private GregorianCalendar date;
    private Library library;



    public CurrentDateTime(Library library) {
        this.library = library;
        this.date=library.getPresentDate();

    }

    public void execute() {
        System.out.println("datetime,"+date.getTime());
    }
}
