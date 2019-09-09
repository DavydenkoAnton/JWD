package by.davydenko.petbook.service.util.creator;

import by.davydenko.petbook.entity.Message;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.util.Optional;

public interface MessageCreator extends Creator<Message> {
    @Override
    Message create();

    int createUserId(HttpServletRequest request) throws CreatorException;
    int createSenderId(HttpServletRequest request) throws CreatorException;
    String createMessage(HttpServletRequest request) throws CreatorException;
    String createDate(HttpServletRequest request);
}
