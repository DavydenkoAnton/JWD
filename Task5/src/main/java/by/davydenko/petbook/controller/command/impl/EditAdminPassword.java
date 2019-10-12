package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.controller.command.util.Error;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import by.davydenko.petbook.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditAdminPassword implements by.davydenko.petbook.controller.command.Command {
    private static Logger logger = LogManager.getLogger(EditAdminPassword.class);
    private static final String ADMIN_SETTINGS_PAGE_URL = "http://localhost:8080/pb/settings.html";
    private UserService userService;

    public EditAdminPassword() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Error error = Error.getInstance();
        error.clean();
        String password=request.getParameter(Attribute.PASSWORD);
        String newPassword=request.getParameter(Attribute.NEW_PASSWORD);
        String newPasswordRepeat=request.getParameter(Attribute.NEW_PASSWORD_REPEAT);
        int id=(int)request.getSession().getAttribute(Attribute.ID);
        try {
            userService.updatePassword(password,newPassword,newPasswordRepeat,id);
        } catch (ServiceException e) {
            request.getSession().setAttribute(Attribute.ERROR,error);
            logger.error(e);
        }
        redirectToAdminSettingsPage(response);
    }

    private void redirectToAdminSettingsPage(HttpServletResponse response) {
        try {
            response.sendRedirect(ADMIN_SETTINGS_PAGE_URL);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
