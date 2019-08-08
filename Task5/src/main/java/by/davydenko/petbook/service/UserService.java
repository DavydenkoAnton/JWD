package by.davydenko.petbook.service;

import by.davydenko.petbook.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService extends Service<User> {
    List<User> getUsers(HttpSession session) throws ServiceException;

    void pagingPrev(HttpSession httpSession) throws ServiceException;

    User getUserByLoginPassword(HttpServletRequest request) throws ServiceException;

    void deleteUser(HttpServletRequest request) throws ServiceException;

    void addUser(HttpServletRequest request) throws ServiceException;

    int getIdByLogin(HttpServletRequest request) throws ServiceException;

    int getId(HttpSession httpSession) throws ServiceException;

    void pagingNext(HttpSession httpSession) throws ServiceException;
}
