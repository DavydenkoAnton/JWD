package by.davydenko.petbook.service.validator.impl;

import by.davydenko.petbook.entity.Message;
import by.davydenko.petbook.service.validator.MessageValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class MessageValidatorImpl implements MessageValidator {

    private static final Logger logger = LogManager.getLogger(MessageValidatorImpl.class);


    @Override
    public boolean valid(Message obj) {
        return false;
    }


    public boolean valid(int senderId,int recieverId, String message) {
        if (senderId < 0 || senderId == 0) {
            return false;
        }
        if (recieverId < 0 || recieverId == 0) {
            return false;
        }
        if (message != null) {
            if (message.equals("")) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }


    public boolean validMessage(HttpServletRequest request) {
        String message=(String)request.getParameter("message");
        if (message != null) {
            if (message.equals("")) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
