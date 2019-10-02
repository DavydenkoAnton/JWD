package by.davydenko.petbook.service;

import by.davydenko.petbook.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;

public interface UserService extends Service<User> {

    void uploadName(String name, String id) throws ServiceException;

    Optional<User> getByLoginPassword(String login, String password) throws ServiceException;

    void deleteByLogin(String login) throws ServiceException;

    void registerUser(String login, String password,String passwordCheck) throws ServiceException;

    int getIdByLogin(String login) throws ServiceException;

    Optional<User> getById(String userId) throws ServiceException;

    void uploadAvatar(Part part,String path, String userId) throws ServiceException;

    Optional<List<User>> getAllUsers() throws ServiceException;

    void changeRole(String id)throws ServiceException;
}