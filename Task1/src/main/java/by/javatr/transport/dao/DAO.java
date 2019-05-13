package by.javatr.transport.dao;

import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.exception.DaoException;

public interface DAO {

    void create();

    void read() throws DaoException;

    void update() throws DaoException;

    void delete();
}
