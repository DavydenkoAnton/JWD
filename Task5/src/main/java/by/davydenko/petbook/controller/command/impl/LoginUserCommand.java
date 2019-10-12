package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.controller.command.util.Error;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import by.davydenko.petbook.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static by.davydenko.petbook.entity.Role.ADMIN;
import static by.davydenko.petbook.entity.Role.USER;

public class LoginUserCommand implements Command {

    private static Logger logger = LogManager.getLogger(LoginUserCommand.class);
    private static final String LOGIN_PAGE_URL = "http://localhost:8080/pb/login.html";
    private static final String USER_PAGE_URL = "http://localhost:8080/pb/user.html";
    private static final String ADMIN_PAGE_URL = "http://localhost:8080/pb/admin.html";
    private static final String ERROR_PAGE_URL = "http://localhost:8080/pb/error.html";
    private UserService userService;

    public LoginUserCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Error error = Error.getInstance();
        error.clean();
        String login = request.getParameter(Attribute.LOGIN);
        String password = request.getParameter(Attribute.PASSWORD);
        try {
            Optional<User> optionalUser = userService.getByLoginPassword(login, password);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                request.getSession().setAttribute(Attribute.ID, user.getId());
                request.getSession().setAttribute(Attribute.AUTHORIZED, true);
                request.getSession().setAttribute(Attribute.ROLE, user.getRole());
                request.getSession().setAttribute(Attribute.USER, user);
                if (user.getRole().equals(USER)) {
                    redirectToUserPage(response);
                } else if (user.getRole().equals(ADMIN)) {
                    redirectToAdminPage(response);
                }
            } else {
                request.getSession().setAttribute(Attribute.ERROR,error);
                redirectToLoginPage(response);
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.getSession().setAttribute(Attribute.ERROR, error);
            redirectToLoginPage(response);
        }
    }

    private void redirectToUserPage(HttpServletResponse response) {
        response.setContentType("user.jsp");
        try {
            response.sendRedirect(USER_PAGE_URL);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    private void redirectToAdminPage(HttpServletResponse response) {
        response.setContentType("admin.jsp");
        try {
            response.sendRedirect(ADMIN_PAGE_URL);
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

    private void redirectToErrorPage(HttpServletResponse response) {
        response.setContentType("error.jsp");
        try {
            response.sendRedirect(ERROR_PAGE_URL);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}