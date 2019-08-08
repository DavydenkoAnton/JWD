package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.ServiceFactory;
import by.davydenko.petbook.service.UserService;
import by.davydenko.petbook.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {

    private static Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final String REDIRECT_LOGIN_PAGE_URL = "http://localhost:8080/Task5/login.html";
    private static final String REDIRECT_USER_PAGE_URL = "http://localhost:8080/Task5/user.html";
    private static final String REDIRECT_ADMIN_PAGE_URL = "http://localhost:8080/Task5/admin.html";
    private UserService userService;
    private ServiceFactory serviceFactory;

    public LoginCommand() {
        serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        User user;
        HttpSession session = request.getSession();

        try {
            user = userService.getUserByLoginPassword(request);
            session.setAttribute("id", user.getId());
            session.setAttribute("authorized", "true");
            session.setAttribute("role", user.getRole());
            String role = user.getRole();
            if (role != null) {
                if (user.getRole().equals("user")) {
                    redirectToUserPage(response);
                } else if (user.getRole().equals("admin")) {
                    redirectToAdminPage(response);
                }
            } else {
                redirectToLoginPage(response);
            }
        } catch (ServiceException e) {
            logger.error(getClass().getName() + "[ cannot get user ]");
        }

    }

    private void redirectToUserPage(HttpServletResponse response) {
        response.setContentType("user.jsp");
        try {
            response.sendRedirect(REDIRECT_USER_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }

    private void redirectToAdminPage(HttpServletResponse response) {
        response.setContentType("admin.jsp");
        try {
            response.sendRedirect(REDIRECT_ADMIN_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }


    private void redirectToLoginPage(HttpServletResponse response) {
        response.setContentType("login.jsp");
        try {
            response.sendRedirect(REDIRECT_LOGIN_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }
}