package by.javatr.transport.dao;

import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.exception.DaoException;

import java.util.List;

public interface TrainPassengerDAO {

    String getTrainPassenger(String id) throws DaoException;

    void update(List<TrainPassenger> trainPassengers) throws DaoException;

    List<TrainPassenger> read() ;
}
