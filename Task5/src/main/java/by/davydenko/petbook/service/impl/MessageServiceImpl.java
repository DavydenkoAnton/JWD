package by.davydenko.petbook.service.impl;

import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.DaoFactory;
import by.davydenko.petbook.dao.MessageDao;
import by.davydenko.petbook.dao.UserDao;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.Message;
import by.davydenko.petbook.service.MessageService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.util.creator.CreatorException;
import by.davydenko.petbook.service.util.creator.CreatorFactory;
import by.davydenko.petbook.service.util.creator.MessageCreator;
import by.davydenko.petbook.service.util.validator.MessageValidator;
import by.davydenko.petbook.service.util.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class MessageServiceImpl implements MessageService {
    private static final Logger logger = LogManager.getLogger(MessageServiceImpl.class);
    private MessageCreator messageCreator;
    private CreatorFactory creatorFactory;
    private MessageDao messageDao;
    private DaoFactory daoFactory;
    private MessageValidator messageValidator;
    private ValidatorFactory validatorFactory;
    private UserDao userDao;

    public MessageServiceImpl() {
        creatorFactory = CreatorFactory.getInstance();
        daoFactory = DaoFactory.getInstance();
        messageCreator = creatorFactory.getMessageCreator();
        messageDao = daoFactory.getMessageDao();
        userDao = daoFactory.getUserDao();
    }

    @Override
    public String getMessage(HttpServletRequest request) {
        validatorFactory = ValidatorFactory.getInstance();
        creatorFactory = CreatorFactory.getInstance();
        messageValidator = validatorFactory.getMessageValidator();
        messageCreator = creatorFactory.getMessageCreator();
        String message = "";

        if (messageValidator.validMessage(request)) {
            message = request.getParameter("message");
        }

        return message;
    }

    @Override
    public void sendMessage(HttpServletRequest request) throws ServiceException {
        Message message = new Message();
        try {
            message.setUserId(messageCreator.createUserId(request));
            message.setSenderId(messageCreator.createSenderId(request));
            message.setDate(messageCreator.createDate(request));
            message.setMessage(messageCreator.createMessage(request));
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }
        try {
            messageDao.create(message);
        } catch (ConnectionPoolException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<List<Message>> getChatMessages(HttpServletRequest request) throws ServiceException {
        Optional<List<Message>> optionalMessages;
        int userId = 0;
        int senderId = 0;
        try {
            userId = messageCreator.createUserId(request);
            senderId = messageCreator.createSenderId(request);
        } catch (CreatorException e) {
            e.printStackTrace();
        }
        try {
            optionalMessages = messageDao.readChatMessages(userId, senderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalMessages;
    }
}
