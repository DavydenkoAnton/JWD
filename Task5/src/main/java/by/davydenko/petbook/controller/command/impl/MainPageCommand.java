package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
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

public final class MainPageCommand implements Command {

    private static Logger logger = LogManager.getLogger(MainPageCommand.class);
    private static final String MAIN_PAGE_URL = "/WEB-INF/jsp/main.jsp";
    private static final String ERROR_PAGE_URL = "http://localhost:8080/pb/error.html";
    private PetService petService;

    public MainPageCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int dogPercent=petService.getDogPercent();
            int catPercent=petService.getCatPercent();
            int birdPercent=petService.getBirdPercent();
            int otherPercent=petService.getOtherPercent();
            request.getSession().setAttribute(Attribute.DOG_PERCENT_VALUE, dogPercent);
            request.getSession().setAttribute(Attribute.CAT_PERCENT_VALUE, catPercent);
            request.getSession().setAttribute(Attribute.BIRD_PERCENT_VALUE, birdPercent);
            request.getSession().setAttribute(Attribute.OTHER_PERCENT_VALUE, otherPercent);
        } catch (ServiceException e) {
            logger.error(e);
            redirectToErrorPage(response);
            return;
        }
        forwardToMainPage(request,response);
    }

    private void forwardToMainPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(MAIN_PAGE_URL);
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error( e);
        }
    }

    private void redirectToErrorPage(HttpServletResponse response) {
        try {
            response.sendRedirect(ERROR_PAGE_URL);
        } catch (IOException e) {
            logger.error( e);
        }
    }
}
