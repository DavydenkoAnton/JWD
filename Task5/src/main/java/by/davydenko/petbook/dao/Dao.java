package by.davydenko.petbook.dao;

import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.Entity;

public interface Dao<T extends Entity> {
    void create(T entity) throws ConnectionPoolException, DaoMySqlException;

    T read(Integer identity) throws DaoMySqlException;

    void update(T entity);

    void delete(Integer identity);
}
