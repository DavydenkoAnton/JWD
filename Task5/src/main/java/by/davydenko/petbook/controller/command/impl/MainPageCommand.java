package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.UserServiceException;
import by.davydenko.petbook.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class MainPageCommand implements Command {

    private static Logger logger = LogManager.getLogger(AddUserCommand.class);
    private static final String TARGET_PAGE = "/main.jsp";
    private static final String ERROR_PAGE = "errorPage.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher;
        List<User> users = null;
        UserServiceImpl userService = new UserServiceImpl();
        HttpSession session = request.getSession();
        request.removeAttribute("main");
        try {
            users = userService.getUsers();
        } catch (UserServiceException e) {
            logger.error(e);
            try {
                dispatcher = request.getRequestDispatcher(ERROR_PAGE);
                dispatcher.forward(request, response);
            } catch (ServletException | IOException ex) {
                logger.error(e);
            }
        }


        session.setAttribute("users", users);


        try {
            dispatcher = request.getServletContext().getRequestDispatcher(TARGET_PAGE);
            dispatcher.forward(request, response);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        } catch (ServletException e) {
            logger.error("ServletException (not redirected)", e);
        }


    }
}
