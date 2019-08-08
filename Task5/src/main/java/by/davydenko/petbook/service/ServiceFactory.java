package by.davydenko.petbook.service;

public class ServiceFactory<T> {

    private final static ServiceFactory instance = new ServiceFactory();
    private MessageService messageService = new MessageServiceImpl();
    private UserService userService = new UserServiceImpl();


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

}
