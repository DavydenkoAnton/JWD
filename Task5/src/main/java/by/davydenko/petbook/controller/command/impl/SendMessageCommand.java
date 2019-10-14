package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Message;
import by.davydenko.petbook.service.MessageService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import by.davydenko.petbook.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class SendMessageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SendMessageCommand.class);
    private static final String REDIRECT_MESSAGE_PAGE_URL = "http://localhost:8080/pb/messages.html";
    private MessageService messageService;

    public SendMessageCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        messageService = serviceFactory.getMessageService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String senderId = String.valueOf(request.getSession().getAttribute(Attribute.ID));
        String receiverId = request.getParameter(Attribute.USER_ID);
        String text = request.getParameter(Attribute.MESSAGE_TEXT);
        try {
            messageService.sendMessage(receiverId, senderId, text);
            Optional<List<Message>> optionalMessages = messageService.getChatMessages(receiverId, senderId);
            if (optionalMessages.isPresent()) {
                List<Message> chatMessages = optionalMessages.get();
                request.getSession().setAttribute(Attribute.CHAT_MESSAGES, chatMessages);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        redirectToMessagePage(response);
    }

    private void redirectToMessagePage(HttpServletResponse response) {
        try {
            response.sendRedirect(REDIRECT_MESSAGE_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }

}
