package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
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
import java.util.List;

public class PhotosPageCommand implements Command {
    private static Logger logger = LogManager.getLogger(PhotosPageCommand.class);
    private static final String PHOTOS_PAGE_URL = "/WEB-INF/jsp/user/photos.jsp";
    private PetService petService;

    public PhotosPageCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int id = (int) request.getSession().getAttribute(Attribute.ID);
        Object objFrom = request.getSession().getAttribute(Attribute.PAGING_VALUE);
        List<String> petPhotoUrl;
        try {
            petPhotoUrl = petService.getPetPhotosById(id);
            int from;
            if (objFrom != null) {
                from = (int) objFrom;
                petPhotoUrl = petService.getPagingPhotoCount(petPhotoUrl, from);
            } else {
                petPhotoUrl = petService.getPagingPhotoCount(petPhotoUrl, 0);
                request.getSession().setAttribute(Attribute.PAGING_VALUE, 0);
            }
            request.getSession().setAttribute(Attribute.PET_PHOTOS, petPhotoUrl);
        } catch (ServiceException e) {
            logger.error(e);
        }
        forwardToMessagePage(request, response);
    }

    private void forwardToMessagePage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(PHOTOS_PAGE_URL);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e);
        }
    }

}
