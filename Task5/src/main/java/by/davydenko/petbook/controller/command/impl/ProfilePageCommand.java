package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.entity.Role;
import by.davydenko.petbook.service.PetService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public final class ProfilePageCommand implements Command {
    private static Logger logger = LogManager.getLogger(ProfilePageCommand.class);
    private static final String USER_PROFILE_PAGE = "/WEB-INF/jsp/user/profile.jsp";
    private static final String ADMIN_PROFILE_PAGE = "/WEB-INF/jsp/admin/profile.jsp";
    private static final String ERROR_PAGE_URL = "http://localhost:8080/pb/error.html";
    private PetService petService;

    public ProfilePageCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute(Attribute.ROLE).equals(Role.USER)) {
            String userId = String.valueOf(request.getSession().getAttribute(Attribute.ID));
            try {
                Optional<Pet> optionalPet = petService.getByUserId(userId);
                if (optionalPet.isPresent()) {
                    Pet pet = optionalPet.get();
                    request.getSession().setAttribute(Attribute.PET, pet);
                }
                forwardToUserProfilePage(request, response);
            } catch (ServiceException e) {
                logger.error(e);
                redirectToErrorPage(response);
            }
        } else if (request.getSession().getAttribute(Attribute.ROLE).equals(Role.ADMIN)) {
            forwardToAdminProfilePage(request, response);
        } else {
            redirectToErrorPage(response);
        }
    }

    private void forwardToUserProfilePage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(USER_PROFILE_PAGE);
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error(e);
        }
    }

    private void forwardToAdminProfilePage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ADMIN_PROFILE_PAGE);
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error(e);
        }
    }

    private void redirectToErrorPage(HttpServletResponse response) {
        try {
            response.sendRedirect(ERROR_PAGE_URL);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}