package by.davydenko.petbook.controller.command;

import by.davydenko.petbook.dao.DaoMySqlException;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.UserServiceImpl;

import java.util.List;

public class UserCommandImpl implements UserCommand {

    @Override
    public User get() {
        return null;
    }

    @Override
    public List<User> getList() throws DaoMySqlException {
        UserServiceImpl userService = new UserServiceImpl();
        return userService.getUsers();
    }

    @Override
    public void putList(List<User> entity) {

    }

    @Override
    public void put(User entity) {

    }
}
