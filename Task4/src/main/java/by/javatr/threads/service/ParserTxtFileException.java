package by.javatr.threads.service;

public class ParserTxtFileException extends Exception {
    public ParserTxtFileException() {
    }

    public ParserTxtFileException(String message) {
        super(message);
    }

    public ParserTxtFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserTxtFileException(Throwable cause) {
        super(cause);
    }
}
