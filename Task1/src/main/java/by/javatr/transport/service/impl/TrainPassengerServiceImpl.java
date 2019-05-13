package by.javatr.transport.service.impl;

import by.javatr.transport.creator.TrainPassengerCreator;
import by.javatr.transport.creator.factory.FactoryCreator;
import by.javatr.transport.dao.TrainCarPassengerDAO;
import by.javatr.transport.dao.TrainPassengerDAO;
import by.javatr.transport.dao.factory.DAOFactory;
import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.exception.DaoException;
import by.javatr.transport.exception.ParseException;
import by.javatr.transport.exception.RepositoryException;
import by.javatr.transport.exception.TrainPassengerException;
import by.javatr.transport.parser.TrainPassengerParser;
import by.javatr.transport.parser.factory.FactoryParser;
import by.javatr.transport.repository.repositoryimpl.TrainPassengerRepository;
import by.javatr.transport.repository.specificationimpl.ByTrainIDSpecification;
import by.javatr.transport.service.TrainPassengerService;

import javax.swing.text.html.parser.Parser;
import java.util.UUID;

public class TrainPassengerServiceImpl implements TrainPassengerService {

    private TrainPassengerRepository trainPassengerRepository = TrainPassengerRepository.getInstance();
    private FactoryCreator factoryCreator=FactoryCreator.getInstance();
    private TrainPassengerCreator trainPassengerCreator=factoryCreator.getTrainPassengerCreator();
    private ByTrainIDSpecification byTrainIDSpecification;

    @Override
    public String getAllTrainPassenger() throws RepositoryException {
        String trains = trainPassengerRepository.getAll();
        return trains;
    }

    public void addTrainPassenger(String request) throws DaoException, TrainPassengerException, ParseException {
        TrainPassenger trainPassenger = new TrainPassenger();
        String uuid=trainPassenger.getId().toString()+" ";
        request=request.replaceFirst("add_train_passenger ","");
        trainPassenger=trainPassengerCreator.create(uuid+request);
        byTrainIDSpecification = new ByTrainIDSpecification(trainPassenger.getId());
        trainPassengerRepository.addTrainPassenger(byTrainIDSpecification,trainPassenger);
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
