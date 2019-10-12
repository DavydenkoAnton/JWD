package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.service.PetService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class EditPetTypeCommand implements by.davydenko.petbook.controller.command.Command {
    private static final Logger logger = LogManager.getLogger(EditPetTypeCommand.class);
    private static final String PROFILE_PAGE_URL = "http://localhost:8080/pb/profile.html";
    private PetService petService;

    public EditPetTypeCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String petType = request.getParameter(Attribute.PET_TYPE);
        String userId = String.valueOf(request.getSession().getAttribute(Attribute.ID));
        try {
            petService.uploadType(petType, userId);
            Optional<Pet> optionalPet = petService.getByUserId(userId);
            if (optionalPet.isPresent()) {
                Pet pet = optionalPet.get();
                request.getSession().setAttribute(Attribute.PET, pet);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        redirectToProfilePage(response);
    }

    private void redirectToProfilePage(HttpServletResponse response) {
        response.setContentType("profile.jsp");
        try {
            response.sendRedirect(PROFILE_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }
}