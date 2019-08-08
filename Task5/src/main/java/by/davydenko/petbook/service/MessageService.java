package by.davydenko.petbook.service;

import by.davydenko.petbook.entity.Message;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface MessageService extends Service<Message> {
    void sendMessage(int senderID,int recieverId,String message) throws ServiceException;
    String getMessage(HttpServletRequest request);
}
