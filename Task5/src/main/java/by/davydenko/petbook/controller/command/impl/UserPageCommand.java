package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.entity.Role;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.PetService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import by.davydenko.petbook.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserPageCommand implements Command {

    private static Logger logger = LogManager.getLogger(UserPageCommand.class);
    private static final String USER_PAGE = "/WEB-INF/jsp/user/user.jsp";
    private static final String ADMIN_PAGE = "/WEB-INF/jsp/admin/admin.jsp";
    private static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
    private UserService userService;
    private PetService petService;

    public UserPageCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Optional<Pet> optionalPet = petService.getPetByUserId(request);
            if (optionalPet.isPresent()) {
                Pet pet = optionalPet.get();
                request.getSession().setAttribute(Attribute.PET, pet);
            }
            Optional<User> optionalUser = userService.getUserById(request);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                request.getSession().setAttribute(Attribute.USER, user);
                if (user.getRole().equals(Role.USER)) {
                    forwardToUserPage(request, response);
                } else if (user.getRole().equals(Role.ADMIN)) {
                    forwardToAdminPage(request, response);
                }
            } else {
                forwardToLoginPage(request, response);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
    }


    private void forwardToUserPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(USER_PAGE);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("[ ServletException ]", e);
        } catch (IOException e) {
            logger.error("[ IOException ]", e);
        }
    }

    private void forwardToAdminPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ADMIN_PAGE);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("[ ServletException ]", e);
        } catch (IOException e) {
            logger.error("[ IOException ]", e);
        }
    }

    private void forwardToLoginPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(LOGIN_PAGE);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("[ ServletException ]", e);
        } catch (IOException e) {
            logger.error("[ IOException ]", e);
        }
    }
}