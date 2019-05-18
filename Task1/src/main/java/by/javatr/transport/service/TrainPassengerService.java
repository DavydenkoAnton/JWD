package by.javatr.transport.service;

import by.javatr.transport.exception.*;

import java.io.IOException;
import java.util.UUID;

public interface TrainPassengerService {
    void addTrainPassenger(String request) throws IOException, DaoException, TrainPassengerException, ParseException;

    String getAllTrainPassenger() throws ParseException, DaoException, RepositoryException, TxtTrainPassengerDAOExeption;

    String sortById();
}
