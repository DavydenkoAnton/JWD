package by.davydenko.petbook.dao;

import by.davydenko.petbook.entity.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    List<User> readUsers() throws DaoMySqlException;

    @Override
    Integer create(User entity);

    @Override
    User read(Integer identity) throws DaoMySqlException;

    @Override
    void update(User entity);

    @Override
    void delete(Integer identity);
}
