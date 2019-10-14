package by.davydenko.petbook.service.impl;

import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.DaoFactory;
import by.davydenko.petbook.dao.UserDao;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.Role;
import by.davydenko.petbook.entity.User;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceImplTest {

    private static UserDao userDao;

    @BeforeAll
    static void init() throws ConnectionPoolException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.init();
        DaoFactory daoFactory = DaoFactory.getInstance();
        userDao = daoFactory.getUserDao();
    }

    @Test
    @Order(1)
    @DisplayName("create new user")
    void registerUser() {
        String login = "testLogin";
        String password = "testPassword";
        Role role = Role.USER;
        assertDoesNotThrow(() -> userDao.create(login, password, role));
    }

    @Test
    @Order(2)
    @DisplayName("create  duplicate user")
    void registerDuplicateUser() {
        String login = "testLogin";
        String password = "testPassword";
        Role role = Role.USER;
        assertThrows(DaoException.class, () -> userDao.create(login, password, role));
    }

    @Test
    @Order(3)
    @DisplayName("find user by login, password")
    void getByLoginPassword() throws DaoException {
        String login = "testLogin";
        String password = "testPassword";
        Optional<User> optionalUser = userDao.read(login, password);
        assertNotEquals(Optional.empty(), optionalUser);
    }

    @Test
    @Order(3)
    @DisplayName("find user by id")
    void getById() throws DaoException {
        String login = "testLogin";
        String password = "testPassword";
        Optional<User> optionalUser = userDao.read(login, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            optionalUser = userDao.readById(user.getId());
            assertNotEquals(Optional.empty(), optionalUser);
        } else {
            fail("[ cannot get user by login, password ]");
        }
    }

    @Test
    @Order(4)
    @DisplayName("find user by wrong id")
    void getByWrongId() throws DaoException {
        int id = 0;
        Optional<User> optionalUser = userDao.readById(id);
        assertEquals(Optional.empty(), optionalUser);
    }

    @Test
    @Order(5)
    @DisplayName("find user by wrong login")
    void getByWrongLogin() throws DaoException {
        String login = "";
        String password = "testPassword";
        Optional<User> optionalUser = userDao.read(login, password);
        assertEquals(Optional.empty(), optionalUser);
    }

    @Test
    @Order(6)
    @DisplayName("find user by wrong password")
    void getByWrongPassword() throws DaoException {
        String login = "testLogin";
        String password = "";
        Optional<User> optionalUser = userDao.read(login, password);
        assertEquals(Optional.empty(), optionalUser);
    }


    @Test
    @Order(7)
    @DisplayName("get all users")
    void getAllUsers() throws DaoException {
        int capacity = -1;
        List<User> users = new ArrayList<>();
        Optional<List<User>> optionalUsers = userDao.readUsers();
        if (optionalUsers.isPresent()) {
            users = optionalUsers.get();
            capacity = users.size();
        }
        optionalUsers = userDao.readUsers();
        if (optionalUsers.isPresent()) {
            users = optionalUsers.get();
        }
        assertEquals(capacity, users.size());
    }

    @Test
    @Order(8)
    @DisplayName("update user login")
    void updateLogin() throws DaoException {
        String oldLogin = "testLogin";
        String newLogin = "loginTest";
        String password = "testPassword";
        Optional<User> optionalUser = userDao.read(oldLogin, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            int id = user.getId();
            userDao.updateLogin(id, newLogin);
            //assertDoesNotThrow(() -> userDao.updateLogin(id, newLogin));
        } else {
            fail("[ cannot get user by login, password ]");
        }
    }

    @Test
    @Order(9)
    @DisplayName("update user password")
    void updatePassword() throws DaoException {
        String login = "loginTest";
        String oldPassword = "testPassword";
        String newPassword = "passwordTest";
        Optional<User> optionalUser = userDao.read(login, oldPassword);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            assertDoesNotThrow(() -> userDao.updatePassword(user.getId(), newPassword));
        } else {
            fail("[ cannot get user by login, password ]");
        }
    }

    @Test
    @Order(10)
    @DisplayName("get admin user")
    void admin() throws DaoException {
        Role role = Role.ADMIN;
        Optional<User> optionalUser = userDao.readAdmin();
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            assertEquals(role, user.getRole());
        } else {
            fail("[ cannot get admin user ]");
        }

    }

    @Test
    @Order(11)
    @DisplayName("get false admin user")
    void adminFalse() throws DaoException {
        Role role = Role.USER;
        Optional<User> optionalUser = userDao.readAdmin();
        User user = new User();
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        assertNotEquals(role, user.getRole());
    }


    @Test
    @Order(12)
    @DisplayName("delete user by login")
    void deleteByLogin() throws DaoException {
        String login = "loginTest";
        String password = "passwordTest";
        userDao.delete(login);
        assertEquals(Optional.empty(), userDao.read(login, password));
    }

}
