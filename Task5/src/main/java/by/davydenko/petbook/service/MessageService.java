package by.davydenko.petbook.service;

import by.davydenko.petbook.entity.Message;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface MessageService extends Service<Message> {
    void sendMessage(HttpServletRequest request) throws ServiceException;
    String getMessage(HttpServletRequest request);

    Optional<List<Message>> getChatMessages(HttpServletRequest request) throws ServiceException;
}
