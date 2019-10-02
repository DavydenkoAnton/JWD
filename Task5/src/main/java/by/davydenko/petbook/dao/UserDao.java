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

    void updateName(int id,String name)throws DaoException;

    @Override
    void delete(int id);

    int getIdByLogin(String login) throws DaoException;

    int getUsersCapacity() throws DaoException;

    void updateAvatarURL(int id, String userAvatarURL) throws DaoException;

    void updateRole(int id, Role role)throws DaoException;
}
