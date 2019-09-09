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
    private static final String TARGET_PAGE = "/WEB-INF/jsp/main.jsp";
    private static final String ERROR_PAGE = "/error.jsp";
    private PetService petService;

    public MainPageCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int dogPrefer = 0;
        int catPrefer = 0;
        int birdPrefer = 0;
        int otherPrefer = 0;
        try {
            dogPrefer = petService.getDogPrefer();
            catPrefer = petService.getCatPrefer();
            birdPrefer = petService.getBirdPrefer();
            otherPrefer = petService.getOtherPrefer();
        } catch (ServiceException e) {
            logger.error(e);
        }
        request.getSession().setAttribute(Attribute.DOG_PREFER_VALUE, dogPrefer);
        request.getSession().setAttribute(Attribute.CAT_PREFER_VALUE, catPrefer);
        request.getSession().setAttribute(Attribute.BIRD_PREFER_VALUE, birdPrefer);
        request.getSession().setAttribute(Attribute.OTHER_PREFER_VALUE, otherPrefer);
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(TARGET_PAGE);
            dispatcher.forward(request, response);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        } catch (ServletException e) {
            logger.error("ServletException (not redirected)", e);
        }
    }
}
