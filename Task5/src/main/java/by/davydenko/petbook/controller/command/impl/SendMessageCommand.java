package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
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

public class SendMessageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SendMessageCommand.class);

    private static final String REDIRECT_MESSAGE_PAGE_URL = "http://localhost:8080/Task5/message.html";
    private MessageService messageService;
    private ServiceFactory serviceFactory;
    private UserService userService;

    public SendMessageCommand() {
        serviceFactory = ServiceFactory.getInstance();
        messageService = serviceFactory.getMessageService();
        userService = serviceFactory.getUserService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        int senderId = 0;
        int recieverId = 0;
        String message;
        HttpSession httpSession = request.getSession();

        try {
            senderId = userService.getId(httpSession);
            recieverId = userService.getIdByLogin(request);
        } catch (ServiceException e) {
            redirectToMessagePage(response);
        }

        try {
            message = messageService.getMessage(request);
            messageService.sendMessage(senderId, recieverId, message);
        } catch (ServiceException e) {
            logger.error(getClass().getName() + "[ Cannot send message ]");
        }
        redirectToMessagePage(response);
    }

    private void redirectToMessagePage(HttpServletResponse response) {
        response.setContentType("message.jsp");
        try {
            response.sendRedirect(REDIRECT_MESSAGE_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }

}
