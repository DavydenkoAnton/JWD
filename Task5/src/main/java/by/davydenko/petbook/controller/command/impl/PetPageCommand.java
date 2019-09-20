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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class PetPageCommand implements Command {

    private static Logger logger = LogManager.getLogger(UserPageCommand.class);
    private static final String PET_PAGE = "/WEB-INF/jsp/user/pet.jsp";
    private RequestDispatcher dispatcher;
    private ServiceFactory serviceFactory;
    private UserService userService;
    private PetService petService;

    public PetPageCommand() {
        serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter(Attribute.USER_ID);
        try {
            Optional<User> optionalUser = userService.getById(userId);
            Optional<Pet> optionalPet = petService.getByUserId(userId);
            if (optionalPet.isPresent() && optionalUser.isPresent()) {
                Pet pet = optionalPet.get();
                User user = optionalUser.get();
                request.getSession().setAttribute(Attribute.PET, pet);
                request.getSession().setAttribute(Attribute.USER, user);
                forwardToPetPage(request, response);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }

    }

    private void forwardToPetPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            dispatcher = request.getServletContext().getRequestDispatcher(PET_PAGE);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("[ ServletException ]", e);
        } catch (IOException e) {
            logger.error("[ IOException ]", e);
        }
    }
}