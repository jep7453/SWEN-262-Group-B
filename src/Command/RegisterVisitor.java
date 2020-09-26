package Command;

import Library.Visitor;
import Library.Library;

import java.util.ArrayList;

public class RegisterVisitor implements Command {

    private Visitor visitor;



    public RegisterVisitor(String firstName,String lastName,String address,String phoneNumber) {
        visitor = new Visitor(firstName,lastName,address,phoneNumber);
    }

    public void execute() {
        ArrayList<Visitor> visitors = Library.getVisitors();
        visitors.add(visitor);
        System.out.println("register,"+String.valueOf(visitor.getVisitorID())+ ",September 26th 2020");
        }
    }
