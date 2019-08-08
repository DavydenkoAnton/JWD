package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.dao.DaoFactory;
import by.davydenko.petbook.dao.UserDao;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import by.davydenko.petbook.service.UserService;
import by.davydenko.petbook.service.UserServiceImpl;
import com.sun.deploy.net.HttpRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPageCommand implements Command {

    private static Logger logger = LogManager.getLogger(RegisterUserCommand.class);
    private static final String TARGET_PAGE = "/main.jsp";
    private static final String ERROR_PAGE = "errorPage.jsp";
    private HttpSession httpSession;
    private RequestDispatcher dispatcher;
    private ServiceFactory serviceFactory;
    private UserService userService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        httpSession = request.getSession();
        Object o = httpSession.getAttribute("users");

        if (o == null) {
            serviceFactory = ServiceFactory.getInstance();
            userService = serviceFactory.getUserService();
            try {
                userService.pagingNext( httpSession);
            } catch (ServiceException e) {
                logger.error(e);
            }
        }
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
