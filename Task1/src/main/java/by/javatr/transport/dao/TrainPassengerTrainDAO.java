package by.javatr.transport.dao;

import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.exception.DaoException;

import java.io.IOException;

public interface TrainPassengerTrainDAO extends TrainDAO {
    void addTrainPassenger(String request) throws DaoException;
}
