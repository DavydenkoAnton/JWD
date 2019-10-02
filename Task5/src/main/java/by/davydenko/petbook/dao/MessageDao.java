package by.davydenko.petbook.dao;

import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.Message;

import java.util.List;
import java.util.Optional;

public interface MessageDao extends Dao<Message> {
    @Override
    void create(Message message) throws  DaoException;

    @Override
    Optional<Message> read(int id) throws DaoException;

    @Override
    void update(Message message);

    @Override
    void delete(int message);

    Optional<List<Message>> readChatMessages(int userId,int senderId) throws DaoException;
}
