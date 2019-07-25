package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.dao.DaoMySqlException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPageCommand implements Command {

    private static Logger logger = LogManager.getLogger(LoginPageCommand.class);
    private static final String TARGET_PAGE = "jsp/login.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(TARGET_PAGE);


        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            logger.error("[ ServletException ]",e);
        } catch (IOException e) {
            logger.error("[ IOException ]",e);
        }


    }
}
