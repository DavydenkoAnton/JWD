package by.davydenko.petbook.service;

import by.davydenko.petbook.entity.Message;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface MessageService extends Service<Message> {

    void sendMessage(String receiverId, String senderId, String text) throws ServiceException;

    Optional<Message> getMessage(String receiverId) throws ServiceException;

    Optional<List<Message>> getChatMessages(String receiverId, String senderId) throws ServiceException;

    void deleteMessage(String receiverId) throws ServiceException;

}
