package Command;

import Library.Visitor;
import Library.Library;

import java.util.ArrayList;
import java.util.HashMap;

public class PayFine implements Command {

    private Visitor visitor;
    private Library library;
    private double amount;


    /**
     * Constructor for PayFine commands
     * @param library
     * @param id
     * @param amount
     */
    public PayFine(Library library, int id, double amount) {
        this.library = library;
        this.visitor=library.getRegisteredVisitors().get(id);
        this.amount = amount;

    }

    /**
     * Command execute function
     * Lowers fines by the specified amount for a user
     */
    public String execute() {
        if (!library.getRegisteredVisitors().containsValue(visitor)) {
            System.out.println("pay,invalid-visitor-id;");
            return "pay,invalid-visitor-id;";
        }
        else if(amount<0 || amount>visitor.getFines()) {
            System.out.println("pay,invalid-amount,"+amount+","+visitor.getFines());
            return "pay,invalid-amount,"+amount+","+visitor.getFines();
        }
        else {
            visitor.removeFines(amount);
            System.out.println("pay,success," + amount);
            return "pay,success," + amount;
        }
    }
}
