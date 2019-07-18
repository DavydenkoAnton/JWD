package by.davydenko.petbook.dao.impl;

import by.davydenko.petbook.dao.Dao;
import by.davydenko.petbook.dao.DaoMySqlException;
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

public class UserDaoMySqlImpl implements UserDao {

    private final static Logger logger = LogManager.getLogger(UserDaoMySqlImpl.class);
    private final static String SELECT_USERS = "SELECT login,password,name,email,phoneNumber,age FROM petbook.users ORDER BY id";
    private final static String INSERT_USER = "INSERT INTO petbook.users " +
            "(`login`, `password`, `name`, `email`, `phoneNumber`, `age`)" +
            " VALUES (?, ?, ?, ?, ?, ?)";
    private final static String SELECT_USER_ID = "SELECT name,email FROM petbook.users WHERE id=? ";
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
    public List<User> readUsers() throws DaoMySqlException {

        users = new ArrayList<>();
        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            Class.forName(DBHelper.DB_DRIVER_CLASS);
            connection = DriverManager.getConnection(url, login, password);
            preparedStatement = connection.prepareStatement(SELECT_USERS);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e);
            throw new DaoMySqlException(e);
        }

        try {
            while (resultSet.next()) {
                User user = new User();
                user.setLogin(resultSet.getNString(DBHelper.Users.LOGIN.getName()));
                user.setPassword(resultSet.getString(DBHelper.Users.PASSWORD.getName()));
                user.setName(resultSet.getString(DBHelper.Users.NAME.getName()));
                user.setEmail(resultSet.getString(DBHelper.Users.EMAIL.getName()));
                user.setPhoneNumber(resultSet.getInt(DBHelper.Users.PHONE.getName()));
                user.setAge(resultSet.getInt(DBHelper.Users.AGE.getName()));
                users.add(user);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DaoMySqlException(e);
        }
        return users;
    }

    @Override
    public void create(User user) throws DaoMySqlException {

        connection = null;
        preparedStatement = null;
        connectionPool = ConnectionPool.getInstance();


        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DaoMySqlException(e);
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

            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DaoMySqlException(e);
            }
            throw new DaoMySqlException(e);
        }/* finally {
            try {
                connectionPool.closeConnection(connection, preparedStatement);
            } catch (ConnectionPoolException e) {
                throw new DaoMySqlException(e);
            }
        }*/


    }

    @Override
    public User read(Integer id) throws DaoMySqlException {
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
            throw new DaoMySqlException(e);
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
            throw new DaoMySqlException(e);
        }
        return user;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Integer identity) {

    }


}
