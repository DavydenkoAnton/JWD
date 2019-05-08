package by.javatr.transport.dao.factory;

import by.javatr.transport.dao.TrainCarPassengerDAO;
import by.javatr.transport.dao.TrainPassengerTrainDAO;
import by.javatr.transport.dao.impl.txt.TxtTrainCarPassengerDAO;
import by.javatr.transport.dao.impl.txt.TxtTrainPassengerTrainDAO;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();
    private final TrainCarPassengerDAO txtTrainCarPassengerDAO = new TxtTrainCarPassengerDAO();
    private final TrainPassengerTrainDAO txtTrainPassengerDAO = new TxtTrainPassengerTrainDAO();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public TrainCarPassengerDAO getTrainCarPassengerDAO() {
        return txtTrainCarPassengerDAO;
    }
    public TrainPassengerTrainDAO getTrainPassengerDAO() {
        return txtTrainPassengerDAO;
    }

}
