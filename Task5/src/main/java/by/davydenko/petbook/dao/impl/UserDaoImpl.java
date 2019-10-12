package by.davydenko.petbook.dao.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.controller.command.util.Error;
import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.UserDao;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.Role;
import by.davydenko.petbook.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private final static String SELECT_ALL_USERS = "SELECT id,role FROM petbook.users WHERE role='USER' OR role='GUEST' ";
    private final static String SELECT_BY_LOGIN_PASSWORD = "SELECT id,role FROM petbook.users WHERE login=? AND password=?";
    private final static String SELECT_USERS_BY_NAME = "SELECT petbook.users.id,petbook.users.role " +
            "FROM (petbook.users INNER JOIN petbook.pets ON users.id = pets.userId)" +
            "WHERE petbook.pets.name LIKE ? GROUP BY petbook.pets.name LIMIT ?";
    private final static String SELECT_ADMIN_NEXT_PAGING_USERS_BY_PET_NAME = "SELECT petbook.users.id,petbook.users.role " +
            "FROM (petbook.users INNER JOIN petbook.pets ON users.id = pets.userId)" +
            "WHERE petbook.pets.name LIKE ? AND petbook.users.id>? AND petbook.users.role!='ADMIN' GROUP BY petbook.pets.name LIMIT ?";
    private final static String SELECT_ADMIN_NEXT_PAGING_USERS = "SELECT petbook.users.id,petbook.users.role " +
            "FROM (petbook.users INNER JOIN petbook.pets ON users.id = pets.userId)" +
            "WHERE petbook.users.id>? AND petbook.users.role!='ADMIN' GROUP BY petbook.users.id LIMIT ?";
    private final static String SELECT_ADMIN_PREV_PAGING_USERS_BY_PET_NAME = "SELECT petbook.users.id,petbook.users.role " +
            "FROM (petbook.users INNER JOIN petbook.pets ON users.id = pets.userId)" +
            "WHERE  petbook.users.id<? AND petbook.users.role!=? AND petbook.pets.name LIKE ? ORDER BY petbook.users.id DESC LIMIT ?";
    private final static String SELECT_ADMIN_PREV_PAGING_USERS = "SELECT  id,role FROM petbook.users WHERE id<? AND role!='ADMIN' ORDER BY id DESC LIMIT ?";
    private final static String SELECT_USERS_FROM_TO = "SELECT  id,role FROM petbook.users WHERE id>=? AND id<=? AND role!='ADMIN'";
    private final static String INSERT_USER_BY_LOGIN_PASSWORD_ROLE = "INSERT INTO petbook.users " +
            "(`login`, `password`, `role`)" +
            " VALUES (?, ?, ?)";
    private final static String SELECT_USER_BY_ID = "SELECT id,login,password,role FROM petbook.users WHERE id=? ";
    private final static String DELETE_USER_LOGIN = "DELETE  FROM petbook.users WHERE login=? ";
    private final static String UPDATE_USER_ROLE = "UPDATE petbook.users  SET role=? WHERE id=? ";
    private final static String UPDATE_USER_LOGIN = "UPDATE petbook.users  SET login=? WHERE id=? ";
    private final static String UPDATE_USER_PASSWORD = "UPDATE petbook.users  SET password=? WHERE id=? ";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ConnectionPool connectionPool;

    public UserDaoImpl() {
        connectionPool = ConnectionPool.getInstance();
    }

    private void closeConnection(ConnectionPool cp, Connection c, PreparedStatement p) throws DaoException {
        try {
            cp.closeConnection(c, p);
        } catch (ConnectionPoolException e) {
            throw new DaoException("cannot close connection");
        }
    }

    private void closeConnection(ConnectionPool cp, Connection c, PreparedStatement p, ResultSet r) throws DaoException {
        try {
            cp.closeConnection(c, p, r);
        } catch (ConnectionPoolException e) {
            throw new DaoException("cannot close connection");
        }
    }

    private void takeConnection() throws DaoException {
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DaoException("cannot close connection");
        }
    }

    @Override
    public Optional<List<User>> readUsers() throws DaoException {
        List<User> users = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            resultSet = preparedStatement.executeQuery();
            users = new ArrayList<>();
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
        takeConnection();
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT_USER_BY_LOGIN_PASSWORD_ROLE);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role.name());
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            Error error = Error.getInstance();
            error.setNewLogin("user exist");
            throw new DaoException("duplicate login", e);
        } catch (SQLException e) {
            closeConnection(connectionPool, connection, preparedStatement);
            throw new DaoException(e);
        }
        closeConnection(connectionPool, connection, preparedStatement);
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
            throw new DaoException("cannot read user by id", e);
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
    public Optional<List<User>> readNextPaging(int id, String searchUserValue) throws DaoException {
        List<User> users = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ADMIN_NEXT_PAGING_USERS_BY_PET_NAME);
            preparedStatement.setString(1, searchUserValue + "%");
            preparedStatement.setInt(2, id);
            preparedStatement.setInt(3, Attribute.ADMIN_PAGING_USERS_INTERVAL);
            resultSet = preparedStatement.executeQuery();
            List<User> usersBuf = new ArrayList<>();
            while (resultSet.next()) {
                resultSet.getRow();
                User user = new User();
                user.setId(resultSet.getInt(Attribute.ID));
                user.setRole(Role.valueOf(resultSet.getString(Attribute.ROLE)));
                usersBuf.add(user);
            }
            if (usersBuf.size() != 0) {
                users = usersBuf;
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("paging next by id");
        }
        return Optional.ofNullable(users);
    }

    @Override
    public Optional<List<User>> readNextPaging(int id) throws DaoException {
        List<User> users = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ADMIN_NEXT_PAGING_USERS);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, Attribute.ADMIN_PAGING_USERS_INTERVAL);
            resultSet = preparedStatement.executeQuery();
            List<User> usersBuf = new ArrayList<>();
            while (resultSet.next()) {
                resultSet.getRow();
                User user = new User();
                user.setId(resultSet.getInt(Attribute.ID));
                user.setRole(Role.valueOf(resultSet.getString(Attribute.ROLE)));
                usersBuf.add(user);
            }
            if (usersBuf.size() != 0) {
                users = usersBuf;
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("paging next by id");
        }
        return Optional.ofNullable(users);
    }


    @Override
    public Optional<List<User>> readPrevPaging(int id) throws DaoException {
        List<User> users = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ADMIN_PREV_PAGING_USERS);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, Attribute.ADMIN_PAGING_USERS_INTERVAL);
            resultSet = preparedStatement.executeQuery();
            List<User> usersBuf = new ArrayList<>();
            while (resultSet.next()) {
                resultSet.getRow();
                User user = new User();
                user.setId(resultSet.getInt(Attribute.ID));
                user.setRole(Role.valueOf(resultSet.getString(Attribute.ROLE)));
                usersBuf.add(user);
            }
            if (usersBuf.size() != 0) {
                users = reverse(usersBuf);
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("paging prev by id", e);
        }
        return Optional.ofNullable(users);
    }

    @Override
    public Optional<List<User>> readPrevPaging(int id, String searchValue) throws DaoException {
        List<User> users = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ADMIN_PREV_PAGING_USERS_BY_PET_NAME);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, "ADMIN");
            preparedStatement.setString(3, searchValue + "%");
            preparedStatement.setInt(4, Attribute.ADMIN_PAGING_USERS_INTERVAL);
            resultSet = preparedStatement.executeQuery();
            List<User> usersBuf = new ArrayList<>();
            while (resultSet.next()) {
                resultSet.getRow();
                User user = new User();
                user.setId(resultSet.getInt(Attribute.ID));
                user.setRole(Role.valueOf(resultSet.getString(Attribute.ROLE)));
                usersBuf.add(user);
            }
            if (usersBuf.size() != 0) {
                users = reverse(usersBuf);
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("paging prev by name", e);
        }
        return Optional.ofNullable(users);
    }

    private <T> List<T> reverse(List<T> list) {
        List<T> reverse = new ArrayList<>(list);
        Collections.reverse(reverse);
        return reverse;
    }

    @Override
    public Optional<List<User>> readFromTo(int from, int to) throws DaoException {
        List<User> users = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_USERS_FROM_TO);
            preparedStatement.setInt(1, from);
            preparedStatement.setInt(2, to);
            resultSet = preparedStatement.executeQuery();
            List<User> usersBuf = new ArrayList<>();
            while (resultSet.next()) {
                resultSet.getRow();
                User user = new User();
                user.setId(resultSet.getInt(Attribute.ID));
                user.setRole(Role.valueOf(resultSet.getString(Attribute.ROLE)));
                usersBuf.add(user);
            }
            if (usersBuf.size() != 0) {
                users = usersBuf;
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("get users from id to id");
        }
        return Optional.ofNullable(users);
    }

    @Override
    public Optional<User> readAdmin() throws DaoException {
        User user = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_LOGIN_PASSWORD);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getRow();
                user = new User();
                user.setId(resultSet.getInt(Attribute.ID));
                user.setLogin(resultSet.getString(Attribute.LOGIN));
                user.setPassword(resultSet.getString(Attribute.PASSWORD));
                user.setRole(Role.valueOf(resultSet.getString(Attribute.ROLE)));
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
    public void updateLogin(int id, String login) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_USER_LOGIN);
            preparedStatement.setString(1, login);
            preparedStatement.setInt(2, id);
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updatePassword(int id, String password) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_USER_PASSWORD);
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, id);
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }
            connectionPool.closeConnection(connection, preparedStatement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> readById(int id) throws DaoException {
        User user = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getRow();
                user = new User();
                user.setId(resultSet.getInt(Attribute.ID));
                user.setLogin(resultSet.getString(Attribute.LOGIN));
                user.setPassword(resultSet.getString(Attribute.PASSWORD));
                user.setRole(Role.valueOf(resultSet.getString(Attribute.ROLE)));
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<List<User>> readByPetName(String searchUserValue) throws DaoException {
        List<User> users = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_USERS_BY_NAME);
            preparedStatement.setString(1, searchUserValue + "%");
            preparedStatement.setInt(2, Attribute.ADMIN_PAGING_USERS_INTERVAL);
            resultSet = preparedStatement.executeQuery();
            List<User> usersBuf = new ArrayList<>();
            while (resultSet.next()) {
                resultSet.getRow();
                User user = new User();
                user.setId(resultSet.getInt(Attribute.ID));
                user.setRole(Role.valueOf(resultSet.getString(Attribute.ROLE)));
                usersBuf.add(user);
            }
            if (usersBuf.size() != 0) {
                users = usersBuf;
            }
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("search user by pet name", e);
        }
        return Optional.ofNullable(users);
    }


    @Override
    public int getUsersCapacity() throws DaoException {
        return 0;
    }
}
