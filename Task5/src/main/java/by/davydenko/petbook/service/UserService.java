package by.davydenko.petbook.service;

import by.davydenko.petbook.entity.User;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;

public interface UserService extends Service<User> {

    Optional<User> getByLoginPassword(String login, String password) throws ServiceException;

    void deleteByLogin(String login) throws ServiceException;

    void registerUser(String newLogin, String newPassword, String newPasswordRepeat) throws ServiceException;

    Optional<User> getById(String userId) throws ServiceException;

    Optional<List<User>> getAllUsers() throws ServiceException;

    void changeRole(String id) throws ServiceException;

    Optional<User> admin() throws ServiceException;

    Optional<List<User>> getUsersPagingNext(int id, String searchUserValue) throws ServiceException;

    Optional<List<User>> getUsersPagingNext(int id) throws ServiceException;

    Optional<List<User>> getUsersPagingPrev(int id) throws ServiceException;

    Optional<List<User>> getUsersPagingPrev(int id, String searchUserValue) throws ServiceException;

    Optional<List<User>> getUsersFromTo(int from, int to) throws ServiceException;

    Optional<List<User>> getUsersByNameFirst(String searchUserValue) throws ServiceException;

    void updateLogin(String login, String newLogin, String newLoginRepeat, int id) throws ServiceException;

    void updatePassword(String password, String newPassword, String newPasswordRepeat, int id) throws ServiceException;
}