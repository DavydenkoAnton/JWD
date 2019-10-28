package by.davydenko.petbook.service;

import by.davydenko.petbook.entity.Message;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface MessageService extends Service<Message> {

    /**
     * Create message in database.
     *
     * @param receiverId user id, who receive message.
     * @param senderId   user id, who send message.
     * @param text       text of the message.
     * @throws ServiceException if any Dao, Creator exception has occurred.
     */
    void sendMessage(String receiverId, String senderId, String text) throws ServiceException;

    /**
     * Find and return message from the database.
     *
     * @param receiverId user id who owns the message.
     * @return Optional containing either:
     * <ol>
     * <li>Message contain fields text, date, senderId,receiverId not null.</li>
     * <li><code>null</code>, if message was not found.</li>
     * </ol>
     * @throws ServiceException if any Dao exception has occurred.
     */
    Optional<Message> getMessage(String receiverId) throws ServiceException;

    /**
     * Find and return message from the database.
     *
     * @param receiverId user id who owns the message.
     * @param senderId user id who sent the message.
     * @return Optional containing either:
     * <ol>
     * <li>Messages contains fields text, date, senderId,receiverId not null.</li>
     * <li><code>null</code>, if messages were not found.</li>
     * </ol>
     * @throws ServiceException if any Dao exception has occurred.
     */
    Optional<List<Message>> getChatMessages(String receiverId, String senderId) throws ServiceException;

    /**
     * Find and delete message from the database.
     *
     * @param receiverId user id who owns the message.
     * @throws ServiceException if any Dao exception has occurred.
     */
    void deleteMessage(String receiverId) throws ServiceException;

}
