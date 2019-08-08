package by.davydenko.petbook.service;

import by.davydenko.petbook.dao.DaoFactory;
import by.davydenko.petbook.dao.UserDao;
import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.creator.CreatorException;
import by.davydenko.petbook.service.creator.CreatorFactory;
import by.davydenko.petbook.service.creator.UserCreator;
import by.davydenko.petbook.service.validator.UserValidator;
import by.davydenko.petbook.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static final int PAGING_CAPACITY = 10;
    private UserDao userDao;
    private DaoFactory daoFactory;
    private ValidatorFactory validatorFactory;
    private UserValidator userValidator;
    private UserCreator userCreator;
    private CreatorFactory creatorFactory;

    public UserServiceImpl() {
        daoFactory = DaoFactory.getInstance();
        validatorFactory = ValidatorFactory.getInstance();
        creatorFactory = CreatorFactory.getInstance();
        userDao = daoFactory.getUserDao();
        userValidator = validatorFactory.getUserValidator();
        userCreator = creatorFactory.getUserCreator();
    }

    @Override
    public List<User> getUsers(HttpSession session) throws ServiceException {
        List<User> users = new ArrayList<>();

        userDao = daoFactory.getUserDao();
        try {
            users = userDao.readUsers();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }

        return users;
    }

    @Override
    public void pagingNext(HttpSession httpSession) throws ServiceException {
        List<User> users = new ArrayList<>();
        if (httpSession.getAttribute("users") == null) {
            try {
                users = userDao.readUsers();
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
            List<User> bufUsers = new ArrayList<>();
            for (int i = 0; i < PAGING_CAPACITY; i++) {
                if (i < users.size()) {
                    bufUsers.add(users.get(i));
                }
            }
            httpSession.setAttribute("users", bufUsers);
            httpSession.setAttribute("pagingNext", bufUsers.size());
        } else {
            List<User> buffUsers = new ArrayList<>();
            int from = (int) httpSession.getAttribute("pagingNext");
            int to = from + PAGING_CAPACITY;
            try {
                users = userDao.readUsers();
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
            if (users.size() > from) {
                for (int i = from; i < to; i++) {
                    if (i < users.size()) {
                        buffUsers.add(users.get(i));
                    } else {
                        break;
                    }
                }
                httpSession.setAttribute("users", buffUsers);
                httpSession.setAttribute("pagingNext", from + buffUsers.size());

            }
        }
    }

    @Override
    public void pagingPrev(HttpSession httpSession) throws ServiceException {

        List<User> users = new ArrayList<>();
        List<User> buffUsers = new ArrayList<>();
        int to = (int) httpSession.getAttribute("pagingNext");
        int from = to - PAGING_CAPACITY;

        try {
            users = userDao.readUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        if (from > 0) {
            for (int i = from; i < to; i++) {
                if (i < users.size()) {
                    buffUsers.add(users.get(i));
                }
            }
            httpSession.setAttribute("users", buffUsers);
            httpSession.setAttribute("pagingNext", from);
        } else {
            for (int i = 0; i < PAGING_CAPACITY; i++) {
                if (i < users.size()) {
                    buffUsers.add(users.get(i));
                } else {
                    break;
                }
            }
            httpSession.setAttribute("users", buffUsers);
            httpSession.setAttribute("pagingNext", 0);
        }
    }

    @Override
    public User getUserByLoginPassword(HttpServletRequest request) throws ServiceException {
        User user = null;
        String login = "";
        String password = "";

        try {
            login = userCreator.createLogin(request);
            password = userCreator.createPassword(request);
        } catch (CreatorException e) {
            logger.error(getClass().getName() + "[ cannot get user ]");
            throw new ServiceException(e);
        }

        try {
            user = userDao.read(login, password);
        } catch (DaoException e) {
            logger.error(getClass().getName() + "[ cannot get user ]");
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public void deleteUser(HttpServletRequest request) throws ServiceException {
        String login="";
        try {
            login=userCreator.createLogin(request);
            userDao.delete(login);
        } catch (CreatorException | DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }


    }

    @Override
    public void addUser(HttpServletRequest request) throws ServiceException {
        User user;


        try {
            user = userCreator.create(request);
        } catch (CreatorException e) {
            logger.error(getClass().getName() + "[ cannot create user ]");
            throw new ServiceException(e);
        }

        try {
            userDao.create(user);
        } catch (DaoException | ConnectionPoolException e) {
            logger.error(getClass().getName() + "[ cannot get user ]");
            throw new ServiceException(e);
        }
    }


    @Override
    public int getIdByLogin(HttpServletRequest request) throws ServiceException {
        String login = "";
        int id;

        if (userValidator.validLogin(request)) {
            login = request.getParameter("login");
        }

        try {
            id = userDao.getIdByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return id;
    }


    @Override
    public int getId(HttpSession httpSession) throws ServiceException {
        int id = 0;
        if (userValidator.validId(httpSession)) {
            id = (int) httpSession.getAttribute("id");
        }
        return id;
    }
}

