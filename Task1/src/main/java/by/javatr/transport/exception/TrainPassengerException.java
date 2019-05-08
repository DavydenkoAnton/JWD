package by.javatr.transport.exception;

public class TrainPassengerException extends Exception {
    public TrainPassengerException(){super();}
    public TrainPassengerException(String name, Throwable exception){super(name,exception);}
    public TrainPassengerException(String name){super(name);}
    public TrainPassengerException(Throwable exception){super(exception);}
}
