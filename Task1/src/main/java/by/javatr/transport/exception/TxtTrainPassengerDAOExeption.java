package by.javatr.transport.exception;

public class TxtTrainPassengerDAOExeption extends Exception {
    public TxtTrainPassengerDAOExeption(){super();}
    public TxtTrainPassengerDAOExeption(String name, Throwable exception){super(name,exception);}
    public TxtTrainPassengerDAOExeption(String name){super(name);}
    public TxtTrainPassengerDAOExeption(Throwable exception){super(exception);}
}
