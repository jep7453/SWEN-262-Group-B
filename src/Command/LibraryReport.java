package Command;
import Library.Visitor;
import Library.Library;

public class LibraryReport implements Command {

    private String getVisitorCount(){
       return "Visitor Count: " + Library.visitorsLength() + '\n';
    }
    public void execute(){
        String reportString = "";
        reportString = getVisitorCount();
        System.out.println(reportString);
    }
}
