package by.davydenko.petbook.service.util.validator;

import by.davydenko.petbook.entity.Message;

import javax.servlet.http.HttpServletRequest;

public interface MessageValidator extends Validator<Message> {
    @Override
    boolean valid(Message obj);
    boolean valid(int senderId, int recieverId, String message);
    boolean validMessage(HttpServletRequest request);
}
