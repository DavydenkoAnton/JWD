package by.javatr.transport.service.factory;

import by.javatr.transport.service.PassengerService;
import by.javatr.transport.service.TrainPassengerService;
import by.javatr.transport.service.impl.PassengerServiceImpl;
import by.javatr.transport.service.impl.TrainPassengerServiceImpl;

public class FactoryService {
    private static final FactoryService instance = new FactoryService();

    private final TrainPassengerService trainPassengerService = new TrainPassengerServiceImpl();
    private final PassengerService passengerService = new PassengerServiceImpl();

    private FactoryService() {
    }

    public static FactoryService getInstance() {
        return instance;
    }

    public TrainPassengerService getTrainPassengerService() {
        return trainPassengerService;
    }

    public PassengerService getPassengerService() {
        return passengerService;
    }
}
