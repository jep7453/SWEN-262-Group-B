package Library;

public class Visitor {

    private static String firstName;
    private static String lastName;
    private static String address;
    private static String phoneNumber;
    private static int visitorID;

    public Visitor(String firstName, String lastName, String address, String phoneNumber) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.visitorID=1000000000+Library.visitorsLength();
    }

    public int getVisitorID(){
        return visitorID;
    }


}
