package by.davydenko.petbook.service.util.validator;

import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserValidator extends Validator<User> {
    @Override
    default boolean valid(User obj) {
        return false;
    }

    boolean valid(HttpServletRequest request);

    boolean validLogin(HttpServletRequest request);

    String createPassword(HttpServletRequest request);

    boolean validId(HttpSession httpSession);

    boolean validPassword(HttpServletRequest request);

    boolean validName(HttpServletRequest request);

    boolean validUserCount(HttpSession session) throws ServiceException;
}
