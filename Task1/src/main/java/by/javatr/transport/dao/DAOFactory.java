package by.javatr.transport.dao;

import by.javatr.transport.dao.impl.txt.TxtTrainCarPassengerDAO;
import by.javatr.transport.dao.impl.txt.TxtTrainPassengerDAO;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();
    private final TrainCarPassengerDAO txtTrainCarPassengerDAO = new TxtTrainCarPassengerDAO();
    private final TrainPassengerDAO txtTrainPassengerDAO = new TxtTrainPassengerDAO();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public TrainCarPassengerDAO getTrainCarPassengerDAO() {
        return txtTrainCarPassengerDAO;
    }
    public TrainPassengerDAO getTrainPassengerDAO() {
        return txtTrainPassengerDAO;
    }

}
