package by.davydenko.petbook.service.impl;

import by.davydenko.petbook.controller.command.util.Error;
import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.DaoFactory;
import by.davydenko.petbook.dao.UserDao;
import by.davydenko.petbook.entity.Role;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.UserService;
import by.davydenko.petbook.service.util.creator.CreatorException;
import by.davydenko.petbook.service.util.creator.CreatorFactory;
import by.davydenko.petbook.service.util.creator.UserCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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


public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static final String USER_AVATAR_FOLDER = "img/users_avatars";
    private static final String JPEG = "image/jpeg";
    private static final String PNG = "image/png";
    private UserDao userDao;
    private DaoFactory daoFactory;
    private UserCreator userCreator;
    private CreatorFactory creatorFactory;

    public UserServiceImpl() {
        daoFactory = DaoFactory.getInstance();
        creatorFactory = CreatorFactory.getInstance();
        userDao = daoFactory.getUserDao();
        userCreator = creatorFactory.getUserCreator();
    }

    @Override
    public void uploadAvatar(Part part, String path, String userId) throws ServiceException {

//        Part image;
//        try {
//            image = request.getPart(Attribute.USER_AVATAR);
//        } catch (IOException | ServletException e) {
//            throw new ServiceException("cannot get image from request", e);
//        }
//        if (image == null) {
//            throw new ServiceException("image is not exist");
//        }

        int id = 0;
        try {
            id = userCreator.createId(userId);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }
        String imageName;

        String contentType = part.getContentType();
        if (contentType.equals(JPEG)) {
            imageName = id + ".jpg";
        } else {
            throw new ServiceException("wrong image format for avatar");
        }

        //String path = request.getServletContext().getRealPath("/") + USER_AVATAR_FOLDER;
        final Path outputFile = Paths.get(path, imageName);

        try (final ReadableByteChannel input = Channels.newChannel(part.getInputStream());
             final WritableByteChannel output = Channels.newChannel(new FileOutputStream(outputFile.toFile()));) {
            pipe(input, output);
        } catch (IOException e) {
            throw new ServiceException("IO error during read/write user avatar", e);
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
    public void uploadName(String name, String id) throws ServiceException {
        try {
            name = userCreator.createName(name);
            int userId = userCreator.createId(id);
            userDao.updateName(userId, name);
        } catch (CreatorException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<List<User>> getAllUsers() throws ServiceException {
        Optional<List<User>> optionalUsers;
        try {
            optionalUsers = userDao.readUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalUsers;
    }

    @Override
    public Optional<User> getById(String userId) throws ServiceException {
        Optional<User> user;
        int id;
        try {
            id = userCreator.createId(userId);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }

        try {
            user = userDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }


    @Override
    public Optional<User> getByLoginPassword(String login, String password) throws ServiceException {
        Optional<User> user;
        try {
            login = userCreator.createLogin(login);
            password = userCreator.createPassword(password);
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
    public void deleteByLogin(String userLogin) throws ServiceException {

        try {
            String login = userCreator.createLogin(userLogin);
            userDao.delete(login);
        } catch (CreatorException | DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeRole(String id) throws ServiceException {
        try {
            int idTemp = userCreator.createId(id);
            Optional<User> optionalUser=userDao.read(idTemp);
            if(optionalUser.isPresent()){
                User user=optionalUser.get();
                if (user.getRole().equals(Role.USER)) {
                    user.setRole(Role.GUEST);
                } else if (user.getRole().equals(Role.GUEST)) {
                    user.setRole(Role.USER);
                }
                userDao.updateRole(idTemp, user.getRole());
            }
        } catch (CreatorException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void registerUser(String login, String password, String passwordCheck) throws ServiceException {
        Error error = Error.getInstance();
        try {
            String loginTemp = userCreator.createLogin(login);
            String passwordTemp = userCreator.createPassword(password);
            String passwordCheckTemp = userCreator.createPassword(passwordCheck);
            if (passwordTemp.equals(passwordCheckTemp)) {
                Role role = userCreator.createRole();
                userDao.create(loginTemp, passwordTemp, role);
            } else {
                error.setPasswordCheck("password not equal");
                throw new ServiceException("password not equal");
            }
        } catch (CreatorException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getIdByLogin(String userLogin) throws ServiceException {
        String login = null;
        try {
            login = userCreator.createLogin(userLogin);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }
        int id;
        try {
            id = userDao.getIdByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return id;
    }


}

