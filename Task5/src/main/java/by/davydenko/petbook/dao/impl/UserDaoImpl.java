package by.davydenko.petbook.dao.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.controller.command.util.Error;
import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.UserDao;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.Role;
import by.davydenko.petbook.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private final static String SELECT_ALL_USERS = "SELECT id,role FROM petbook.users WHERE role='USER' OR role='GUEST' ";
    private final static String SELECT_BY_LOGIN_PASSWORD = "SELECT id,role FROM petbook.users WHERE login=? AND password=?";
    private final static String SELECT_ID_BY_LOGIN = "SELECT id FROM petbook.users WHERE login=?";
    private final static String INSERT_USER_BY_LOGIN_PASSWORD_ROLE = "INSERT INTO petbook.users " +
            "(`login`, `password`, `role`)" +
            " VALUES (?, ?, ?)";
    private final static String SELECT_USER_BY_ID = "SELECT name,email,phoneNumber,avatarUrl,role FROM petbook.users WHERE id=? ";
    private final static String DELETE_USER_LOGIN = "DELETE  FROM petbook.users WHERE login=? ";
    private final static String UPDATE_USER_AVATAR = "UPDATE petbook.users  SET avatarUrl=? WHERE id=? ";
    private final static String UPDATE_USER_NAME = "UPDATE petbook.users  SET name=? WHERE id=? ";
    private final static String UPDATE_USER_ROLE = "UPDATE petbook.users  SET role=? WHERE id=? ";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ConnectionPool connectionPool;

    public UserDaoImpl() {
        connectionPool = ConnectionPool.getInstance();
    }

    @Override
    public Optional<List<User>> readUsers() throws DaoException {
        List<User> users=null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            resultSet = preparedStatement.executeQuery();
            users=new ArrayList<>();
            while (resultSet.next()) {
                resultSet.getRow();
                User user = new User();
                user.setId(resultSet.getInt(Attribute.ID));
                user.setRole(Role.valueOf(resultSet.getString(Attribute.ROLE)));
                users.add(user);
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("cannot read user by id");
        }
        return Optional.ofNullable(users);
    }

    @Override
    public void create(User user) throws DaoException {

    }

    @Override
    public void create(String login, String password, Role role) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT_USER_BY_LOGIN_PASSWORD_ROLE);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role.name());
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> read(int userId) throws DaoException {
        User user = null;
        try {
            connection = connectionPool.takeConnection();
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
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("cannot read user by id");
        }
        return Optional.ofNullable(user);
    }


    public Optional<User> read(String login, String password) throws DaoException {
        Error error = Error.getInstance();
        User user = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_LOGIN_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getRow();
                user = new User();
                user.setId(resultSet.getInt(Attribute.ID));
                user.setRole(Role.valueOf(resultSet.getString(Attribute.ROLE)));
            }
            if (!resultSet.next()) {
                error.setLogin("wrong login or password");
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
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
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_USER_AVATAR);
            preparedStatement.setString(1, userAvatarURL);
            preparedStatement.setInt(2, id);
            int stateValue = preparedStatement.executeUpdate();
            if (stateValue > 0) {
                connection.commit();
            }
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Cannot update user avatar", e);
        }
    }

    @Override
    public void updateName(int id, String name) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_USER_NAME);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            int stateValue = preparedStatement.executeUpdate();
            if (stateValue > 0) {
                connection.commit();
            }
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateRole(int id, Role role) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_USER_ROLE);
            preparedStatement.setString(1, role.name());
            preparedStatement.setInt(2, id);
            int stateValue = preparedStatement.executeUpdate();
            if (stateValue > 0) {
                connection.commit();
            }
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void delete(String login) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(DELETE_USER_LOGIN);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public int getIdByLogin(String login) throws DaoException {
        int id = 0;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ID_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getRow();
                id = resultSet.getInt(Attribute.ID);
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return id;
    }

    @Override
    public int getUsersCapacity() throws DaoException {
        return 0;
    }
}
