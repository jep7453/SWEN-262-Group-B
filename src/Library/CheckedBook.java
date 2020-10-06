package Library;

public class CheckedBook {
    private static String IBSN;
    private static int visitorID;
    private static String dateCheckedIn;
    private static String dueDate;

    public CheckedBook(String IBSN, int id, String dateCheckedIn, String dueDate) {
        this.IBSN=IBSN;
        this.visitorID=id;
        this.dateCheckedIn=dateCheckedIn;
        this.dueDate=dueDate;
        this.visitorID=id;
    }
    private static int bookCount = 0;
    public void addBook() {
        bookCount++;
    }
    public void returnBook() {
        bookCount--;
    }

}
