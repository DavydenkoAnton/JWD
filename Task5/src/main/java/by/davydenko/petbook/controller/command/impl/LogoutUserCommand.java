package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutUserCommand implements Command {

    private static Logger logger = LogManager.getLogger(LogoutUserCommand.class);
    private static final String REDIRECT_MAIN_PAGE_URL = "http://localhost:8080/pb/main.html";
    private static final String REDIRECT_USER_PAGE_URL = "http://localhost:8080/pb/main.html";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        boolean authorized = (boolean) request.getSession().getAttribute(Attribute.AUTHORIZED);

        if (authorized) {
            request.getSession().invalidate();
            redirectToMainPage(response);
        } else {
            redirectToUserPage(response);
        }

    }

    private void redirectToMainPage(HttpServletResponse response) {
        try {
            response.sendRedirect(REDIRECT_MAIN_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }

    private void redirectToUserPage(HttpServletResponse response) {

        try {
            response.sendRedirect(REDIRECT_USER_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }

}

