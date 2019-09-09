package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminPageMessagesCommand implements Command {

    private static Logger logger = LogManager.getLogger(AdminPageMessagesCommand.class);
    private static final String MESSAGE_PAGE = "/WEB-INF/jsp/admin/message.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        redirectToMessagePage(request, response);
    }

    private void redirectToMessagePage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(MESSAGE_PAGE);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("[ ServletException ]", e);
        } catch (IOException e) {
            logger.error("[ IOException ]", e);
        }
    }
}
