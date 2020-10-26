package Library;

import java.io.Serializable;

public class OpenLibrary implements LibraryState, Serializable {

    private final Library library;

    OpenLibrary(Library library) { this.library=library;}

    @Override
    public boolean borrowBook() {
        return true;
    }

    @Override
    public boolean startVisit() {
        return true;
    }

    @Override
    public void checkVisits() {

    }
}