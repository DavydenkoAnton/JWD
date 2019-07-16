package by.davydenko.petbook.service;

import by.davydenko.petbook.dao.UserDaoMySqlImpl;
import by.davydenko.petbook.dao.DaoMySqlException;
import by.davydenko.petbook.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl  {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class.getName());
    UserDaoMySqlImpl userDaoMySql;
    List<User> users;


    public List<User> getUsers() throws DaoMySqlException {
        System.out.println("service");
        userDaoMySql = new UserDaoMySqlImpl();
        users = new ArrayList<>();

            users = userDaoMySql.readUsers();

        return users;
    }

    public void addUser(User user){

        userDaoMySql = new UserDaoMySqlImpl();
        userDaoMySql.create(user);
    }
}
