package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import by.davydenko.petbook.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ChangeUserRoleCommand implements by.davydenko.petbook.controller.command.Command {
    private static final Logger logger = LogManager.getLogger(ChangeUserRoleCommand.class);
    private static final String USERS_PAGE_URL = "http://localhost:8080/pb/users.html";
    private UserService userService;

    public ChangeUserRoleCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(Attribute.USER_ID);
        String searchUserValue = getSearchValue(request);
        try {
            userService.changeRole(id);
            int from = (int) request.getSession().getAttribute(Attribute.PAGING_PREV);
            int to = (int) request.getSession().getAttribute(Attribute.PAGING_NEXT);
            Optional<List<User>> optionalUsers = userService.getUsersFromTo(from, to,searchUserValue);
            if (optionalUsers.isPresent()) {
                List<User> users = optionalUsers.get();
                request.getSession().setAttribute(Attribute.ADMIN_USERS, users);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        redirectToAdminUsersPage(response);
    }

    private void redirectToAdminUsersPage(HttpServletResponse response) {
        try {
            response.sendRedirect(USERS_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }

    private String getSearchValue(HttpServletRequest request){
        Object searchValue=request.getSession().getAttribute(Attribute.SEARCH_USER_VALUE);
        return searchValue==null?"":(String)searchValue;
    }
}
