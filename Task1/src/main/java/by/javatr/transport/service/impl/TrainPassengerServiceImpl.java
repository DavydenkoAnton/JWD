package by.javatr.transport.service.impl;

import by.javatr.transport.dao.TrainCarPassengerDAO;
import by.javatr.transport.dao.TrainPassengerDAO;
import by.javatr.transport.dao.factory.DAOFactory;
import by.javatr.transport.exception.DaoException;
import by.javatr.transport.exception.ParseException;
import by.javatr.transport.exception.RepositoryException;
import by.javatr.transport.exception.TrainPassengerException;
import by.javatr.transport.repository.repositoryimpl.TrainPassengerRepository;
import by.javatr.transport.repository.specificationimpl.ByTrainIDSpecification;
import by.javatr.transport.service.TrainPassengerService;

public class TrainPassengerServiceImpl implements TrainPassengerService {

    private TrainPassengerRepository trainPassengerRepository = TrainPassengerRepository.getInstance();
    private ByTrainIDSpecification byTrainIDSpecification;

    @Override
    public String getAllTrainPassenger() throws RepositoryException {
        String trains = trainPassengerRepository.getAll();
        return trains;
    }

    public void addTrainPassenger(String request) throws DaoException, TrainPassengerException {
        trainPassengerRepository.addTrainPassenger(request);
    }


    public void getTrainCarPassenger() {
        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        TrainCarPassengerDAO trainCarPassengerDAO = daoObjectFactory.getTrainCarPassengerDAO();
    }


    private String getID(String request) throws TrainPassengerException {
        String id = request.replaceAll("[a-z _]+", "");
        return id;
    }


}
