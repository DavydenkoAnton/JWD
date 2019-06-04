package by.davydenko.greenhouse.service;

public class ServiceFactory<T> {

    private XMLService service;
	private XMLService servicexddddddddddddddddddddddddddddddddddddddddddddddd;
    private static ServiceFactory instance;

    public enum SERVICE_TYPE {
        XML
    }

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }


    public XMLService getService(SERVICE_TYPE serviceType) {
        switch (serviceType) {
            case XML:
                service = new XMLServiceImpl();
        }
        return service;
    }

}
