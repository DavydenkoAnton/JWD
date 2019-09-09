package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.PetService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import by.davydenko.petbook.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;


public class RegisterUserCommand implements Command {

    private static Logger logger = LogManager.getLogger(RegisterUserCommand.class);
    private static final String REDIRECT_LOGIN_PAGE_URL = "http://localhost:8080/pb/login.html";
    private static final String REDIRECT_REGISTRATION_PAGE_URL = "http://localhost:8080/pb/registration.html";
    private ServiceFactory serviceFactory;
    private UserService userService;
    private PetService petService;


    public RegisterUserCommand() {
        serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            userService.registerUser(request);
            Optional<User> optionalUser = userService.getUserByLoginPassword(request);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                request.getSession().setAttribute(Attribute.ROLE, user.getRole());
                request.getSession().setAttribute(Attribute.AUTHORIZED, true);
                request.getSession().setAttribute(Attribute.ID, user.getId());
            }
            petService.registerPet(request);
            redirectToLoginPage(response);
        } catch (ServiceException e) {
            redirectToRegistrationPage(response);
            logger.error(e);
        }
    }


    private void redirectToRegistrationPage(HttpServletResponse response) {
        response.setContentType("registration.jsp");
        try {
            response.sendRedirect(REDIRECT_REGISTRATION_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }

    private void redirectToLoginPage(HttpServletResponse response) {
        response.setContentType("login.jsp");
        try {
            response.sendRedirect(REDIRECT_LOGIN_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }


}
