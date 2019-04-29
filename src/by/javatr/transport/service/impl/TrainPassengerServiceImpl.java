package by.javatr.transport.service.impl;

import by.javatr.transport.dao.TrainCarPassengerDAO;
import by.javatr.transport.dao.TrainPassengerDAO;
import by.javatr.transport.dao.factory.DAOFactory;
import by.javatr.transport.entity.TrainCarPassenger;
import by.javatr.transport.service.TrainPassengerService;

public class TrainPassengerServiceImpl implements TrainPassengerService {

    @Override
    public void addTrainCarPassenger(int id) {
        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        TrainPassengerDAO trainPassengerDAO = daoObjectFactory.getTrainPassengerDAO();
        trainPassengerDAO.addTrainPassenger(id);
    }

    @Override
    public void getTrainCarPassenger() {
        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        TrainCarPassengerDAO trainCarPassengerDAO = daoObjectFactory.getTrainCarPassengerDAO();
    }
}
