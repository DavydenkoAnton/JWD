package by.davydenko.petbook.service.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.dao.DaoFactory;
import by.davydenko.petbook.dao.UserDao;
import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.entity.Role;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.UserService;
import by.davydenko.petbook.service.util.creator.CreatorException;
import by.davydenko.petbook.service.util.creator.CreatorFactory;
import by.davydenko.petbook.service.util.creator.UserCreator;
import by.davydenko.petbook.service.util.validator.UserValidator;
import by.davydenko.petbook.service.util.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;


public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static final int PAGING_CAPACITY = 10;
    private static final String USER_AVATAR_FOLDER = "img/users_avatars";
    private static final String USER_AVATAR_URL = "http://localhost:8080/pb/img/users_avatar";
    private static final String JPEG = "image/jpeg";
    private static final String PNG = "image/png";
    private UserDao userDao;
    private DaoFactory daoFactory;
    private ValidatorFactory validatorFactory;
    private UserValidator userValidator;
    private UserCreator userCreator;
    private CreatorFactory creatorFactory;

    public UserServiceImpl() {
        daoFactory = DaoFactory.getInstance();
        validatorFactory = ValidatorFactory.getInstance();
        creatorFactory = CreatorFactory.getInstance();
        userDao = daoFactory.getUserDao();
        userValidator = validatorFactory.getUserValidator();
        userCreator = creatorFactory.getUserCreator();
    }

    @Override
    public void uploadAvatar(HttpServletRequest request) throws ServiceException {

        Part image;
        try {
            image = request.getPart(Attribute.USER_AVATAR);
        } catch (IOException | ServletException e) {
            throw new ServiceException("cannot get image from request",e);
        }
        if(image==null){
            throw new ServiceException("image is not exist");
        }

        int id = 0;
        try {
            id = userCreator.createId(request);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }
        String imageName;

        String contentType = image.getContentType();
        if (contentType.equals(JPEG)) {
            imageName = id + ".jpg";
        } else {
            throw new ServiceException("wrong image format for avatar");
        }

        String path = request.getServletContext().getRealPath("/") + USER_AVATAR_FOLDER;
        final Path outputFile = Paths.get(path, imageName);

        try (final ReadableByteChannel input = Channels.newChannel(image.getInputStream());
             final WritableByteChannel output = Channels.newChannel(new FileOutputStream(outputFile.toFile()));) {
            pipe(input, output);
        } catch (IOException e) {
            throw new ServiceException("IO error during read/write user avatar",e);
        }
        setAvatarURL(id, imageName);
    }


    private void setAvatarURL(int userId, String imageName) throws ServiceException {
        // final String userAvatarURL = request.getServletContext().getRealPath("/")+USER_AVATAR_URL + "/" + imageName;
        final String path = USER_AVATAR_FOLDER + "/" + imageName;
        try {
            userDao.updateAvatarURL(userId, path);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    private static void pipe(ReadableByteChannel in, WritableByteChannel out)
            throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (in.read(buffer) >= 0 || buffer.position() > 0) {
            buffer.flip();
            out.write(buffer);
            buffer.compact();
        }
    }

    @Override
    public void uploadName(HttpServletRequest request) throws ServiceException {

        String name;
        int id;
        try {
            name = userCreator.createName(request);
        id=userCreator.createId(request);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }

        try {
            userDao.updateName(id,name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> getUserById(HttpServletRequest request) throws ServiceException {
        Optional<User> user;
        int id;
        try {
            id = userCreator.createId(request);
        } catch (CreatorException e) {
            throw new ServiceException("Cannot create id");
        }

        try {
            user = userDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException("Cannot read user");
        }
        return user;
    }

    @Override
    public void pagingNext(HttpSession httpSession) throws ServiceException {
        List<User> users = new ArrayList<>();
        if (httpSession.getAttribute("users") == null) {
            try {
                users = userDao.readUsers();
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
            List<User> bufUsers = new ArrayList<>();
            for (int i = 0; i < PAGING_CAPACITY; i++) {
                if (i < users.size()) {
                    bufUsers.add(users.get(i));
                }
            }
            httpSession.setAttribute("users", bufUsers);
            httpSession.setAttribute("pagingNext", bufUsers.size());
        } else {
            List<User> buffUsers = new ArrayList<>();
            int from = (int) httpSession.getAttribute("pagingNext");
            int to = from + PAGING_CAPACITY;
            try {
                users = userDao.readUsers();
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
            if (users.size() > from) {
                for (int i = from; i < to; i++) {
                    if (i < users.size()) {
                        buffUsers.add(users.get(i));
                    } else {
                        break;
                    }
                }
                httpSession.setAttribute("users", buffUsers);
                httpSession.setAttribute("pagingNext", from + buffUsers.size());

            }
        }
    }

    @Override
    public void pagingPrev(HttpSession httpSession) throws ServiceException {

        List<User> users = new ArrayList<>();
        List<User> buffUsers = new ArrayList<>();
        int to = (int) httpSession.getAttribute("pagingNext");
        int from = to - PAGING_CAPACITY;

        try {
            users = userDao.readUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        if (from > 0) {
            for (int i = from; i < to; i++) {
                if (i < users.size()) {
                    buffUsers.add(users.get(i));
                }
            }
            httpSession.setAttribute("users", buffUsers);
            httpSession.setAttribute("pagingNext", from);
        } else {
            for (int i = 0; i < PAGING_CAPACITY; i++) {
                if (i < users.size()) {
                    buffUsers.add(users.get(i));
                } else {
                    break;
                }
            }
            httpSession.setAttribute("users", buffUsers);
            httpSession.setAttribute("pagingNext", 0);
        }
    }

    @Override
    public Optional<User> getUserByLoginPassword(HttpServletRequest request) throws ServiceException {
        Optional<User> user;
        String login = "";
        String password = "";

        try {
            login = userCreator.createLogin(request);
            password = userCreator.createPassword(request);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }

        try {
            user = userDao.read(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }





    @Override
    public void deleteUser(HttpServletRequest request) throws ServiceException {
        String login = "";
        try {
            login = userCreator.createLogin(request);
            userDao.delete(login);
        } catch (CreatorException | DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }


    @Override
    public void registerUser(HttpServletRequest request) throws ServiceException {
        User user = new User();

        try {
            String login = userCreator.createLogin(request);
            String password = userCreator.createPassword(request);
            Role role = userCreator.createRole();
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(role);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }

        try {
            userDao.create(user);
        } catch (DaoException e) {
            logger.error(getClass().getName() + "[ cannot get user ]");
            throw new ServiceException(e);
        }
    }

    @Override
    public int getIdByLogin(HttpServletRequest request) throws ServiceException {
        String login = "";
        int id;

        if (userValidator.validLogin(request)) {
            login = request.getParameter("login");
        }

        try {
            id = userDao.getIdByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return id;
    }


    @Override
    public int getId(HttpSession httpSession) throws ServiceException {
        int id = 0;
        if (userValidator.validId(httpSession)) {
            id = (int) httpSession.getAttribute("id");
        }
        return id;
    }
}

