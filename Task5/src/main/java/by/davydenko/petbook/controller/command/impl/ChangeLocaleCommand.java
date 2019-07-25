package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.dao.DaoMySqlException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {

    private static Logger logger = LogManager.getLogger(ChangeLocaleCommand.class);
    private static final String REDIRECT_PAGE_URL = "http://localhost:8080/Task5/PetBook?command=main";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)  {

        String lang = request.getParameter("localeValue");

        if (lang != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("language", lang);

            RequestDispatcher dispatcher;
            try {
                dispatcher = request.getServletContext().getRequestDispatcher("/main.jsp");
                dispatcher.forward(request, response);
            } catch (IOException e) {
                logger.error("IOException (not redirected)", e);
            } catch (ServletException e) {
                logger.error("ServletException (not redirected)", e);
            }
        }
    }

}
