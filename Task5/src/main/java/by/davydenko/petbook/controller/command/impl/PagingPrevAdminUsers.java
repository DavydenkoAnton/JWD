package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.PetService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import by.davydenko.petbook.service.UserService;
import com.sun.istack.internal.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PagingPrevAdminUsers implements Command {
    private static Logger logger = LogManager.getLogger(PagingPrevAdminUsers.class);
    private static final String ADMIN_USERS_PAGE = "http://localhost:8080/pb/users.html";
    private static final String ADMIN_PAGING_FIRST_USERS = "http://localhost:8080/pb/pagingFirstAdminUsers.html";
    private UserService userService;
    private PetService petService;

    public PagingPrevAdminUsers() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int pagingPrevValue = getPagingPrevValue(request);
        String searchUserValue = getSearchValue(request);
        try {
            Optional<List<User>> optionalUsers = userService.getUsersPagingPrev(pagingPrevValue, searchUserValue);
            if (optionalUsers.isPresent()) {
                List<User> users = optionalUsers.get();
                if (users.size() != Attribute.PAGING_ADMIN_USERS_INTERVAL) {
                    redirectToPagingFirstAdminUsers(response);
                    return;
                }
                request.getSession().setAttribute(Attribute.ADMIN_USERS, users);
                int firstUserId = getFirstUserId(users);
                int lastUserId = getLastUserId(users);
                request.getSession().setAttribute(Attribute.PAGING_NEXT, lastUserId);
                request.getSession().setAttribute(Attribute.PAGING_PREV, firstUserId);
                Optional<List<Pet>> optionalPets = petService.getFromTo(firstUserId, lastUserId, searchUserValue);
                if (optionalPets.isPresent()) {
                    List<Pet> pets = optionalPets.get();
                    request.getSession().setAttribute(Attribute.ADMIN_PETS, pets);
                }
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        redirectToAdminUsersPage(response);
    }

    private int getPagingPrevValue(HttpServletRequest request) {
        Object pagingPrevValue = request.getSession().getAttribute(Attribute.PAGING_PREV);
        return pagingPrevValue == null ? 0 : (int) pagingPrevValue;
    }

    private String getSearchValue(HttpServletRequest request) {
        Object searchValue = request.getSession().getAttribute(Attribute.SEARCH_USER_VALUE);
        return searchValue == null ? "" : (String) searchValue;
    }

    private int getFirstUserId(@NotNull List<User> users) {
        return users.size() > 0 ? users.get(0).getId() : 0;
    }

    private int getLastUserId(@NotNull List<User> users) {
        return users.size() > 0 ? users.get(users.size() - 1).getId() : 0;
    }

    private void redirectToAdminUsersPage(HttpServletResponse response) {
        try {
            response.sendRedirect(ADMIN_USERS_PAGE);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    private void redirectToPagingFirstAdminUsers(HttpServletResponse response) {
        try {
            response.sendRedirect(ADMIN_PAGING_FIRST_USERS);
        } catch (IOException e) {
            logger.error(e);
        }
    }

}
