package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Message;
import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.service.MessageService;
import by.davydenko.petbook.service.PetService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GetChatMessages implements Command {

    private static Logger logger = LogManager.getLogger(GetChatMessages.class);
    private static final String REDIRECT_MESSAGES_PAGE_URL = "http://localhost:8080/pb/messages.html";
    private ServiceFactory serviceFactory;
    private MessageService messageService;
    private PetService petService;

    public GetChatMessages() {
        serviceFactory = ServiceFactory.getInstance();
        messageService = serviceFactory.getMessageService();
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Optional<Pet> optionalSender = petService.getSender(request);
            if (optionalSender.isPresent()) {
                Pet sender = optionalSender.get();
                request.getSession().setAttribute(Attribute.MESSAGE_SENDER, sender);
            }
            Optional<Pet> optionalReceiver=petService.getReceiver(request);
            if (optionalReceiver.isPresent()) {
                Pet receiver = optionalReceiver.get();
                request.getSession().setAttribute(Attribute.MESSAGE_RECEIVER, receiver);
            }
            Optional<List<Message>> optionalMessages = messageService.getChatMessages(request);
            if (optionalMessages.isPresent()) {
                List<Message> chatMessages = optionalMessages.get();
                request.getSession().setAttribute(Attribute.CHAT_MESSAGES, chatMessages);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        redirectToMessagesPage(response);
    }

    private void redirectToMessagesPage(HttpServletResponse response) {
        response.setContentType("message.jsp");
        try {
            response.sendRedirect(REDIRECT_MESSAGES_PAGE_URL);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
