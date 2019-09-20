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

public class PagingPetPhotoPrev implements Command {

    private static Logger logger = LogManager.getLogger(PagingPetPhotoPrev.class);
    private static final String REDIRECT_PAGE_URL = "http://localhost:8080/pb/photos.html";
    private static final int MAX_PETS_PHOTO = 4;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int pagingValue = (int) request.getSession().getAttribute(Attribute.PAGING_VALUE);
        int prevValue = pagingValue - MAX_PETS_PHOTO;
        pagingValue = prevValue < 0 ? 0 : prevValue;
        request.getSession().setAttribute(Attribute.PAGING_VALUE, pagingValue);
        redirectToPhotosPage(response);
    }

    private void redirectToPhotosPage(HttpServletResponse response) {
        response.setContentType("photos.jsp");
        try {
            response.sendRedirect(REDIRECT_PAGE_URL);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}




