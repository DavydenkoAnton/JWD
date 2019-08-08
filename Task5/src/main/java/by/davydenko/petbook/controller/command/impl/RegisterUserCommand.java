package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import by.davydenko.petbook.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class RegisterUserCommand implements Command {

    private static Logger logger = LogManager.getLogger(RegisterUserCommand.class);
    private static final String REDIRECT_MAIN_PAGE_URL = "http://localhost:8080/Task5/main.html";
    private static final String REDIRECT_REGISTRATION_PAGE_URL = "http://localhost:8080/Task5/registration.html";
    private ServiceFactory serviceFactory;
    private UserService userService;


    public RegisterUserCommand() {
        serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        User user;
        HttpSession httpSession = request.getSession();
        try {
            userService.addUser(request);
            user = userService.getUserByLoginPassword(request);
            httpSession.setAttribute("role", "user");
            httpSession.setAttribute("authorized", "true");
            httpSession.setAttribute("id", user.getId());
        } catch (ServiceException e) {
            logger.error(getClass().getName() + "[ cannot register user ]");
        }
        redirectToRegistrationPage(response);
    }


    private void redirectToRegistrationPage(HttpServletResponse response) {
        response.setContentType("registration.jsp");
        try {
            response.sendRedirect(REDIRECT_REGISTRATION_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }

    private void redirectToMainPage(HttpServletResponse response) {
        response.setContentType("main.jsp");
        try {
            response.sendRedirect(REDIRECT_MAIN_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }


}
