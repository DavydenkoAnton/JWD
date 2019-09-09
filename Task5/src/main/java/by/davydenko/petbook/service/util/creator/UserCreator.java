package by.davydenko.petbook.service.util.creator;

import by.davydenko.petbook.entity.Role;
import by.davydenko.petbook.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserCreator extends Creator<User> {

    @Override
    default User create() {
        return null;
    }

    String createLogin(HttpServletRequest request)throws CreatorException;

    String createPassword(HttpServletRequest request)throws CreatorException;

    User create(HttpServletRequest request)throws CreatorException;

    String createName(HttpServletRequest request)throws CreatorException;

    String createEmail(HttpServletRequest request) throws CreatorException;

    int createId(HttpServletRequest request) throws CreatorException;

    int createPhoneNumber(HttpServletRequest request) throws CreatorException;

    int createAge(HttpServletRequest request) throws CreatorException;

    Role createRole();

    int createUserCount(HttpSession session);

    int createId(HttpSession session) throws CreatorException;

}
