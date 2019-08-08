package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutUserCommand implements Command {

    private static Logger logger = LogManager.getLogger(LogoutUserCommand.class);
    private static final String REDIRECT_MAIN_PAGE_URL = "http://localhost:8080/Task5/main.html";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        String authorized = (String) session.getAttribute("authorized");

        if (authorized != null && authorized.equals("true")) {
            session.setAttribute("authorized","");
            session.setAttribute("role","");
            session.setAttribute("id","");
        }
        redirectToMainPage(request,response);
    }

    private void redirectToMainPage(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("main.jsp");
        try {
            response.sendRedirect(REDIRECT_MAIN_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }
}
