package by.javatr.transport.service;

import by.javatr.transport.entity.TrainCarPassenger;
import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.exception.DaoException;
import by.javatr.transport.exception.TrainPassengerException;

import java.io.IOException;

public interface TrainPassengerService {
    void addTrainPassenger(String request) throws IOException, DaoException, TrainPassengerException;

    void getTrainCarPassenger();
}
