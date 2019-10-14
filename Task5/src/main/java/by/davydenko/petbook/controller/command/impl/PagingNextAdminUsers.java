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
        int pagingNextValue = (int) request.getSession().getAttribute(Attribute.ADMIN_PAGING_NEXT_USERS_KEY);
        String searchUserValue = (String) request.getSession().getAttribute(Attribute.SEARCH_USER_VALUE);
        try {
            Optional<List<User>> optionalUsers;
            if(searchUserValue.isEmpty()){
                optionalUsers=userService.getUsersPagingNext(pagingNextValue);
            }else {
                optionalUsers=userService.getUsersPagingNext(pagingNextValue,searchUserValue);
            }
            if (optionalUsers.isPresent()) {
                List<User> users = optionalUsers.get();
                request.getSession().setAttribute(Attribute.ADMIN_USERS, users);
                int firstUserId = getFirstUserId(users);
                int lastUserId = getLastUserId(users);
                Optional<List<Pet>> optionalPets;
                if(searchUserValue.isEmpty()){
                    optionalPets = petService.getFromTo(firstUserId, lastUserId);
                }else{
                    optionalPets = petService.getFromTo(firstUserId, lastUserId,searchUserValue);
                }
                if (optionalPets.isPresent()) {
                    List<Pet> pets = optionalPets.get();
                    request.getSession().setAttribute(Attribute.ADMIN_PETS, pets);
                }
                request.getSession().setAttribute(Attribute.ADMIN_PAGING_PREV_USERS_KEY, firstUserId);
                if (users.size() == Attribute.ADMIN_PAGING_USERS_INTERVAL) {
                    request.getSession().setAttribute(Attribute.ADMIN_PAGING_NEXT_USERS_KEY, lastUserId);
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

    private void redirectToAdminUsersPage(HttpServletResponse response) {
        try {
            response.sendRedirect(ADMIN_USERS_PAGE);
        } catch (IOException e) {
            logger.error(e);
        }
    }


}
