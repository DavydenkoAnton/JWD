package by.javatr.transport.dao;

import by.javatr.transport.entity.TrainCarPassenger;
import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.exception.DaoException;

import java.util.List;

public interface TrainDAO {

    void create();

    void read() throws DaoException;

    void update() throws DaoException;

    void delete();
}
