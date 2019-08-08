package by.davydenko.petbook.dao;

import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.Entity;

public interface Dao<T extends Entity> {
    void create(T entity) throws ConnectionPoolException, DaoException;

    T read(Integer identity) throws DaoException;

    void update(T entity);

    void delete(Integer identity);
}
