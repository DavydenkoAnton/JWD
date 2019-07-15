package by.davydenko.petbook.controller.command;

import by.davydenko.petbook.dao.DaoMySqlException;
import by.davydenko.petbook.entity.Entity;
import by.davydenko.petbook.entity.User;

import java.util.List;

public interface UserCommand extends Command<User> {

    @Override
    User get();

    @Override
    List<User> getList() throws DaoMySqlException;

    @Override
    void put(User user);

    @Override
    void putList(List<User> users);
}
