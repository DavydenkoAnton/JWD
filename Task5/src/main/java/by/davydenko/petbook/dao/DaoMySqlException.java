package by.davydenko.petbook.dao;

public class DaoMySqlException extends Exception {
    public DaoMySqlException() {
    }

    public DaoMySqlException(String message) {
        super(message);
    }

    public DaoMySqlException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoMySqlException(Throwable cause) {
        super(cause);
    }
}