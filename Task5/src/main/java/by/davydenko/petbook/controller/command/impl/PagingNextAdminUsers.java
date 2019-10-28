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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PagingNextAdminUsers implements Command {
    private static Logger logger = LogManager.getLogger(PagingNextAdminUsers.class);
    private static final String ADMIN_USERS_PAGE = "http://localhost:8080/pb/users.html";
    private UserService userService;
    private PetService petService;

    public PagingNextAdminUsers() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
        petService = serviceFactory.getPetService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int pagingNextValue = getPagingNextValue(request);
        String searchUserValue = getSearchValue(request);
        try {
            Optional<List<User>> optionalUsers = userService.getUsersPagingNext(pagingNextValue, searchUserValue);
            if (optionalUsers.isPresent()) {
                List<User> users = optionalUsers.get();
                request.getSession().setAttribute(Attribute.ADMIN_USERS, users);
                int firstUserId = getFirstUserId(users);
                int lastUserId = getLastUserId(users);
                Optional<List<Pet>> optionalPets = petService.getFromTo(firstUserId, lastUserId, searchUserValue);
                if (optionalPets.isPresent()) {
                    List<Pet> pets = optionalPets.get();
                    request.getSession().setAttribute(Attribute.ADMIN_PETS, pets);
                }
                request.getSession().setAttribute(Attribute.PAGING_PREV, firstUserId);
                if (users.size() == Attribute.PAGING_ADMIN_USERS_INTERVAL) {
                    request.getSession().setAttribute(Attribute.PAGING_NEXT, lastUserId);
                }
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        redirectToAdminUsersPage(response);
    }

    private int getFirstUserId(List<User> users) {
        return users.size() > 0 ? users.get(0).getId() : 0;
    }

    private int getLastUserId(List<User> users) {
        return users.size() > 0 ? users.get(users.size() - 1).getId() : 0;
    }

    private int getPagingNextValue(HttpServletRequest request) {
        Object pagingNextValue = request.getSession().getAttribute(Attribute.PAGING_NEXT);
        return pagingNextValue == null ? 0 : (int) pagingNextValue;
    }

    private String getSearchValue(HttpServletRequest request){
        Object searchValue=request.getSession().getAttribute(Attribute.SEARCH_USER_VALUE);
        return searchValue==null?"":(String)searchValue;
    }

    private void redirectToAdminUsersPage(HttpServletResponse response) {
        try {
            response.sendRedirect(ADMIN_USERS_PAGE);
        } catch (IOException e) {
            logger.error(e);
        }
    }


}
