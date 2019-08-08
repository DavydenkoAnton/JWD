package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserPageCommand implements Command {

    private static Logger logger = LogManager.getLogger(UserPageCommand.class);
    private static final String USER_PAGE = "/user.jsp";
    private static final String ADMIN_PAGE = "/admin.jsp";
    private static final String ROLE = "role";
    private static final String USER = "user";
    private static final String ADMIN = "admin";
    private RequestDispatcher dispatcher;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);

        if (session.getAttribute(ROLE).equals(USER)) {
            redirectToUserPage(request, response);
        } else if (session.getAttribute(ROLE).equals(ADMIN)) {
            redirectToAdminPage(request, response);
        }
    }

    private void redirectToUserPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            dispatcher = request.getServletContext().getRequestDispatcher(USER_PAGE);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("[ ServletException ]", e);
        } catch (IOException e) {
            logger.error("[ IOException ]", e);
        }
    }

    private void redirectToAdminPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            dispatcher = request.getServletContext().getRequestDispatcher(ADMIN_PAGE);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("[ ServletException ]", e);
        } catch (IOException e) {
            logger.error("[ IOException ]", e);
        }
    }
}