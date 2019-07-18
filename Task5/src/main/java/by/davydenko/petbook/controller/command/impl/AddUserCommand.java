package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.URLCreator;
import by.davydenko.petbook.dao.DaoMySqlException;
import by.davydenko.petbook.dao.util.DBHelper;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AddUserCommand implements Command {

    private static Logger logger = LogManager.getLogger(AddUserCommand.class);
    private static final String REDIRECT_PAGE_URL = "http://localhost:8080/Task5/PetBookServlet?command=goToStartPage";//&success_param=true
    private static final String TARGET_PAGE = "startPage.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        UserServiceImpl userService = new UserServiceImpl();
        User user = new User();
        List<User> users = new ArrayList<>();

        user.setLogin(request.getParameter(DBHelper.Users.LOGIN.getName()));
        user.setPassword(request.getParameter(DBHelper.Users.PASSWORD.getName()));
        user.setName(request.getParameter(DBHelper.Users.NAME.getName()));
        user.setEmail(request.getParameter(DBHelper.Users.EMAIL.getName()));
        String s = request.getParameter(DBHelper.Users.PHONE.getName());
        user.setPhoneNumber(Integer.valueOf(request.getParameter(DBHelper.Users.PHONE.getName())));
        user.setAge(Integer.valueOf(request.getParameter(DBHelper.Users.AGE.getName())));

        userService.addUser(user);
        try {
            users = userService.getUsers();
        } catch (DaoMySqlException e) {
            e.printStackTrace();
        }

        logger.error("AddUserCommand worked");

        HttpSession session = request.getSession(true);
        session.setAttribute("users", users);

        try {
            response.sendRedirect(TARGET_PAGE);
        } catch (IOException e) {
            logger.error("IOException (not redirected)",e);
        }
    }
}
