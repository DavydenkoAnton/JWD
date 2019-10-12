package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.controller.command.util.Error;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.PetService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import by.davydenko.petbook.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class RegisterUserCommand implements Command {

    private static Logger logger = LogManager.getLogger(RegisterUserCommand.class);
    private static final String LOGIN_PAGE_URL = "http://localhost:8080/pb/login.html";
    private static final String REGISTRATION_PAGE_URL = "http://localhost:8080/pb/registration.html";
    private UserService userService;
    private PetService petService;

    public RegisterUserCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Error error = Error.getInstance();
        error.clean();
        final String newLogin = request.getParameter(Attribute.NEW_LOGIN);
        final String newPassword = request.getParameter(Attribute.NEW_PASSWORD);
        final String newPasswordRepeat = request.getParameter(Attribute.NEW_PASSWORD_REPEAT);
        try {
            userService.registerUser(newLogin,newPassword,newPasswordRepeat);
            Optional<User> optionalUser = userService.getByLoginPassword(newLogin, newPassword);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                request.getSession().setAttribute(Attribute.ROLE, user.getRole());
                request.getSession().setAttribute(Attribute.AUTHORIZED, true);
                request.getSession().setAttribute(Attribute.ID, user.getId());
                petService.registerByUserId(user.getId());
            }
            redirectToLoginPage(response);
        } catch (ServiceException e) {
            logger.error(e);
            request.getSession().setAttribute(Attribute.ERROR, error);
            redirectToRegistrationPage(response);
        }
    }

    private void redirectToRegistrationPage(HttpServletResponse response) {
        response.setContentType("registration.jsp");
        try {
            response.sendRedirect(REGISTRATION_PAGE_URL);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    private void redirectToLoginPage(HttpServletResponse response) {
        response.setContentType("login.jsp");
        try {
            response.sendRedirect(LOGIN_PAGE_URL);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
