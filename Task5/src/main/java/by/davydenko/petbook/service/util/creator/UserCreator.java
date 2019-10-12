package by.davydenko.petbook.service.util.creator;

import by.davydenko.petbook.entity.Role;
import by.davydenko.petbook.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserCreator extends Creator<User> {

    @Override
    User create();

    String createLogin(String login) throws CreatorException;

    String createNewLogin(String newLogin) throws CreatorException;

    String createNewLoginRepeat(String newLoginRepeat) throws CreatorException;

    String createPassword(String password) throws CreatorException;

    String createNewPassword(String newPassword) throws CreatorException;

    String createNewPasswordRepeat(String newPasswordRepeat) throws CreatorException;

    int createId(String id) throws CreatorException;

    Role createRole();

    Role createRole(String role) throws CreatorException;


}
