package by.davydenko.petbook.service.util.creator.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Message;
import by.davydenko.petbook.service.util.XSSFilter;
import by.davydenko.petbook.service.util.creator.CreatorException;
import by.davydenko.petbook.service.util.creator.MessageCreator;
import by.davydenko.petbook.service.util.validator.MessageValidator;
import by.davydenko.petbook.service.util.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class MessageCreatorImpl implements MessageCreator {

    @Override
    public Message create() {
        return new Message();
    }

    @Override
    public int createSenderId(HttpServletRequest request) throws CreatorException {
        int userId = 0;
        try {
            userId = Integer.parseInt(request.getParameter(Attribute.MESSAGE_SENDER_ID));
        } catch (NumberFormatException e) {
            throw new CreatorException("wrong user id format");
        }
        return userId;
    }

    @Override
    public int createUserId(HttpServletRequest request) throws CreatorException {
        int senderId = 0;
        try {
            senderId = (int) request.getSession().getAttribute(Attribute.ID);
        } catch (NumberFormatException e) {
            throw new CreatorException("wrong sender id format");
        }
        return senderId;
    }

    @Override
    public String createMessage(HttpServletRequest request) throws CreatorException {
        String message = request.getParameter(Attribute.MESSAGE);
        if (message == null || message.isEmpty()) {
            throw new CreatorException("wrong message value");
        }
        if (XSSFilter.isScript(message)) {
            throw new CreatorException("trying to add script in message");
        }
        return message;
    }

    @Override
    public String createDate(HttpServletRequest request) {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dt);
    }
}
