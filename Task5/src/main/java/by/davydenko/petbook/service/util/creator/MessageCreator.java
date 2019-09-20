package by.davydenko.petbook.service.util.creator;

import by.davydenko.petbook.entity.Message;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.util.Optional;

public interface MessageCreator extends Creator<Message> {
    @Override
    Message create();

    int createId(String id) throws CreatorException;
    String createMessage(String text) throws CreatorException;
    String createDate();

    int createReceiverId(HttpServletRequest request) throws CreatorException;

    int createByUserId(HttpServletRequest request);
}
