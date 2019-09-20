package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPageCommand implements Command {

    private static Logger logger = LogManager.getLogger(LoginPageCommand.class);
    private static final String LOGIN_PAGE_URL = "/WEB-INF/jsp/login.jsp";
    private static final String ERROR_PAGE_URL = "/WEB-INF/jsp/error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            forwardToLoginPage(request, response);
        } catch (IOException | ServletException e) {
            logger.error(e);
            forwardToErrorPage(request, response);
        }
    }

    private void forwardToLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(LOGIN_PAGE_URL);
        dispatcher.forward(request, response);
    }

    private void forwardToErrorPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ERROR_PAGE_URL);
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error(e);
        }
    }
}
