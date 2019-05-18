package by.javatr.transport.dao;

import by.javatr.transport.dao.impl.txt.TxtTrainPassengerDAO;
import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.exception.DaoException;
import by.javatr.transport.exception.TxtTrainPassengerDAOExeption;

import java.util.List;

public interface TrainPassengerDAO {

    String getTrainPassenger(String id) throws DaoException;

    void update(List<TrainPassenger> trainPassengers) throws DaoException;

    List<TrainPassenger> read() throws TxtTrainPassengerDAOExeption;
}
