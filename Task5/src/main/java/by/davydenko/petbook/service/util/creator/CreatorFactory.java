package by.davydenko.petbook.service.util.creator;


import by.davydenko.petbook.service.util.creator.impl.ArticleCreatorImpl;
import by.davydenko.petbook.service.util.creator.impl.MessageCreatorImpl;
import by.davydenko.petbook.service.util.creator.impl.PetCreatorImpl;
import by.davydenko.petbook.service.util.creator.impl.UserCreatorImpl;

public class CreatorFactory {

    private static final CreatorFactory instance = new CreatorFactory();
    private MessageCreator messageCreator = new MessageCreatorImpl();
    private UserCreator userCreator = new UserCreatorImpl();
    private PetCreator petCreator = new PetCreatorImpl();
    private ArticleCreator articleCreator = new ArticleCreatorImpl();

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
    public PetCreator getPetCreator() {
        return petCreator;
    }
    public ArticleCreator getArticleCreator() {
        return articleCreator;
    }
}
