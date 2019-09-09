package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {

    private static Logger logger = LogManager.getLogger(ChangeLocaleCommand.class);
    private static final String REDIRECT_PAGE_URL = "http://localhost:8080/pb/main.html";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        String lang = request.getParameter("localeValue");

        if (lang != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("language", lang);
        }


        response.setContentType("main.jsp");
        HttpSession session = request.getSession(true);
        try {
            response.sendRedirect(REDIRECT_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }

    }

}
