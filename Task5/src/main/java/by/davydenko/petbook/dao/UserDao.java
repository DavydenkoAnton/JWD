package by.davydenko.petbook.dao;

import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    List<User> readUsers() throws DaoException;

    @Override
    void create(User entity) throws ConnectionPoolException, DaoException;

    void create(String login,String password) throws DaoException;

    @Override
    User read(Integer identity) throws DaoException;

    void delete(String login) throws DaoException;

    User read(String login, String password)throws DaoException;

    @Override
    void update(User entity);

    @Override
    void delete(Integer identity);

    int getIdByLogin(String login) throws DaoException;

    int getUsersCapacity() throws DaoException;
}
