package Library;

public abstract class LibraryState {

    Library library;

    LibraryState(Library library) {
        this.library = library;
    }


    public abstract boolean borrowBook();
}


