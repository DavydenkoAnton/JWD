package by.davydenko.petbook.service;

import by.davydenko.petbook.service.impl.ArticleServiceImpl;
import by.davydenko.petbook.service.impl.MessageServiceImpl;
import by.davydenko.petbook.service.impl.PetServiceImpl;
import by.davydenko.petbook.service.impl.UserServiceImpl;

public class ServiceFactory<T> {

    private final static ServiceFactory instance = new ServiceFactory();
    private MessageService messageService = new MessageServiceImpl();
    private UserService userService = new UserServiceImpl();
    private PetService petService = new PetServiceImpl();
    private ArticleService articleService = new ArticleServiceImpl();


    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public UserService getUserService() {
        return userService;
    }

    public PetService getPetService() {
        return petService;
    }

    public ArticleService getArticleService() {return articleService;
    }
}
