package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import by.davydenko.petbook.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserCommand implements Command {

    private static Logger logger = LogManager.getLogger(LoginUserCommand.class);
    private static final String REDIRECT_ADMIN_PAGE_URL = "http://localhost:8080/pb/admin.html";
    private UserService userService;
    private ServiceFactory serviceFactory;

    public DeleteUserCommand() {
        serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            userService.deleteUser(request);
        } catch (ServiceException e) {
            logger.error(e);
        }

        response.setContentType("admin.jsp");
        try {
            response.sendRedirect(REDIRECT_ADMIN_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }
}
