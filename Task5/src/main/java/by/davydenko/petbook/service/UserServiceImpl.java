package by.davydenko.petbook.service;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.dao.UserDaoMySqlImpl;
import by.davydenko.petbook.dao.DaoMySqlException;
import by.davydenko.petbook.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class.getName());
    UserDaoMySqlImpl userDaoMySql;
    List<User> users;

    /**
     * Method return List<T> of user entity
     * @param request String value
     * @return List
     */
    @Override
    public List<User> getUsers(String request) {
        userDaoMySql = new UserDaoMySqlImpl();
        users = new ArrayList<>();
        try {
            users = userDaoMySql.readUsers();
        } catch (DaoMySqlException e) {
            LOGGER.error(e);
        }
        return users;
    }
}
