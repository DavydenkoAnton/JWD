package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.entity.PetType;
import by.davydenko.petbook.service.ArticleService;
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
import java.util.Optional;


public class PetsPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(PetsPageCommand.class);
    private static final String PETS_PAGE_URL = "/WEB-INF/jsp/pets.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        forwardToPetsPage(request,response);
    }

    private void forwardToPetsPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(PETS_PAGE_URL);
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error("not redirected", e);
        }
    }
}
