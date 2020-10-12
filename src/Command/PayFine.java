package Command;

import Library.Visitor;
import Library.Library;

import java.util.ArrayList;
import java.util.HashMap;

public class PayFine implements Command {

    private Visitor visitor;
    private Library library;
    private double amount;



    public PayFine(Library library, int id, double amount) {
        this.library = library;
        this.visitor=library.getRegisteredVisitors().get(id);
        this.amount = amount;

    }

    public void execute() {
        if (!library.getRegisteredVisitors().containsValue(visitor)) {
            System.out.println("pay,invalid-visitor-id;");
        }
        else if(amount<0 || amount>visitor.getFines()) {
            System.out.println("pay,invalid-amount,"+amount+","+visitor.getFines());
        }
        else {
            visitor.removeFines(amount);
            System.out.println("pay,success," + amount);
        }
    }
}
