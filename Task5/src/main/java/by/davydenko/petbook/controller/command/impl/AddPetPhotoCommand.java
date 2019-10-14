package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.service.PetService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Optional;

public class AddPetPhotoCommand implements by.davydenko.petbook.controller.command.Command {

    private static final Logger logger = LogManager.getLogger(AddPetPhotoCommand.class);
    private static final String PHOTOS_PAGE_URL = "http://localhost:8080/pb/photos.html";
    private PetService petService;

    public AddPetPhotoCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Part part = null;
        try {
            part = request.getPart(Attribute.PET_PHOTO);
        } catch (IOException | ServletException e) {
            logger.error(e);
        }
        String path = request.getServletContext().getRealPath("/") ;
        String userId = String.valueOf(request.getSession().getAttribute(Attribute.ID));
            try {
                petService.uploadPhoto(part, path, userId);
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
        try {
            response.sendRedirect(PHOTOS_PAGE_URL);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
