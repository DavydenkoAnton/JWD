package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.dao.DaoMySqlException;
import by.davydenko.petbook.entity.User;
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

public class StartPageCommand implements Command {

    private static Logger logger = LogManager.getLogger(AddUserCommand.class);

    private static final Integer ERROR_NUMBER_500 = 500;

    private static final String REDIRECT_PAGE_URL = "http://localhost:8080/Task5/PetBookServlet?command=goToStartPage";

    private static final String TARGET_PAGE = "startPage.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)  {
        List<User> users=null;
        UserServiceImpl userService=new UserServiceImpl();
        try {
            users=userService.getUsers();
        } catch (DaoMySqlException e) {
            logger.error(this.getClass().getName());
        }

        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher(TARGET_PAGE);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
