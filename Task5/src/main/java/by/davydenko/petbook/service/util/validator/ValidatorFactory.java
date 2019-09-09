package by.davydenko.petbook.service.util.validator;


import by.davydenko.petbook.service.util.validator.impl.MessageValidatorImpl;
import by.davydenko.petbook.service.util.validator.impl.UserValidatorImpl;

public class ValidatorFactory {

    private static final ValidatorFactory instance = new ValidatorFactory();
    private MessageValidator messageValidator = new MessageValidatorImpl();
    private UserValidator userValidator = new UserValidatorImpl();

    private ValidatorFactory() {
    }

    public static ValidatorFactory getInstance() {
        return instance;
    }

    public MessageValidator getMessageValidator() {
        return messageValidator;
    }

    public UserValidator getUserValidator() {
        return userValidator;
    }
}
