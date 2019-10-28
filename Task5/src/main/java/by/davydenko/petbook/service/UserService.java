package by.davydenko.petbook.service;

import by.davydenko.petbook.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends Service<User> {

    /**
     * Find and return user from the database by login, password.
     *
     * @param login    login of the user.
     * @param password password of the user.
     * @return Optional containing either:
     * <ol>
     * <li>User contain login, password, role, id fields.</li>
     * <li><code>null</code>, if user was not found for given login, password.</li>
     * </ol>
     * @throws ServiceException if any Dao or Creator exception has occurred.
     */
    Optional<User> getByLoginPassword(String login, String password) throws ServiceException;

    /**
     * Delete user from the database by login.
     *
     * @param login login of the user.
     * @throws ServiceException if any Dao or Creator exception has occurred.
     */
    void deleteByLogin(String login) throws ServiceException;

    /**
     * Adding user to database by new login, new password, password identical by previous.
     *
     * @param newLogin          new login of the user.
     * @param newPassword       new password of the user.
     * @param newPasswordRepeat password identical by previous.
     * @throws ServiceException if any Dao or Creator exception has occurred.
     **/
    void registerUser(String newLogin, String newPassword, String newPasswordRepeat) throws ServiceException;

    /**
     * Find and return user from the database by id.
     *
     * @param id id of the user.
     * @return Optional containing either:
     * <ol>
     * <li>User contain fields login, password, role, id..</li>
     * <li><code>null</code>, if user was not found for given id.</li>
     * </ol>
     * @throws ServiceException if any Dao or Creator exception has occurred.
     */
    Optional<User> getById(String id) throws ServiceException;

    /**
     * Find and return users count of database,without admin user.
     *
     * @return int users count.
     * @throws ServiceException if any Dao exception has occurred.
     */
    int getUsersCount() throws ServiceException;

    /**
     * Find and update user role in database by id.
     *
     * @param id id of the user.
     * @throws ServiceException if any Dao or Creator exception has occurred.
     */
    void changeRole(String id) throws ServiceException;

    /**
     * Find and return admin user from the database.
     *
     * @return Optional containing either:
     * <ol>
     * <li>User contain fields login, password, role, id.</li>
     * <li><code>null</code>, if user was not found.</li>
     * </ol>
     * @throws ServiceException if any Dao exception has occurred.
     */
    Optional<User> admin() throws ServiceException;

    /**
     * Find and return users from the database.
     *
     * @param id              start id of the user, after which the search will be performed.
     * @param searchUserValue part or all of the pet’s name to be searched in the database.
     * @return Optional containing either:
     * <ol>
     * <li>Users contain fields login, password, role, id  not null.</li>
     * <li><code>null</code>, if users were not found.</li>
     * </ol>
     * @throws ServiceException if any Dao, Creator exception has occurred.
     */
    Optional<List<User>> getUsersPagingNext(int id, String searchUserValue) throws ServiceException;

    /**
     * Find and return users from the database.
     *
     * @param id          start id of the user, before which the search will be performed.
     * @param searchValue part or all of the pet’s name to be searched in the database.
     * @return Optional containing either:
     * <ol>
     * <li>Users contain fields login, password, role, id  not null.</li>
     * <li><code>null</code>, if users were not found.</li>
     * </ol>
     * @throws ServiceException if any Dao, Creator exception has occurred.
     */
    Optional<List<User>> getUsersPagingPrev(int id, String searchValue) throws ServiceException;

    /**
     * Find and return users from the database.
     *
     * @param from        id of the user, from which the search will be performed.
     * @param to          id of the user to which the search will be performed inclusively.
     * @param searchValue part or all of the pet’s name to be searched in the database.
     * @return Optional containing either:
     * <ol>
     * <li>Users contain fields login, password, role, id  not null.</li>
     * <li><code>null</code>, if users were not found.</li>
     * </ol>
     * @throws ServiceException if any Dao, Creator exception has occurred.
     */
    Optional<List<User>> getUsersFromTo(int from, int to, String searchValue) throws ServiceException;

    /**
     * Find and update login of the user in database.
     *
     * @param login old user login.
     * @param newLogin    new user login.
     * @param newLoginRepeat    must be identical new login.
     * @throws ServiceException if any Dao, Creator exception has occurred.
     */
    void updateLogin(String login, String newLogin, String newLoginRepeat, int id) throws ServiceException;

    /**
     * Find and update password of the user in database.
     *
     * @param password old user password.
     * @param newPassword    new user password.
     * @param newPasswordRepeat    must be identical new password.
     * @throws ServiceException if any Dao, Creator exception has occurred.
     */
    void updatePassword(String password, String newPassword, String newPasswordRepeat, int id) throws ServiceException;
}