package by.davydenko.petbook.dao.impl;

import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.MessageDao;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.dao.util.DBHelper;
import by.davydenko.petbook.entity.Message;
import by.davydenko.petbook.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MessageDaoImpl implements MessageDao {


    private final static Logger logger = LogManager.getLogger(MessageDaoImpl.class);

    private final static String SELECT_USERS = "SELECT name FROM petbook.users ORDER BY id";
    private final static String INSERT_MESSAGE = "INSERT INTO petbook.messages " +
            "(message, user_id, sender_id) VALUES (?, ?, ?)";

    private String url = DBHelper.DB_URL;
    private String login = DBHelper.DB_LOGIN;
    private String password = DBHelper.DB_PASSWORD;
    private List<User> users;
    private User user;
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
            preparedStatement.setInt(2, message.getUserId());
            preparedStatement.setInt(3, message.getSenderId());
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }

        } catch (SQLException e) {
            try {
                connectionPool.closeConnection(connection, preparedStatement);
            } catch (ConnectionPoolException ex) {
                throw new DaoException(e);
            }
            throw new DaoException(e);
        }



    }

    @Override
    public Message read(Integer identity) {
        return null;
    }

    @Override
    public void update(Message entity) {

    }

    @Override
    public void delete(Integer identity) {

    }
}
