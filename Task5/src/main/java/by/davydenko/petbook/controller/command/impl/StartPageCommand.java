package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.dao.DaoMySqlException;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class StartPageCommand implements Command {

    private static final String PARAMETER_PREVIOUS_REQUEST = "prev_request";
    private static final Integer ERROR_NUMBER_500 = 500;

    private static final String REDIRECT_PAGE_URL = "http://localhost:8080/petBook/Servlet?command=goToStartPage";

    public static final int rowsByPage = 5;

    public static final int firstPageNumber = 1;

    public static final int defaultNumberOfPages = 1;

    private static final String TARGET_PAGE = "/WEB-INF/jsp/startPage.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws DaoMySqlException {
        List<User> users;
        UserServiceImpl userService=new UserServiceImpl();
        users=userService.getUsers();

        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher(TARGET_PAGE);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
