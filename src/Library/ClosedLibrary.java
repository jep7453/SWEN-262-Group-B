package Library;

public class ClosedLibrary extends LibraryState {

    ClosedLibrary(Library library) { super(library);}

    @Override
    public boolean borrowBook() {
        return false;
    }
}
