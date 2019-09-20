package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.service.MessageService;
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
import java.util.Optional;

public class VisitPageCommand implements by.davydenko.petbook.controller.command.Command {
    private static Logger logger = LogManager.getLogger(VisitPageCommand.class);
    private static final String VISIT_PAGE_URL = "/WEB-INF/jsp/visit.jsp";
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
    private PetService petService;

    public VisitPageCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter(Attribute.USER_ID);
        try {
            Optional<Pet> optionalPet = petService.getByUserId(userId);
            //boolean friend=messageService.isFriend(request);
            boolean friend=true;
            if (optionalPet.isPresent()) {
                Pet pet = optionalPet.get();
                request.getSession().setAttribute(Attribute.PET, pet);
                request.getSession().setAttribute(Attribute.FRIEND, friend);
            }
        } catch (ServiceException e) {
            logger.error(e);
            forwardToErrorPage(request, response);
        }
        forwardToVisitPage(request, response);
    }

    private void forwardToVisitPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(VISIT_PAGE_URL);
            dispatcher.forward(request, response);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        } catch (ServletException e) {
            logger.error("ServletException (not redirected)", e);
        }
    }

    private void forwardToErrorPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ERROR_PAGE);
            dispatcher.forward(request, response);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        } catch (ServletException e) {
            logger.error("ServletException (not redirected)", e);
        }
    }
}
