package by.javatr.transport.service.impl;

import by.javatr.transport.creator.factory.FactoryCreator;
import by.javatr.transport.creator.TrainPassengerCreator;
import by.javatr.transport.dao.TrainCarPassengerDAO;
import by.javatr.transport.dao.TrainPassengerTrainDAO;
import by.javatr.transport.dao.factory.DAOFactory;
import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.exception.DaoException;
import by.javatr.transport.exception.ParseException;
import by.javatr.transport.exception.TrainPassengerException;
import by.javatr.transport.parser.TrainPassengerParser;
import by.javatr.transport.parser.factory.FactoryParser;
import by.javatr.transport.repository.impl.AllTrainPassengerSpecification;
import by.javatr.transport.repository.impl.TrainPassengerRepository;
import by.javatr.transport.service.TrainPassengerService;
import by.javatr.transport.validator.FactoryValidator;
import by.javatr.transport.validator.TrainPassengerValidator;

import java.util.List;

public class TrainPassengerServiceImpl implements TrainPassengerService {


    public void addTrainPassenger(String request) throws DaoException, TrainPassengerException {

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        TrainPassengerTrainDAO trainPassengerDAO = daoObjectFactory.getTrainPassengerDAO();
        String id = getID(request);
        trainPassengerDAO.addTrainPassenger(id);
    }


    public void getTrainCarPassenger() {
        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        TrainCarPassengerDAO trainCarPassengerDAO = daoObjectFactory.getTrainCarPassengerDAO();
    }

    public List<TrainPassenger> getAllTrainPassenger() throws ParseException {
        FactoryParser factoryParser = FactoryParser.getInstance();
        TrainPassengerParser trainPassengerParser = factoryParser.getTrainPassengerParser();

        List<String> trainsPassenger = trainPassengerParser.parse();

        FactoryValidator factoryValidator = FactoryValidator.getInstance();
        TrainPassengerValidator trainPassengerValidator = factoryValidator.getTrainPassengerCreator();

        FactoryCreator factoryCreator = FactoryCreator.getInstance();
        TrainPassengerCreator trainPassengerCreator = factoryCreator.getTrainPassengerCreator();

        TrainPassengerRepository trainPassengerRepository = new TrainPassengerRepository();

        if (trainPassengerValidator.valid(trainsPassenger)) {
            trainPassengerRepository.addTrainPassenger(trainPassengerCreator.create(trainsPassenger));
        }

        return trainPassengerRepository.find(new AllTrainPassengerSpecification());

    }

    private String getID(String request) throws TrainPassengerException {
        String id = request.replaceAll("[a-z _]+", "");
        return id;
    }


}
