package by.davydenko.petbook.service.util.creator.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.controller.command.util.Error;
import by.davydenko.petbook.entity.Role;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.util.XSSFilter;
import by.davydenko.petbook.service.util.creator.CreatorException;
import by.davydenko.petbook.service.util.creator.UserCreator;
import com.sun.deploy.net.HttpRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class UserCreatorImpl implements UserCreator {

    private static final Logger logger = LogManager.getLogger(UserCreatorImpl.class);
    private static final int MAX_LOGIN_LENGTH = 16;
    private static final int MAX_PASSWORD_LENGTH = 16;
    private static final int MAX_USER_NAME_LENGTH = 16;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern;
    private Matcher matcher;


    @Override
    public User create() {
        return new User();
    }

    @Override
    public String createLogin(String login) throws CreatorException {
        Error error = Error.getInstance();
        if (login == null) {
            error.setLogin("empty");
            throw new CreatorException("login is null");
        } else if (login.isEmpty()) {
            error.setLogin("empty");
            throw new CreatorException("login is empty");
        } else if (login.length() > MAX_LOGIN_LENGTH) {
            error.setLogin("16 symbol max");
            throw new CreatorException("login length more than 16");
        } else if (XSSFilter.SCRIPT(login)){
            error.setLogin("wrong login");
            throw new CreatorException("trying add script as login");
        }
            return login;
    }

    @Override
    public String createNewLogin(String newLogin) throws CreatorException {
        Error error = Error.getInstance();
        if (newLogin == null) {
            error.setNewLogin("empty");
            throw new CreatorException("new login is null");
        } else if (newLogin.isEmpty()) {
            error.setNewLogin("empty");
            throw new CreatorException("new login is empty");
        } else if (newLogin.length() > MAX_LOGIN_LENGTH) {
            error.setNewLogin("16 symbol max");
            throw new CreatorException("new login length more than 16");
        } else if (XSSFilter.SCRIPT(newLogin)){
            error.setNewLogin("wrong login");
            throw new CreatorException("trying add script as newLogin");
        }
        return newLogin;
    }

    @Override
    public String createNewLoginRepeat(String newLoginRepeat) throws CreatorException {
        Error error = Error.getInstance();
        if (newLoginRepeat == null) {
            error.setNewLoginRepeat("empty");
            throw new CreatorException("newLoginRepeat is null");
        } else if (newLoginRepeat.isEmpty()) {
            error.setNewLoginRepeat("empty");
            throw new CreatorException("newLoginRepeat is empty");
        } else if (newLoginRepeat.length() > MAX_LOGIN_LENGTH) {
            error.setNewLoginRepeat("16 symbol max");
            throw new CreatorException("newLoginRepeat more than 16");
        }else if (XSSFilter.SCRIPT(newLoginRepeat)){
            error.setNewLoginRepeat("wrong login");
            throw new CreatorException("trying add script as newLoginRepeat");
        }
        return newLoginRepeat;
    }



    @Override
    public String createPassword(String password) throws CreatorException {
        Error error = Error.getInstance();
        if (password == null) {
            error.setPassword("password is null");
            throw new CreatorException("password is null");
        } else if (password.isEmpty()) {
            error.setPassword("password is empty");
            throw new CreatorException("password is empty");
        } else if (password.length() > MAX_PASSWORD_LENGTH) {
            error.setPassword("16 symbol max");
            throw new CreatorException("password length more than 16");
        }else if (XSSFilter.SCRIPT(password)){
            error.setPassword("wrong password");
            throw new CreatorException("trying add script as password");
        }
        return hash(password);
    }

    @Override
    public String createNewPassword(String newPassword) throws CreatorException {
        Error error = Error.getInstance();
        if (newPassword == null) {
            error.setNewPassword("new password is null");
            throw new CreatorException("is empty");
        } else if (newPassword.isEmpty()) {
            error.setNewPassword("is empty");
            throw new CreatorException("new password is empty");
        } else if (newPassword.length() > MAX_PASSWORD_LENGTH) {
            error.setNewPassword("16 symbol max");
            throw new CreatorException("password length more than 16");
        }else if (XSSFilter.SCRIPT(newPassword)){
            error.setNewPassword("wrong password");
            throw new CreatorException("trying add script as newPassword");
        }
        return hash(newPassword);
    }

    @Override
    public String createNewPasswordRepeat(String newPasswordRepeat) throws CreatorException {
        Error error = Error.getInstance();
        if (newPasswordRepeat == null) {
            error.setNewPasswordRepeat("is empty");
            throw new CreatorException("new password repeat is null");
        } else if (newPasswordRepeat.isEmpty()) {
            error.setNewPasswordRepeat("is empty");
            throw new CreatorException("new password repeat is empty");
        } else if (newPasswordRepeat.length() > MAX_PASSWORD_LENGTH) {
            error.setNewPasswordRepeat("16 symbol max");
            throw new CreatorException("new password repeat length more than 16");
        }else if (XSSFilter.SCRIPT(newPasswordRepeat)){
            error.setNewLogin("wrong password");
            throw new CreatorException("trying add script as newPasswordRepeat");
        }
        return hash(newPasswordRepeat);
    }

    private String hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            password = DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        }
        return password;
    }


    @Override
    public int createId(String id) throws CreatorException {
        int userId;
        try {
            userId = Integer.valueOf(id);
        } catch (NumberFormatException e) {
            throw new CreatorException("wrong user id format");
        }
        return userId;
    }


    @Override
    public Role createRole() {
        return Role.USER;
    }

    @Override
    public Role createRole(String role) throws CreatorException {
        Role roleTemp;
        if (role != null) {
            if (!role.isEmpty()) {
                try {
                    roleTemp = Role.valueOf(role);
                } catch (IllegalArgumentException e) {
                    throw new CreatorException(e);
                }
            } else {
                throw new CreatorException("role is empty");
            }
        } else {
            throw new CreatorException("role is null");
        }
        return roleTemp;
    }

}
