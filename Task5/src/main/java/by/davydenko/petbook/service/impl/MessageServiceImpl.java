package by.davydenko.petbook.service.impl;

import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.DaoFactory;
import by.davydenko.petbook.dao.MessageDao;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.Message;
import by.davydenko.petbook.service.MessageService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.util.creator.CreatorException;
import by.davydenko.petbook.service.util.creator.CreatorFactory;
import by.davydenko.petbook.service.util.creator.MessageCreator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class MessageServiceImpl implements MessageService {
    private MessageCreator messageCreator;
    private CreatorFactory creatorFactory;
    private MessageDao messageDao;
    private DaoFactory daoFactory;

    public MessageServiceImpl() {
        creatorFactory = CreatorFactory.getInstance();
        daoFactory = DaoFactory.getInstance();
        messageCreator = creatorFactory.getMessageCreator();
        messageDao = daoFactory.getMessageDao();
    }

    @Override
    public int getReceiverId(HttpServletRequest request) throws ServiceException {
        int receiverId;
        try {
            receiverId = messageCreator.createReceiverId(request);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }
        return receiverId;
    }

    @Override
    public boolean isFriend(HttpServletRequest request) {

        int userId = messageCreator.createByUserId(request);


        return true;
    }

    @Override
    public String getMessage(HttpServletRequest request) {
        creatorFactory = CreatorFactory.getInstance();
        messageCreator = creatorFactory.getMessageCreator();
        return "messageServiceImpl getMessage()";
    }

    @Override
    public void sendMessage(String receiverId, String senderId, String text) throws ServiceException {
        Message message;
        try {
            int receiver_id = messageCreator.createId(receiverId);
            int sender_id = messageCreator.createId(senderId);
            String messageTemp = messageCreator.createMessage(text);
            String date = messageCreator.createDate();
            message = new Message();
            message.setUserId(receiver_id);
            message.setSenderId(sender_id);
            message.setDate(date);
            message.setMessage(messageTemp);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }
        try {
            messageDao.create(message);
        } catch ( DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<List<Message>> getChatMessages(String receiverId, String senderId) throws ServiceException {
        Optional<List<Message>> optionalMessages;
        try {
            int receiver_id = messageCreator.createId(receiverId);
            int sender_id = messageCreator.createId(senderId);
            optionalMessages = messageDao.readChatMessages(receiver_id, sender_id);
        } catch (CreatorException | DaoException e) {
            throw new ServiceException(e);
        }
        return optionalMessages;
    }
}
