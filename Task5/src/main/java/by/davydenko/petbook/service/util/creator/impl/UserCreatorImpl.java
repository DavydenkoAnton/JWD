package by.davydenko.petbook.service.util.creator.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
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
        if (login == null) {
            throw new CreatorException("login is null");
        } else if (login.isEmpty()) {
            throw new CreatorException("login is empty");
        } else if (login.length() > MAX_LOGIN_LENGTH) {
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
        if (password == null) {
            throw new CreatorException("password is null");
        } else if (password.isEmpty()) {
            throw new CreatorException("password is empty");
        } else if (password.length() > MAX_PASSWORD_LENGTH) {
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
    public String createEmail(HttpServletRequest request) throws CreatorException {

        String email = request.getParameter("email");
        if (email != null && !email.isEmpty()) {
            pattern = Pattern.compile(EMAIL_PATTERN);
            if (!validEmail(email)) {
                throw new CreatorException("[wrong email format]");
            }
        }
        return email;
    }

    private boolean validEmail(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
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
    public int createPhoneNumber(HttpServletRequest request) throws CreatorException {
        int phoneNumber = 0;
        String buffer;
        buffer = request.getParameter("phoneNumber");
        if (!buffer.isEmpty()) {
            try {
                phoneNumber = Integer.valueOf(buffer);
            } catch (NumberFormatException e) {
                throw new CreatorException(e);
            }
        }
        return phoneNumber;
    }

    @Override
    public int createAge(HttpServletRequest request) throws CreatorException {
        int age = 0;
        String buffer;
        buffer = request.getParameter("age");
        if (!buffer.isEmpty()) {
            try {
                age = Integer.valueOf(buffer);
            } catch (NumberFormatException e) {
                throw new CreatorException(e);
            }
        }
        return age;
    }

    @Override
    public Role createRole() {
        return Role.USER;
    }

    @Override
    public int createUserCount(HttpSession session) {


        int count = 0;
        try {
            count = (int) session.getAttribute("usersCount");
        } catch (Exception e) {
            //TODO
        }

        return count;
    }
}
