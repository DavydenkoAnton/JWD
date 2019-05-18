package by.javatr.transport.service.impl;

import by.javatr.transport.Sorter.FactorySorter;
import by.javatr.transport.Sorter.TrainPassengerSorter;
import by.javatr.transport.creator.TrainPassengerCreator;
import by.javatr.transport.creator.FactoryCreator;
import by.javatr.transport.dao.TrainCarPassengerDAO;
import by.javatr.transport.dao.DAOFactory;
import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.exception.*;
import by.javatr.transport.repository.Specification;
import by.javatr.transport.repository.repositoryimpl.TrainPassengerRepository;
import by.javatr.transport.repository.specificationimpl.ByTrainIDSpecification;
import by.javatr.transport.service.TrainPassengerService;

import java.util.List;

public class TrainPassengerServiceImpl implements TrainPassengerService {

    private TrainPassengerRepository trainPassengerRepository = TrainPassengerRepository.getInstance();
    private FactoryCreator factoryCreator = FactoryCreator.getInstance();


    private TrainPassengerCreator trainPassengerCreator = factoryCreator.getTrainPassengerCreator();
    private ByTrainIDSpecification byTrainIDSpecification;


    @Override
    public String getAllTrainPassenger() throws RepositoryException, TxtTrainPassengerDAOExeption {
        String trains = trainPassengerRepository.getAll();
        return trains;
    }

    @Override
    public String sortById() {
        String result="";
        List<TrainPassenger> trainPassengerList = trainPassengerRepository.sort(byTrainIDSpecification);
        for (TrainPassenger trainPassenger : trainPassengerList) {
            result+=trainPassenger.toString()+"\n";
        }
        return result;
    }

    public void addTrainPassenger(String request) throws DaoException, TrainPassengerException, ParseException {
        TrainPassenger trainPassenger = new TrainPassenger();
        String uuid = trainPassenger.getId().toString() + " ";
        request = request.replaceFirst("add_train_passenger ", "");
        trainPassenger = trainPassengerCreator.create(uuid + request);
        byTrainIDSpecification = new ByTrainIDSpecification(trainPassenger.getId());
        trainPassengerRepository.addTrainPassenger(byTrainIDSpecification, trainPassenger);
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
