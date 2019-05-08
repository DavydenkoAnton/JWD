package by.javatr.transport.creator;

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
