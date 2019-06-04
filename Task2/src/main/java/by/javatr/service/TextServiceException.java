package by.javatr.service;

public class TextServiceException extends Exception {
    public TextServiceException() {
    }

    public TextServiceException(String message) {
        super(message);
    }

    public TextServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextServiceException(Throwable cause) {
        super(cause);
    }
}
