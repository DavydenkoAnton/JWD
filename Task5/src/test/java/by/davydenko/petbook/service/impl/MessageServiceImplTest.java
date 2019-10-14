package by.davydenko.petbook.service.impl;

import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.DaoFactory;
import by.davydenko.petbook.dao.MessageDao;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.Message;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MessageServiceImplTest {

    private static MessageDao messageDao;

    @BeforeAll
    public static void init() throws ConnectionPoolException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.init();
        DaoFactory daoFactory = DaoFactory.getInstance();
        messageDao = daoFactory.getMessageDao();
    }

    @Test
    @Order(1)
    @DisplayName("add message to db")
    public void sendMessage() {
        Message message = new Message();
        message.setUserId(47);
        message.setSenderId(74);
        message.setMessage("testMessage");
        message.setDate("2000-01-01 00:00:00");
        assertDoesNotThrow(() -> messageDao.create(message));
    }

    @Test
    @Order(2)
    @DisplayName("get message by user id")
    public void getMessage() throws DaoException {
        int userId = 47;
        Optional<Message> message = messageDao.read(userId);
        assertNotEquals(Optional.empty(), message);
    }

    @Test
    @Order(3)
    @DisplayName("delete message by user id")
    public void deleteMessage() throws DaoException {
        int userId = 47;
        messageDao.delete(userId);
        Optional<Message> message = messageDao.read(userId);
        assertEquals(Optional.empty(), message);
    }

}
