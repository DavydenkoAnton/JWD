package by.davydenko.petbook.dao.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.UserDao;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.Role;
import by.davydenko.petbook.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private final static Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private final static String ADMIN = "admin";
    private final static String SELECT_USERS_COUNT = "SELECT name,role FROM petbook.users  ";
    private final static String SELECT_USERS = "SELECT role FROM petbook.users WHERE role=? ";
    private final static String SELECT_USER_LOGIN_PASSWORD = "SELECT name,role,id,avatarUrl FROM petbook.users WHERE login=? AND password=?";
    private final static String SELECT_ID_BY_LOGIN = "SELECT id FROM petbook.users WHERE login=?";
    private final static String SQL_INSERT_USER = "INSERT INTO petbook.users " +
            "(`login`, `password`, `name`, `email`, `phoneNumber`, `age`, `role`)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String SELECT_USER_BY_ID = "SELECT name,email,phoneNumber,avatarUrl,role FROM petbook.users WHERE id=? ";
    private final static String DELETE_USER_LOGIN = "DELETE  FROM petbook.users WHERE login=? ";
    private final static String UPDATE_USER_AVATAR = "UPDATE petbook.users  SET avatarUrl=? WHERE id=? ";
    private final static String UPDATE_USER_NAME = "UPDATE petbook.users  SET name=? WHERE id=? ";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ConnectionPool connectionPool;

    public UserDaoImpl() {
        connectionPool = ConnectionPool.getInstance();
    }

    @Override
    public List<User> readUsers() throws DaoException {
        List<User> users = new ArrayList<>();
        return users;
    }

    @Override
    public void create(User user) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            logger.error(getClass().getName() + " [cannot create connection] ");
            throw new DaoException(e);
        }
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getPhoneNumber());
            preparedStatement.setInt(6, user.getAge());
            preparedStatement.setString(7, user.getRole().name());
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void create(String login, String password) throws DaoException {
    }

    @Override
    public Optional<User> read(int userId) throws DaoException {
        User user = null;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getRow();
                user = new User();
                user.setName(resultSet.getString(Attribute.NAME));
                user.setEmail(resultSet.getString(Attribute.EMAIL));
                user.setPhoneNumber(resultSet.getInt(Attribute.PHONE_NUMBER));
                user.setAvatarURL(resultSet.getString(Attribute.AVATAR_URL));
                user.setRole(Role.valueOf(resultSet.getString(Attribute.ROLE)));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException("cannot read user by id");
        }
        try {
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }


    public Optional<User> read(String login, String password) throws DaoException {
        User user = null;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(SELECT_USER_LOGIN_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getRow();
                user = new User();
                user.setName(resultSet.getString(Attribute.NAME));
                user.setRole(Role.valueOf(resultSet.getString(Attribute.ROLE)));
                user.setAvatarURL(resultSet.getString(Attribute.AVATAR_URL));
                user.setId(resultSet.getInt(Attribute.ID));
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
        return Optional.ofNullable(user);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void updateAvatarURL(int id, String userAvatarURL) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_USER_AVATAR);
            preparedStatement.setString(1, userAvatarURL);
            preparedStatement.setInt(2, id);
            int stateValue = preparedStatement.executeUpdate();
            if (stateValue > 0) {
                connection.commit();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException("Cannot update user avatar", e);
        }
        try {
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Cannot close connection", e);
        }
    }

    @Override
    public void updateName(int id, String name) throws DaoException {
        connection = null;
        connectionPool = ConnectionPool.getInstance();
        preparedStatement = null;
        resultSet = null;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        try {

            preparedStatement = connection.prepareStatement(UPDATE_USER_NAME);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

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
    public void delete(int id) {

    }

    @Override
    public void delete(String login) throws DaoException {
        connection = null;
        connectionPool = ConnectionPool.getInstance();
        preparedStatement = null;
        resultSet = null;


        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(DELETE_USER_LOGIN);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
            preparedStatement.close();

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
    public int getIdByLogin(String login) throws DaoException {
        int id = 0;
        connection = null;
        connectionPool = ConnectionPool.getInstance();
        preparedStatement = null;
        resultSet = null;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(SELECT_ID_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getRow();
                id = resultSet.getInt(Attribute.ID);
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
        return id;
    }

    @Override
    public int getUsersCapacity() throws DaoException {
        return 0;
    }
}
