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

public class EditAdminLogin implements by.davydenko.petbook.controller.command.Command {
    private static Logger logger = LogManager.getLogger(EditAdminLogin.class);
    private static final String ADMIN_SETTINGS_PAGE_URL = "http://localhost:8080/pb/settings.html";
    private UserService userService;

    public EditAdminLogin() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Error error = Error.getInstance();
        error.clean();
        String login=request.getParameter(Attribute.LOGIN);
        String newLogin=request.getParameter(Attribute.NEW_LOGIN);
        String newLoginRepeat=request.getParameter(Attribute.NEW_LOGIN_REPEAT);
        int id=(int)request.getSession().getAttribute(Attribute.ID);
        try {
            userService.updateLogin(login,newLogin,newLoginRepeat,id);
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
