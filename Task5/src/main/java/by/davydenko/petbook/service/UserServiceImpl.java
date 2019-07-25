package by.davydenko.petbook.service;

import by.davydenko.petbook.dao.impl.UserDaoMySqlImpl;
import by.davydenko.petbook.dao.DaoMySqlException;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    UserDaoMySqlImpl userDaoMySql;
    List<User> users;
    User user;


    public List<User> getUsers() throws UserServiceException {

        userDaoMySql = new UserDaoMySqlImpl();
        users = new ArrayList<>();
        try {
            users = userDaoMySql.readUsers();
        } catch (DaoMySqlException e) {
            logger.error(e);
            throw new UserServiceException(e);
        }
        return users;
    }

    /**
     *
     * @param login String
     * @param password String
     * @return User
     * @throws UserServiceException
     */
    public User getUser(String login,String password) throws UserServiceException {
        userDaoMySql = new UserDaoMySqlImpl();
        try {
            user = userDaoMySql.read(login,password);
        } catch (DaoMySqlException e) {
            logger.error(e);
            throw new UserServiceException(e);
        }
        return user;
    }

    public void addUser(User user) {
        userDaoMySql = new UserDaoMySqlImpl();
        try {
            userDaoMySql.create(user);
        } catch (DaoMySqlException e) {
            e.printStackTrace();
        }
    }



//    public boolean existUser(String login, String password) throws UserServiceException {
//        userDaoMySql = new UserDaoMySqlImpl();
//        try {
//            user = userDaoMySql.getUser(login, password);
//        } catch (DaoMySqlException e) {
//            logger.error(e);
//            throw new UserServiceException(e);
//        }
//        boolean check = user.getLogin().equals(login) && user.getPassword().equals(password);
//        return check;
//    }
}
