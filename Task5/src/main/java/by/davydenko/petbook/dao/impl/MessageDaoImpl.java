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

    private final static String SELECT_CHAT_MESSAGES = "SELECT message,date,userId,senderId FROM petbook.messages " +
            "WHERE (userId=? OR senderId=?) AND (userId=? OR senderId=?) ORDER BY date DESC ";
    private final static String SELECT_MESSAGE_BY_USER_ID = "SELECT message,date,userId,senderId FROM petbook.messages WHERE userId=?";
    private final static String INSERT_MESSAGE = "INSERT INTO petbook.messages " +
            "(message, userId, senderId,date) VALUES (?, ?, ?,?)";
    private final static String DELETE_BY_USER_ID = "DELETE FROM petbook.messages WHERE userId=?";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ConnectionPool connectionPool;

    public MessageDaoImpl() {
        connectionPool = ConnectionPool.getInstance();
    }

    private void closeConnection(ConnectionPool cp, Connection c, PreparedStatement p) throws DaoException {
        try {
            cp.closeConnection(c, p);
        } catch (ConnectionPoolException e) {
            throw new DaoException("cannot close connection", e);
        }
    }

    private void closeConnection(ConnectionPool cp, Connection c, PreparedStatement p, ResultSet r) throws DaoException {
        try {
            cp.closeConnection(c, p, r);
        } catch (ConnectionPoolException e) {
            throw new DaoException("cannot close connection", e);
        }
    }

    private void takeConnection() throws DaoException {
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DaoException("cannot close connection", e);
        }
    }

    @Override
    public void create(Message message) throws DaoException {
        try {
            takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT_MESSAGE);
            preparedStatement.setString(1, message.getMessage());
            preparedStatement.setInt(2, message.getUserId());
            preparedStatement.setInt(3, message.getSenderId());
            preparedStatement.setString(4, message.getDate());
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }
            closeConnection(connectionPool,connection, preparedStatement);
        } catch (SQLException  e) {
            closeConnection(connectionPool,connection, preparedStatement);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Message> read(int userId) throws DaoException {
        Message message = null;
        try {
            takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_MESSAGE_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getRow();
                message = new Message();
                message.setMessage(resultSet.getString(Attribute.MESSAGE));
                message.setDate(resultSet.getString(Attribute.MESSAGE_DATE));
                message.setSenderId(resultSet.getInt(Attribute.SENDER_ID));
                message.setUserId(resultSet.getInt(Attribute.USER_ID));
            }
            closeConnection(connectionPool,connection, preparedStatement,resultSet);
        } catch (SQLException  e) {
            closeConnection(connectionPool,connection, preparedStatement,resultSet);
            throw new DaoException(e);
        }
        return Optional.ofNullable(message);
    }

    @Override
    public Optional<List<Message>> readChatMessages(int userId, int senderId) throws DaoException {
        List<Message> messages = new ArrayList<>();
        try {
            takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_CHAT_MESSAGES);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, senderId);
            preparedStatement.setInt(4, senderId);
            resultSet = preparedStatement.executeQuery();
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
            closeConnection(connectionPool,connection, preparedStatement,resultSet);
        } catch (SQLException  e) {
            closeConnection(connectionPool,connection, preparedStatement,resultSet);
            throw new DaoException(e);
        }
        return Optional.ofNullable(messages);
    }

    @Override
    public void update(Message entity) {

    }

    @Override
    public void delete(int userId) throws DaoException {
        try {
            takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }
            closeConnection(connectionPool,connection, preparedStatement);
        } catch (SQLException  e) {
            throw new DaoException(e);
        }
    }
}
