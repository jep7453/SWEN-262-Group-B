package Command;

import Library.Visitor;
import Library.Library;

import java.util.ArrayList;

public class RegisterVisitor implements Command {

    private Visitor visitor;
    private Library library;



    public RegisterVisitor(Library library, String firstName,String lastName,String address,String phoneNumber) {
        this.library = library;
        int id =1000000000+ library.visitorsLength();
        visitor = new Visitor(firstName,lastName,address,phoneNumber);

    }

    public void execute() {
        ArrayList<Visitor> visitors = library.getRegisteredVisitors();
        visitors.add(visitor);
        System.out.println("register,"+String.valueOf(visitor.getVisitorID())+ ",September 26th 2020");
    }
}
