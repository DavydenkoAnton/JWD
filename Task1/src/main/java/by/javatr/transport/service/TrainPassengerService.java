package by.javatr.transport.service;

import by.javatr.transport.exception.DaoException;
import by.javatr.transport.exception.ParseException;
import by.javatr.transport.exception.RepositoryException;
import by.javatr.transport.exception.TrainPassengerException;

import java.io.IOException;
import java.util.UUID;

public interface TrainPassengerService {
    void addTrainPassenger(String request) throws IOException, DaoException, TrainPassengerException;

    String getAllTrainPassenger() throws ParseException, DaoException, RepositoryException;
}
