package by.davydenko.petbook.service.creator;

public class CreatorException extends Exception {
    public CreatorException() {
    }

    public CreatorException(String message) {
        super(message);
    }

    public CreatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreatorException(Throwable cause) {
        super(cause);
    }
}
