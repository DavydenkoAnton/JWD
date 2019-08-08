package by.davydenko.petbook.service.creator.impl;

import by.davydenko.petbook.entity.Message;
import by.davydenko.petbook.service.creator.MessageCreator;
import by.davydenko.petbook.service.validator.MessageValidator;
import by.davydenko.petbook.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MessageCreatorImpl implements MessageCreator {

    private static final Logger logger = LogManager.getLogger(MessageCreatorImpl.class);

    private final static String MESSAGE_TEXT_ATTRIBUTE = "message";
    private final static String SENDER_ID_ATTRIBUTE = "id";
    private final static String RECIEVER_LOGIN_ATTRIBUTE = "login";
    MessageValidator messageValidator;
    ValidatorFactory validatorFactory;


    public MessageCreatorImpl() {
        validatorFactory = ValidatorFactory.getInstance();
        messageValidator = validatorFactory.getMessageValidator();
    }

    @Override
    public Message create() {
        return new Message();
    }

    public Message create(int senderID,int recieverId,String messageText)  {
        Message message = new Message();

        if(messageValidator.valid(senderID,recieverId,messageText)){
            message.setSenderId(senderID);
            message.setUserId(recieverId);
            message.setMessage(messageText);
        }

        return message;
    }
}
