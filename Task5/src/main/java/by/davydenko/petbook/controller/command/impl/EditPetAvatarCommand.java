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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

public class EditPetAvatarCommand implements Command {

    private static final Logger logger = LogManager.getLogger(EditPetAvatarCommand.class);
    private static final String REDIRECT_PROFILE_PAGE_URL = "http://localhost:8080/pb/profile.html";
    private PetService petService;

    public EditPetAvatarCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Part part = null;
        try {
            part = request.getPart(Attribute.PET_AVATAR);
        } catch (IOException | ServletException e) {
            logger.error(e);
        }
        String path = request.getServletContext().getRealPath("/") ;
        String userId = String.valueOf(request.getSession().getAttribute(Attribute.ID));
        if (part != null) {
            try {
                petService.uploadAvatar(part, path, userId);
                Optional<Pet> optionalPet = petService.getByUserId(userId);
                if (optionalPet.isPresent()) {
                    Pet pet = optionalPet.get();
                    request.getSession().setAttribute(Attribute.PET, pet);
                }
            } catch (ServiceException e) {
                logger.error(e);
            }
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
