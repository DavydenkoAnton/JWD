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
    private static final String TARGET_PAGE = "/WEB-INF/jsp/login.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

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
