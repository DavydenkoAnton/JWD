package by.davydenko.petbook.dao;

import by.davydenko.petbook.entity.Role;
import by.davydenko.petbook.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<List<User>> readUsers() throws DaoException;

    @Override
    void create(User user) throws DaoException;

    void create(String login, String password, Role role) throws DaoException;

    @Override
    Optional<User> read(int id) throws DaoException;

    void delete(String login) throws DaoException;

    /**
     * @param login    String
     * @param password String
     * @return User
     */
    Optional<User> read(String login, String password) throws DaoException;

    @Override
    void update(User user);

    @Override
    void delete(int id);

    int getUsersCapacity() throws DaoException;

    Optional<User> readAdmin() throws DaoException;

    Optional<List<User>> readNextPaging(int id, String searchUserValue) throws DaoException;

    Optional<List<User>> readNextPaging(int id) throws DaoException;

    Optional<List<User>> readPrevPaging(int id) throws DaoException;

    Optional<List<User>> readPrevPaging(int id, String searchUserValue) throws DaoException;

    Optional<List<User>> readFromTo(int from, int to) throws DaoException;

    Optional<List<User>> readFromTo(int from, int to, String searchValue)throws DaoException;

    Optional<List<User>> readByPetName(String searchUserValue) throws DaoException;

    Optional<User> readById(int id) throws DaoException;

    void updateLogin(int id, String login) throws DaoException;

    void updatePassword(int id, String password) throws DaoException;

    void updateRole(int id, Role role) throws DaoException;



}
