package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
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
import java.util.ArrayList;
import java.util.List;

public class PagingPetPhotoNext implements Command {
    private static Logger logger = LogManager.getLogger(PagingPetPhotoNext.class);
    private static final String REDIRECT_PAGE_URL = "http://localhost:8080/pb/photos.html";
    private static final int MAX_PETS_PHOTO = 4;
    private PetService petService;

    public PagingPetPhotoNext() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int id = (int) request.getSession().getAttribute(Attribute.ID);
        int pagingValue = (int) request.getSession().getAttribute(Attribute.PAGING_VALUE);
        List<String> petFotoUrl = new ArrayList<>();
        try {
            petFotoUrl = petService.getPetPhotosById(id);
            int nextValue = pagingValue + MAX_PETS_PHOTO;
            pagingValue = nextValue > petFotoUrl.size() ? pagingValue : nextValue;
            request.getSession().setAttribute(Attribute.PAGING_VALUE, pagingValue);
        } catch (NumberFormatException | ServiceException e) {
            logger.error(e);
        }
        redirectToPhotosPage(response);
    }

    private void redirectToPhotosPage(HttpServletResponse response) {
        try {
            response.sendRedirect(REDIRECT_PAGE_URL);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
