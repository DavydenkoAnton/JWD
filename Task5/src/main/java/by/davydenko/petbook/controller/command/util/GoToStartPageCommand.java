package by.davydenko.petbook.controller.command.util;

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
import java.util.ArrayList;
import java.util.List;

public class GoToStartPageCommand implements Command {

    private static final String TARGET_PAGE = "/WEB-INF/jsp/startPage.jsp";
    private static final String PARAMETER_PREVIOUS_REQUEST = "prev_request";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws DaoMySqlException {
        List<User> users=new ArrayList<>();
        UserServiceImpl userService=new UserServiceImpl();
        users=userService.getUsers();
        HttpSession session;

        String url = URLCreator.create(request);

        session = request.getSession(true);

        session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher(TARGET_PAGE);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}