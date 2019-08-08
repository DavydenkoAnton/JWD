package by.davydenko.petbook.service.creator;

import by.davydenko.petbook.entity.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface MessageCreator extends Creator<Message> {
    @Override
    Message create();
    Message create(int senderID,int recieverId,String message) throws CreatorException;
}
