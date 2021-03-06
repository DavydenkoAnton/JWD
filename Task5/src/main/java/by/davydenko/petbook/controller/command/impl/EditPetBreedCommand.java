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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;


public class EditPetBreedCommand implements Command {

    private static final Logger logger = LogManager.getLogger(EditPetBreedCommand.class);
    private static final String REDIRECT_PROFILE_PAGE_URL = "http://localhost:8080/pb/profile.html";
    private PetService petService;

    public EditPetBreedCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String breed = request.getParameter(Attribute.BREED);
        String userId = String.valueOf(request.getSession().getAttribute(Attribute.ID));
        try {
            petService.uploadBreed(breed,userId);
            Optional<Pet> optionalPet = petService.getByUserId(userId);
            if(optionalPet.isPresent()) {
                Pet pet = optionalPet.get();
                request.getSession().setAttribute(Attribute.PET, pet);
            }
            } catch (ServiceException e) {
            logger.error(e);
        }
        redirectToProfilePage(response);
    }

    private void redirectToProfilePage(HttpServletResponse response) {
        try {
            response.sendRedirect(REDIRECT_PROFILE_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }

}

