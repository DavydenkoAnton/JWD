package by.javatr.transport.dao;

import by.javatr.transport.exception.DaoException;

public interface DAO {

    void create();

    void read();

    void update() throws DaoException;

    void delete();
}
