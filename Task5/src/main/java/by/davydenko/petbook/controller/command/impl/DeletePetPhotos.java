package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.service.PetService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import by.davydenko.petbook.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeletePetPhotos implements by.davydenko.petbook.controller.command.Command {
    private static final Logger logger = LogManager.getLogger(DeletePetPhotos.class);

    private static final String USER_PHOTOS_PAGE = "http://localhost:8080/pb/photos.html";
    private PetService petService;

    public DeletePetPhotos() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        List<String> urls = new ArrayList<>();
        int id = (int) request.getSession().getAttribute(Attribute.ID);
        urls.add(request.getParameter("checkBoxPhotoUrl_1"));
        urls.add(request.getParameter("checkBoxPhotoUrl_2"));
        urls.add(request.getParameter("checkBoxPhotoUrl_3"));
        urls.add(request.getParameter("checkBoxPhotoUrl_4"));
        try {
            petService.deletePhotos(id, urls);
        } catch (ServiceException e) {
            logger.error(e);
        }
        redirectToUserPhotosPage(response);
    }

    private void redirectToUserPhotosPage(HttpServletResponse response) {
        try {
            response.sendRedirect(USER_PHOTOS_PAGE);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }
}
