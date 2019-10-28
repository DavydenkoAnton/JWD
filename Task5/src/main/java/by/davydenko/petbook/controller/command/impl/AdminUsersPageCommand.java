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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AdminUsersPageCommand implements Command {
    private static Logger logger = LogManager.getLogger(AdminUsersPageCommand.class);
    private static final String USER_PROFILE_PAGE = "/WEB-INF/jsp/admin/users.jsp";
    private static final int PAGING_START = 0;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        forwardToAdminUsersPage(request, response);
    }

    private void forwardToAdminUsersPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(USER_PROFILE_PAGE);
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error(e);
        }
    }
}
