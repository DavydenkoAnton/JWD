package by.davydenko.petbook.service.util.creator;

import by.davydenko.petbook.entity.Role;
import by.davydenko.petbook.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserCreator extends Creator<User> {

    @Override
    User create();

    String createLogin(String login)throws CreatorException;

    String createPassword(String password)throws CreatorException;

    String createName(String name)throws CreatorException;

    String createEmail(HttpServletRequest request) throws CreatorException;

    int createId(String id) throws CreatorException;

    int createPhoneNumber(HttpServletRequest request) throws CreatorException;

    int createAge(HttpServletRequest request) throws CreatorException;

    Role createRole();

    int createUserCount(HttpSession session);

}
