package by.davydenko.petbook.dao.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.MessageDao;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageDaoImpl implements MessageDao {

    private final static String SELECT_CHAT_MESSAGES = "SELECT message,date,user_id,sender_id FROM petbook.messages " +
            "WHERE (user_id=? OR sender_id=?) AND (user_id=? OR sender_id=?) ORDER BY date DESC ";
    private final static String INSERT_MESSAGE = "INSERT INTO petbook.messages " +
            "(message, user_id, sender_id,date) VALUES (?, ?, ?,?)";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ConnectionPool connectionPool;


    @Override
    public void create(Message message) throws DaoException {
        connection = null;
        preparedStatement = null;
        connectionPool = ConnectionPool.getInstance();

        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT_MESSAGE);

            preparedStatement.setString(1, message.getMessage());
            preparedStatement.setInt(2, message.getSenderId());
            preparedStatement.setInt(3, message.getUserId());
            preparedStatement.setString(4, message.getDate());
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try {
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Message> read(int id) {
        Message message = null;
        return Optional.ofNullable(message);
    }

    @Override
    public Optional<List<Message>> readChatMessages(int userId, int senderId) throws DaoException {
        List<Message> messages;
        connection = null;
        connectionPool = ConnectionPool.getInstance();
        preparedStatement = null;
        resultSet = null;

        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DaoException("cannot create connection");
        }
        try {
            preparedStatement = connection.prepareStatement(SELECT_CHAT_MESSAGES);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, senderId);
            preparedStatement.setInt(4, senderId);
            resultSet = preparedStatement.executeQuery();
            messages = new ArrayList<>();
            while (resultSet.next()) {
                resultSet.getRow();
                Message message = new Message();
                message.setMessage(resultSet.getString(Attribute.MESSAGE));
                message.setDate(resultSet.getString(Attribute.MESSAGE_DATE));
                message.setSenderId(resultSet.getInt(Attribute.SENDER_ID));
                message.setUserId(resultSet.getInt(Attribute.USER_ID));
                messages.add(message);
            }
            if (messages.size() == 0) {
                messages = null;
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try {
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(messages);
    }

    @Override
    public void update(Message entity) {

    }

    @Override
    public void delete(int id) {

    }
}
