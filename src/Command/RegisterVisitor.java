package Command;

import Library.Visitor;
import Library.Library;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Calendar;

public class RegisterVisitor implements Command {

    private Visitor visitor;
    private Library library;
    private int id;
    private GregorianCalendar cal;

    public RegisterVisitor(Library library, String firstName,String lastName,String address,String phoneNumber) {
        this.library = library;
        this.id =1000000000+ library.visitorsLength();
        visitor = new Visitor(firstName,lastName,address,phoneNumber,id);
        cal = library.getPresentDate();
    }

    public void execute() {
        HashMap<Integer,Visitor> visitors = library.getRegisteredVisitors();
        if(visitors.get(id) != null)
            System.out.println("arrive," + "duplicate;");
        else {
            visitors.put(id, visitor);
            System.out.println("register," + String.valueOf(visitor.getVisitorID()) + "," + cal.getTime() + ";");
        }
    }
}

