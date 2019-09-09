package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import by.davydenko.petbook.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PaggingUsersPrev implements Command {

    private static Logger logger = LogManager.getLogger(PaggingUsersPrev.class);
    private static final String REDIRECT_PAGE_URL = "http://localhost:8080/pb/main.html";
    private UserService userService;
    private ServiceFactory serviceFactory;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();

        HttpSession httpSession = request.getSession();

        try {
            userService.pagingPrev(httpSession);
        } catch (ServiceException e) {
            logger.error(e);
        }

        response.setContentType("main.jsp");

        try {
            response.sendRedirect(REDIRECT_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }
}
