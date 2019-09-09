package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.entity.Role;
import by.davydenko.petbook.service.PetService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MessagePageCommand implements Command {

    private static Logger logger = LogManager.getLogger(MessagePageCommand.class);
    private static final String ADMIN_MESSAGE_PAGE = "/WEB-INF/jsp/admin/message.jsp";
    private static final String USER_MESSAGE_PAGE = "/WEB-INF/jsp/user/message.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        PetService petService = serviceFactory.getPetService();
        try {
            Optional<List<Pet>> optionalMessageSenders = petService.getMessageSenders(request);
            if (optionalMessageSenders.isPresent()) {
                List<Pet> messageSenders = optionalMessageSenders.get();
                request.getSession().setAttribute(Attribute.MESSAGE_SENDERS, messageSenders);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
if(request.getSession().getAttribute(Attribute.ROLE).equals(Role.ADMIN)){
    redirectToAdminMessagePage(request, response);
}else if(request.getSession().getAttribute(Attribute.ROLE).equals(Role.USER))
        redirectToUserMessagePage(request, response);
    }

    private void redirectToUserMessagePage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(USER_MESSAGE_PAGE);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("[ ServletException ]", e);
        } catch (IOException e) {
            logger.error("[ IOException ]", e);
        }
    }

    private void redirectToAdminMessagePage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ADMIN_MESSAGE_PAGE);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("[ ServletException ]", e);
        } catch (IOException e) {
            logger.error("[ IOException ]", e);
        }
    }
}