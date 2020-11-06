package Library;

import java.io.Serializable;

public class OpenLibrary implements LibraryState, Serializable {

    private final Library library;

    /**
     * Constructor for OpenLibrary object
     * @param library
     */
    OpenLibrary(Library library) { this.library=library;}

    /**
     * returns true as books can be checked out when open
     * @return true
     */
    @Override
    public boolean borrowBook() {
        return true;
    }

    /**
     * returns true as visits can be started when open
     * @return true
     */
    @Override
    public boolean startVisit() {
        return true;
    }

    /**
     * Visits are fine when library is open
     */
    @Override
    public void checkVisits() {

    }
}