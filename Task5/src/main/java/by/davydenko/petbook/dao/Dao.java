package by.davydenko.petbook.dao;

import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.Entity;

import java.util.Optional;

public interface Dao<T extends Entity> {
    void create(T entity) throws  DaoException;

    Optional<T> read(int id) throws DaoException;

    void update(T entity)throws DaoException;

    void delete(int id)throws DaoException;
}
