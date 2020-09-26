package Library;

import java.util.ArrayList;

public class Library {

    private static ArrayList<Visitor> visitors;

    public Library() {
        this.visitors= new ArrayList<>();

    }

    public static int visitorsLength() {
        if(visitors==null)
            return 0;
        return visitors.size();
    }

    public static ArrayList<Visitor> getVisitors(){
        return visitors;
    }
}
