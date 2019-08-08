package by.davydenko.petbook.dao;

import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.Message;

public interface MessageDao extends Dao<Message> {
    @Override
    void create(Message message) throws ConnectionPoolException, DaoException;

    @Override
    Message read(Integer id) throws DaoException;

    @Override
    void update(Message message);

    @Override
    void delete(Integer message);

}
