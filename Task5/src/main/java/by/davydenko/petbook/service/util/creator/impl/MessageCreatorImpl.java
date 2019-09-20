package by.davydenko.petbook.service.util.creator.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Message;
import by.davydenko.petbook.service.util.XSSFilter;
import by.davydenko.petbook.service.util.creator.CreatorException;
import by.davydenko.petbook.service.util.creator.MessageCreator;

import javax.servlet.http.HttpServletRequest;


public class MessageCreatorImpl implements MessageCreator {

    private static final int MAX_TEXT_LENGTH = 250;

    @Override
    public Message create() {
        return new Message();
    }

    @Override
    public int createId(String id) throws CreatorException {
        int userId = 0;
        try {
            userId = Integer.valueOf(id);
        } catch (NumberFormatException e) {
            throw new CreatorException("wrong id format", e);
        }
        return userId;
    }

    @Override
    public String createMessage(String text) throws CreatorException {
        if (text == null){
            throw new CreatorException("message is null");
        }else if( text.isEmpty()) {
            throw new CreatorException("message is empty");
        }else if(text.length()>MAX_TEXT_LENGTH){
            throw new CreatorException("message is too long");
        }else        if (XSSFilter.isScript(text)) {
            throw new CreatorException("message is a script");
        }
        return text;
    }

    @Override
    public String createDate() {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dt);
    }

    @Override
    public int createReceiverId(HttpServletRequest request) throws CreatorException {
        int receiverId = 0;
        try {
            receiverId = Integer.parseInt(request.getParameter(Attribute.RECEIVER_ID));
        } catch (NumberFormatException e) {
            throw new CreatorException("wrong user id format");
        }
        return receiverId;
    }

    @Override
    public int createByUserId(HttpServletRequest request) {
        int userId;
        try {
            userId = (int) request.getSession().getAttribute(Attribute.USER_ID);
        } catch (ClassCastException e) {
            userId = 0;
        }
        return userId;
    }
}
