package by.davydenko.petbook.service.creator;


import by.davydenko.petbook.service.creator.impl.MessageCreatorImpl;
import by.davydenko.petbook.service.creator.impl.UserCreatorImpl;

public class CreatorFactory {

    private static final CreatorFactory instance = new CreatorFactory();
    private MessageCreator messageCreator = new MessageCreatorImpl();
    private UserCreator userCreator = new UserCreatorImpl();

    private CreatorFactory() {
    }

    public static CreatorFactory getInstance() {
        return instance;
    }

    public MessageCreator getMessageCreator() {
        return messageCreator;
    }

    public UserCreator getUserCreator() {
        return userCreator;
    }
}
