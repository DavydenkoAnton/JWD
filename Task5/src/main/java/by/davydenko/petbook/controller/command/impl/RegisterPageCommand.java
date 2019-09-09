package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterPageCommand implements Command {

    private static Logger logger = LogManager.getLogger(RegisterPageCommand.class);
    private static final String REGISTRATION_PAGE_URL = "http://localhost:8080/pb/registration.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //response.setContentType("registration.jsp");
        try {
            response.sendRedirect(REGISTRATION_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }
}
