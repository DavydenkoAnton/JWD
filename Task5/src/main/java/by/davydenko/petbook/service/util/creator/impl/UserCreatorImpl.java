package by.davydenko.petbook.service.util.creator.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.controller.command.util.Error;
import by.davydenko.petbook.entity.Role;
import by.davydenko.petbook.entity.User;
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
            throw new CreatorException("login is null");
        } else if (login.isEmpty()) {
            error.setLogin("login is empty");
            throw new CreatorException("login is empty");
        } else if (login.length() > MAX_LOGIN_LENGTH) {
            error.setLogin("16 symbol max");
            throw new CreatorException("login length more than 16");
        }
        return login;
    }

    @Override
    public String createName(String userName) throws CreatorException {
        if (userName == null) {
            throw new CreatorException("userName is null");
        } else if (userName.isEmpty()) {
            throw new CreatorException("userName is empty");
        } else if (userName.length() > MAX_USER_NAME_LENGTH) {
            throw new CreatorException("login length more than 16");
        }
        return userName;
    }

    @Override
    public String createPassword(String password) throws CreatorException {
        Error error = Error.getInstance();
        if (password == null) {
            error.setLogin("password is null");
            throw new CreatorException("password is null");
        } else if (password.isEmpty()) {
            error.setLogin("password is empty");
            throw new CreatorException("password is empty");
        } else if (password.length() > MAX_PASSWORD_LENGTH) {
            error.setPassword("16 symbol max");
            throw new CreatorException("password length more than 16");
        }
        return hash(password);
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
