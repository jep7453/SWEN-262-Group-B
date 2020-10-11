package Command;

import Library.Visitor;
import Library.Library;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisterVisitor implements Command {

    private Visitor visitor;
    private Library library;
    private int id;



    public RegisterVisitor(Library library, String firstName,String lastName,String address,String phoneNumber) {
        this.library = library;
        this.id =1000000000+ library.visitorsLength();
        visitor = new Visitor(firstName,lastName,address,phoneNumber,id);

    }

    public void execute() {
        HashMap<Integer,Visitor> visitors = library.getRegisteredVisitors();
        visitors.put(id,visitor);
        System.out.println("register,"+String.valueOf(visitor.getVisitorID())+ ",September 26th 2020");
    }
}