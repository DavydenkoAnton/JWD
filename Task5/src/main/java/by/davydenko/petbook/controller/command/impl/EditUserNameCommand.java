package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import by.davydenko.petbook.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Optional;

public class EditUserNameCommand implements Command {

    private static final Logger logger = LogManager.getLogger(EditUserNameCommand.class);
    private static final String REDIRECT_PROFILE_PAGE_URL = "http://localhost:8080/pb/profile.html";
    private static final String USERS_AVATARS_PATH = "img/users_avatars";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserService userService;

    public EditUserNameCommand() {
        userService = serviceFactory.getUserService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(Attribute.NAME);
        String userId = request.getParameter(Attribute.USER_ID);
        try {
            userService.uploadName(name, userId);
            Optional<User> optionalUser = userService.getById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                request.getSession().setAttribute(Attribute.USER, user);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        redirectToProfilePage(response);
    }

    private void redirectToProfilePage(HttpServletResponse response) {
        response.setContentType("profile.jsp");
        try {
            response.sendRedirect(REDIRECT_PROFILE_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }

}
