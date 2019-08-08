package by.davydenko.petbook.dao.impl;

import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.UserDao;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.dao.util.DBHelper;
import by.davydenko.petbook.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final static Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private final static String ADMIN = "admin";
    private final static String SELECT_USERS_COUNT = "SELECT name,role FROM petbook.users  ";
    private final static String SELECT_USERS = "SELECT role FROM petbook.users WHERE role=? ";
    private final static String SELECT_USER_LOGIN_PASSWORD = "SELECT name,role,id FROM petbook.users WHERE login=? AND password=?";
    private final static String SELECT_ID_BY_LOGIN = "SELECT id FROM petbook.users WHERE login=?";
    private final static String INSERT_USER = "INSERT INTO petbook.users " +
            "(`login`, `password`, `name`, `email`, `phoneNumber`, `age`, `role`)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String SELECT_USER_ID = "SELECT name,email FROM petbook.users WHERE id=? ";
    private final static String DELETE_USER_LOGIN = "DELETE  FROM petbook.users WHERE login=? ";
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
    public List<User> readUsers() throws DaoException {

        users = new ArrayList<>();
        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            Class.forName(DBHelper.DB_DRIVER_CLASS);
            connection = DriverManager.getConnection(url, login, password);
            preparedStatement = connection.prepareStatement(SELECT_USERS_COUNT);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e);
            throw new DaoException(e);
        }

        try {
            while (resultSet.next()) {
                String role = resultSet.getString(DBHelper.Users.ROLE.getName());
                if (!role.equals(ADMIN)) {
                    User user = new User();
                    user.setName(resultSet.getString(DBHelper.Users.NAME.getName()));
                    users.add(user);
                }
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public void create(User user) throws DaoException {

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
            preparedStatement = connection.prepareStatement(INSERT_USER);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getPhoneNumber());
            preparedStatement.setInt(6, user.getAge());
            preparedStatement.setString(7, user.getRole());

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
    public void create(String login, String password) throws DaoException {

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
            preparedStatement = connection.prepareStatement(INSERT_USER);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getPhoneNumber());
            preparedStatement.setInt(6, user.getAge());
            preparedStatement.setString(7, user.getRole());

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
    public User read(Integer id) throws DaoException {
        user = new User();
        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            Class.forName(DBHelper.DB_DRIVER_CLASS);
            connection = DriverManager.getConnection(url, login, password);
            preparedStatement = connection.prepareStatement(SELECT_USER_ID);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e);
            throw new DaoException(e);
        }

        try {
            while (resultSet.next()) {
                if (resultSet.getInt(id) == id) {
                    user.setName(resultSet.getString(DBHelper.Users.NAME.getName()));
                    user.setEmail(resultSet.getString(DBHelper.Users.EMAIL.getName()));
                    break;
                }
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Integer identity) {

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
            connection.setAutoCommit(false);
            Statement statement=
            preparedStatement = connection.prepareStatement(DELETE_USER_LOGIN);
            preparedStatement.setString(1, login);
            int row=preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            logger.error("----------SQLException------------", e);
            try {
                connectionPool.closeConnection(connection, preparedStatement);
            } catch (ConnectionPoolException ex) {
                logger.error(e);
                throw new DaoException(e);
            }
            throw new DaoException(e);
        }
    }

    /**
     * @param login    String
     * @param password String
     * @return User
     */
    public User read(String login, String password) throws DaoException {
        user = new User();
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
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SELECT_USER_LOGIN_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getRow();
                user.setName(resultSet.getString(DBHelper.Users.NAME.getName()));
                user.setRole(resultSet.getString(DBHelper.Users.ROLE.getName()));
                user.setId(resultSet.getInt(DBHelper.Users.ID.getName()));
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            logger.error("----------SQLException------------", e);
            try {
                connectionPool.closeConnection(connection, preparedStatement);
            } catch (ConnectionPoolException ex) {
                logger.error(e);
                throw new DaoException(e);
            }
            throw new DaoException(e);
        }
        return user;
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
            //connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SELECT_ID_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getRow();
                id = resultSet.getInt(DBHelper.Users.ID.getName());
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            logger.error("----------SQLException------------", e);
            try {
                connectionPool.closeConnection(connection, preparedStatement);
            } catch (ConnectionPoolException ex) {
                logger.error(e);
                throw new DaoException(e);
            }
            throw new DaoException(e);
        }
        return id;
    }

    @Override
    public int getUsersCapacity() throws DaoException {

        users = new ArrayList<>();
        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            Class.forName(DBHelper.DB_DRIVER_CLASS);
            connection = DriverManager.getConnection(url, login, password);
            preparedStatement = connection.prepareStatement(SELECT_USERS);
            preparedStatement.setString(1, "user");
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e);
            throw new DaoException(e);
        }

        try {
            while (resultSet.next()) {
                User user = new User();
                user.setRole(resultSet.getString(DBHelper.Users.ROLE.getName()));
                users.add(user);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users.size();
    }
}
