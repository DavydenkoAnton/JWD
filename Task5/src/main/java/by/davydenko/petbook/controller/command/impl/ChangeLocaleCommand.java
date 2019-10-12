package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {

    private static Logger logger = LogManager.getLogger(ChangeLocaleCommand.class);
    private static final String START_URL = "http://localhost:8080/pb/";
    private final static int YEAR = 40;
    private final static int MONTH = 12;
    private final static int DAY = 30;
    private final static int HOUR = 24;
    private final static int MINUTE = 60;
    private final static int SECOND = 60;
    private final static int PERIOD = YEAR * MONTH * DAY * HOUR * MINUTE * SECOND;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String language = request.getParameter(Attribute.LANGUAGE);
        String pageURL = request.getParameter(Attribute.PAGE_URL);
        Cookie langCookie = new Cookie(Attribute.LANGUAGE, language);
        langCookie.setComment("Language value");
        langCookie.setHttpOnly(true);
        langCookie.setMaxAge(PERIOD);
        //uncomment this if use HTTPS or SSL protocol
        //language.setSecure(true);
        response.addCookie(langCookie);
        redirectToCurrentPage(response,pageURL);
    }

    private void redirectToCurrentPage(HttpServletResponse response, String pageURL) {
        try {
            response.sendRedirect(START_URL+pageURL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }
}
