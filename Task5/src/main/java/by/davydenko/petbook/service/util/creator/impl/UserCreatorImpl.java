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
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern;
    private Matcher matcher;

    public UserCreatorImpl() {
    }


    @Override
    public User create(HttpServletRequest request) throws CreatorException {
        User user = new User();
        user.setLogin(createLogin(request));
        user.setPassword(createPassword(request));
        user.setName(createName(request));
        user.setEmail(createEmail(request));
        user.setPhoneNumber(createPhoneNumber(request));
        user.setAge(createAge(request));
        user.setRole(createRole());
        return user;
    }

    public User createFromLoginPassword() {
        User user = new User();
        return user;
    }


    @Override
    public String createLogin(HttpServletRequest request) throws CreatorException {
        String login = request.getParameter(Attribute.LOGIN);
        if (login == null||login.isEmpty()) {
            throw new CreatorException("login incorrect");
        }
        return login;
    }


    @Override
    public String createPassword(HttpServletRequest request) throws CreatorException {
        String password = request.getParameter(Attribute.PASSWORD);
        if (password == null || password.isEmpty()) {
            throw new CreatorException("password incorrect");
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
    public String createName(HttpServletRequest request) throws CreatorException {
        String name = request.getParameter(Attribute.NAME);
        if (name == null || name.isEmpty()) {
            throw new CreatorException("name incorrect");
        }
        return name;
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
    public int createId(HttpSession session) throws CreatorException {
        int id;
        try {
            id = (Integer) session.getAttribute(Attribute.ID);
        } catch (NumberFormatException e) {
            throw new CreatorException("wrong id format");
        }
        return id;
    }

    @Override
    public int createId(HttpServletRequest request) throws CreatorException {
        int id;
        try {
            id = (Integer) request.getSession().getAttribute(Attribute.ID);
        } catch (NumberFormatException e) {
            throw new CreatorException("wrong id format");
        }
        return id;
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
