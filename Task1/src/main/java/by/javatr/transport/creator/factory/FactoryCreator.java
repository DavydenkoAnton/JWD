package by.javatr.transport.creator.factory;

import by.javatr.transport.creator.TrainPassengerCreator;
import by.javatr.transport.creator.impl.TrainPassengerCreatorImpl;

public class FactoryCreator {
    private static final FactoryCreator instance = new FactoryCreator();

    private final TrainPassengerCreator trainPassengerCreator = new TrainPassengerCreatorImpl();


    private FactoryCreator() {
    }

    public static FactoryCreator getInstance() {
        return instance;
    }

    public TrainPassengerCreator getTrainPassengerCreator() {
        return trainPassengerCreator;
    }
}
