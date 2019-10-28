package by.davydenko.petbook.service.impl;

import by.davydenko.petbook.controller.command.util.Error;
import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.DaoFactory;
import by.davydenko.petbook.dao.UserDao;
import by.davydenko.petbook.entity.Role;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.UserService;
import by.davydenko.petbook.service.util.XSSFilter;
import by.davydenko.petbook.service.util.creator.CreatorException;
import by.davydenko.petbook.service.util.creator.CreatorFactory;
import by.davydenko.petbook.service.util.creator.UserCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;


public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private UserDao userDao;
    private UserCreator userCreator;
    private Error error;


    public UserServiceImpl() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        CreatorFactory creatorFactory = CreatorFactory.getInstance();
        userDao = daoFactory.getUserDao();
        userCreator = creatorFactory.getUserCreator();
        error = Error.getInstance();
    }

    @Override
    public int getUsersCount() throws ServiceException {
        Optional<List<User>> optionalUsers;
        int usersCount=0;
        try {
            optionalUsers = userDao.readUsers();
            usersCount= optionalUsers.map(List::size).orElse(0);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return usersCount;
    }

    @Override
    public Optional<List<User>> getUsersPagingNext(int id, String searchUserValue) throws ServiceException {
        Optional<List<User>> optionalUsers;
        try {
            if (searchUserValue == null || searchUserValue.isEmpty()) {
                optionalUsers = userDao.readNextPaging(id);
            } else {
                optionalUsers = userDao.readNextPaging(id, searchUserValue);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalUsers;
    }

    @Override
    public Optional<List<User>> getUsersPagingPrev(int id, String searchValue) throws ServiceException {
        Optional<List<User>> optionalUsers;
        try {
            optionalUsers = userDao.readPrevPaging(id, searchValue);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalUsers;
    }

    @Override
    public Optional<List<User>> getUsersFromTo(int from, int to,String searchValue) throws ServiceException {
        Optional<List<User>> optionalUsers;
        try {
            if(searchValue==null||searchValue.isEmpty()) {
                optionalUsers = userDao.readFromTo(from, to);
            }else {
                optionalUsers = userDao.readFromTo(from, to,searchValue);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalUsers;
    }



    @Override
    public Optional<User> getById(String userId) throws ServiceException {
        Optional<User> user;
        int id;
        try {
            id = userCreator.createId(userId);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }

        try {
            user = userDao.readById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }


    @Override
    public Optional<User> getByLoginPassword(String login, String password) throws ServiceException {
        Optional<User> user;
        try {
            login = userCreator.createLogin(login);
            password = userCreator.createPassword(password);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }

        try {
            user = userDao.read(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public void updateLogin(String login, String newLogin, String newLoginRepeat, int id) throws ServiceException {
        try {
            String loginBuf = userCreator.createLogin(login);
            Optional<User> optionalUser = userDao.readById(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (!loginBuf.equals(user.getLogin())) {
                    error.setLogin("wrong login");
                    throw new ServiceException("wrong login " + getClass().getName());
                }
                String newLoginBuf = userCreator.createNewLogin(newLogin);
                String newLoginRepeatBuf = userCreator.createNewLoginRepeat(newLoginRepeat);
                if (newLoginBuf.equals(newLoginRepeatBuf)) {
                    userDao.updateLogin(user.getId(), newLoginBuf);
                } else {
                    error.setNewLogin("not equals");
                    error.setNewLoginRepeat("not equals");
                    throw new ServiceException("new login and new repeat login not equals " + getClass().getName());
                }
            }
        } catch (CreatorException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updatePassword(String password, String newPassword, String newPasswordRepeat, int id) throws ServiceException {
        try {
            String passwordBuf = userCreator.createPassword(password);
            Optional<User> optionalUser = userDao.readById(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (!passwordBuf.equals(user.getPassword())) {
                    error.setPassword("wrong password");
                    throw new ServiceException("wrong password " + getClass().getName());
                }
                String newPasswordBuf = userCreator.createNewPassword(newPassword);
                String newPasswordRepeatBuf = userCreator.createNewPasswordRepeat(newPasswordRepeat);
                if (newPasswordBuf.equals(newPasswordRepeatBuf)) {
                    userDao.updatePassword(id, newPasswordBuf);
                } else {
                    error.setNewPassword("not equals");
                    error.setNewPasswordRepeat("not equals");
                    throw new ServiceException("new password and new repeat password not equals");
                }
            }
        } catch (CreatorException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> admin() throws ServiceException {
        Optional<User> user;
        try {
            user = userDao.readAdmin();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public void deleteByLogin(String userLogin) throws ServiceException {

        try {
            String login = userCreator.createLogin(userLogin);
            userDao.delete(login);
        } catch (CreatorException | DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }


    @Override
    public void changeRole(String id) throws ServiceException {
        try {
            int idTemp = userCreator.createId(id);
            Optional<User> optionalUser = userDao.readById(idTemp);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (user.getRole().equals(Role.USER)) {
                    user.setRole(Role.GUEST);
                } else if (user.getRole().equals(Role.GUEST)) {
                    user.setRole(Role.USER);
                }
                userDao.updateRole(idTemp, user.getRole());
            }
        } catch (CreatorException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void registerUser(String newLogin, String newPassword, String newPasswordRepeat) throws ServiceException {
        Error error = Error.getInstance();
        try {
            String login = userCreator.createNewLogin(newLogin);
            String password = userCreator.createNewPassword(newPassword);
            String passwordRepeat = userCreator.createNewPasswordRepeat(newPasswordRepeat);
            if (password.equals(passwordRepeat)) {
                Role role = userCreator.createRole();
                userDao.create(login, password, role);
            } else {
                error.setNewPassword("not equal");
                error.setNewPasswordRepeat("not equal");
                throw new ServiceException("passwords not equals " + getClass().getName());
            }
        } catch (CreatorException | DaoException e) {
            throw new ServiceException(e);
        }
    }


}

