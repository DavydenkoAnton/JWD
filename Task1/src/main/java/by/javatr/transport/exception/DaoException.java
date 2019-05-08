package by.javatr.transport.exception;

public class DaoException extends Exception{
    public DaoException(){super();}
    public DaoException(String name, Throwable exception){super(name,exception);}
    public DaoException(String name){super(name);}
    public DaoException(Throwable exception){super(exception);}
}
