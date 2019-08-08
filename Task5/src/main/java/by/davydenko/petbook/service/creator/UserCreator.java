package by.davydenko.petbook.service.creator;

import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserCreator extends Creator<User> {

    String createLogin(HttpServletRequest request)throws CreatorException;

    String createPassword(HttpServletRequest request)throws CreatorException;

    User create(HttpServletRequest request)throws CreatorException;

    String createName(HttpServletRequest request)throws CreatorException;

    String createEmail(HttpServletRequest request) throws CreatorException;

    int createPhoneNumber(HttpServletRequest request) throws CreatorException;

    int createAge(HttpServletRequest request) throws CreatorException;

    String createRole();

    int createUserCount(HttpSession session);
}
