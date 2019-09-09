package by.davydenko.petbook.service;

import by.davydenko.petbook.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface UserService extends Service<User> {
    void uploadName(HttpServletRequest request) throws ServiceException;

    void pagingPrev(HttpSession httpSession) throws ServiceException;

    Optional<User> getUserByLoginPassword(HttpServletRequest request) throws ServiceException;

    void deleteUser(HttpServletRequest request) throws ServiceException;

    void registerUser(HttpServletRequest request) throws ServiceException;

    int getIdByLogin(HttpServletRequest request) throws ServiceException;

    int getId(HttpSession httpSession) throws ServiceException;

    Optional<User> getUserById(HttpServletRequest request) throws ServiceException;

    void pagingNext(HttpSession httpSession) throws ServiceException;

    void uploadAvatar(HttpServletRequest request) throws ServiceException;

}