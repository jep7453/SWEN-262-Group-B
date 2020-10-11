package Library;

public class OpenLibrary extends LibraryState {

    OpenLibrary(Library library) { super(library);}

    @Override
    public boolean borrowBook() {
        return true;
    }
}