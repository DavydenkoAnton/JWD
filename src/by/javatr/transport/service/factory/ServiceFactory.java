package by.javatr.transport.service.factory;

import by.javatr.transport.service.PassengerService;
import by.javatr.transport.service.TrainPassengerService;
import by.javatr.transport.service.impl.ServicePassengerImpl;
import by.javatr.transport.service.impl.TrainPassengerServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final TrainPassengerService trainPassengerService = new TrainPassengerServiceImpl();
    private final PassengerService passengerService = new ServicePassengerImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public TrainPassengerService getTrainPassengerService() {
        return trainPassengerService;
    }

    public PassengerService getPassengerService() {
        return passengerService;
    }
}
