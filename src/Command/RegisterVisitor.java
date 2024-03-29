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

    /**
     * Constructor for RegisterVisitor commands
     * @param library
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     */
    public RegisterVisitor(Library library, String firstName,String lastName,String address,String phoneNumber) {
        this.library = library;
        this.id =1000000000+ library.visitorsLength();
        visitor = new Visitor(firstName,lastName,address,phoneNumber,id);
        cal = library.getPresentDate();
    }

    /**
     * Command execute function
     * Adds a user to the libraries registered users
     */
    public String execute() {
        HashMap<Integer,Visitor> visitors = library.getRegisteredVisitors();
        if(visitors.get(id) != null) {
            System.out.println("arrive," + "duplicate;");
            return "arrive," + "duplicate;";
        }
        else {
            visitors.put(id, visitor);
            System.out.println("register," + String.valueOf(visitor.getVisitorID()) + "," + cal.getTime() + ";");
            return "register," + String.valueOf(visitor.getVisitorID()) + "," + cal.getTime() + ";";
        }
    }
}

