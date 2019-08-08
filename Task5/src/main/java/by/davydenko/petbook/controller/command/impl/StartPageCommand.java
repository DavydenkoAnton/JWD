package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.ServiceException;
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

    private static Logger logger = LogManager.getLogger(RegisterUserCommand.class);
    private static final String TARGET_PAGE = "jsp/main.jsp";
    private static final String ERROR_PAGE = "jsp/errorPage.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)  {
        RequestDispatcher dispatcher;
        List<User> bufUsers=null;
        List<User> users=null;
        UserServiceImpl userService=new UserServiceImpl();
        HttpSession httpSession=request.getSession();
        try {
            bufUsers=userService.getUsers(httpSession);
            for (int i = 0; i < 10; i++) {
                users.add(bufUsers.get(i));
                httpSession.setAttribute("pagingNext",users.size());
            }
        } catch (ServiceException e) {
            logger.error(e);
            try {
                dispatcher=request.getRequestDispatcher(ERROR_PAGE);
                dispatcher.forward(request,response);
            } catch (ServletException | IOException ex) {
                logger.error(e);
            }
        }

        request.setAttribute("users", users);
        dispatcher = request.getRequestDispatcher(TARGET_PAGE);

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
