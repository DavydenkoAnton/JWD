package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.UserServiceException;
import by.davydenko.petbook.service.UserServiceImpl;
import com.mysql.cj.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginUserCommand implements Command {

    private static Logger logger = LogManager.getLogger(LoginUserCommand.class);
    private static final String REDIRECT_PAGE_URL = "http://localhost:8080/Task5/login.html";
    private static final String TARGET_PAGE_URL = "http://localhost:8080/Task5/main.html";
    private UserServiceImpl userService;
    private User user;

    public LoginUserCommand() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        userService = new UserServiceImpl();
        user = new User();

        //TODO UserValidator
        String login = request.getParameter("login");
        String pasword = request.getParameter("password");
        HttpSession session = request.getSession();
        String authorized = (String) session.getAttribute("authorized");


        if (authorized != null) {
            //TODO redirect to login page
        } else {
            try {
                user = userService.getUser(login, pasword);
            } catch (UserServiceException e) {
                logger.error("user not authorized login:" + login + " password: " + pasword);
            }
            session.setAttribute("authorized", "true");
            session.setAttribute("role", user.getRole());
            authorized = (String) session.getAttribute("authorized");
            int c = 9;
            //TODO redirect to main page
        }


    }
}
