package by.davydenko.petbook.service;

import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.DaoFactory;
import by.davydenko.petbook.dao.MessageDao;
import by.davydenko.petbook.dao.UserDao;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.Message;
import by.davydenko.petbook.service.creator.CreatorException;
import by.davydenko.petbook.service.creator.CreatorFactory;
import by.davydenko.petbook.service.creator.MessageCreator;
import by.davydenko.petbook.service.validator.MessageValidator;
import by.davydenko.petbook.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class MessageServiceImpl implements MessageService {
    private static final Logger logger = LogManager.getLogger(MessageServiceImpl.class);
    private Message message;
    private MessageCreator messageCreator;
    private CreatorFactory creatorFactory;
    private MessageDao messageDao;
    private DaoFactory daoFactory;
    private MessageValidator messageValidator;
    private ValidatorFactory validatorFactory;
    private UserDao userDao;

    public MessageServiceImpl() {
        creatorFactory = CreatorFactory.getInstance();
        messageCreator = creatorFactory.getMessageCreator();
        daoFactory = DaoFactory.getInstance();
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
    public void sendMessage(int senderID, int recieverId, String messageText) throws ServiceException {
        int userId = 0;
        Message messsage;

        try {
            message = messageCreator.create(senderID, recieverId, messageText);
        } catch (CreatorException e) {
            logger.error(getClass().getName() + "[ Cannot create message. ]");
            throw new ServiceException(e);
        }

        try {
            messageDao.create(message);
        } catch (ConnectionPoolException | DaoException e) {
            logger.error(getClass().getName() + "[ Cannot send message. ]");
            throw new ServiceException(e);
        }
    }

}
