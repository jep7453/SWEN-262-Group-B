package Library;

public class ClosedLibrary implements LibraryState {

    private final Library library;

    ClosedLibrary(Library library) { this.library=library;}

    @Override
    public boolean borrowBook() {
        return false;
    }
}
