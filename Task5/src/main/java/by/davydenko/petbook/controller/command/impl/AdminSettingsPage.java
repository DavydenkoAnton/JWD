package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.User;
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
import java.util.Optional;

public class AdminSettingsPage implements Command {
    private static Logger logger = LogManager.getLogger(AdminSettingsPage.class);
    private static final String ADMIN_PROFILE_PAGE = "/WEB-INF/jsp/admin/admin_settings.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        forwardToAdminSettingsPage(request, response);
    }

    private void forwardToAdminSettingsPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ADMIN_PROFILE_PAGE);
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error(e);
        }
    }
}
