package by.davydenko.greenhouse.service;

public class ServiceFactory<T> {

    private XMLService service;
    private static ServiceFactory instance;

    public enum ServiceType {
        XML,JSON
    }

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }


    public XMLService getService(ServiceType serviceType) {
        switch (serviceType) {
            case XML:
                service = new XMLServiceImpl();
        }
        return service;
    }

}
