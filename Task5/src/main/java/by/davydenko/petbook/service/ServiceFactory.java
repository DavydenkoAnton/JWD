package by.davydenko.petbook.service;

public class ServiceFactory<T> {

    private static ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }


}
