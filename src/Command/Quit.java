package Command;

import Library.Library;
import Library.Visitor;

public class Quit implements Command{

    private Library library;

    public Quit(Library library){
        this.library = library;
    }

    public void execute(){
        for(Visitor visitor : library.getRegisteredVisitors().values()){
            if(visitor.isCurrentlyInLibrary()) {
                Command endVisit = new EndVisit(library, visitor.getVisitorID());
                endVisit.execute();
            }
        }
        System.out.println("Library has closed and program is ready for shutdown" + '\n');
    }
}
