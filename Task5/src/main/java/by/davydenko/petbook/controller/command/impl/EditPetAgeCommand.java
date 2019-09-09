package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
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

public class EditPetAgeCommand implements Command {
    private static final Logger logger = LogManager.getLogger(EditPetAgeCommand.class);
    private static final String REDIRECT_PROFILE_PAGE_URL = "http://localhost:8080/pb/profile.html";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PetService petService;

    public EditPetAgeCommand() {
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            petService.uploadAge(request);
            Optional<Pet> optionalPet = petService.getPetByUserId(request);
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
            response.sendRedirect(REDIRECT_PROFILE_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }
}
