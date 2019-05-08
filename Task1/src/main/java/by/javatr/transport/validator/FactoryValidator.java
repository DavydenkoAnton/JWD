package by.javatr.transport.validator;

import by.javatr.transport.validator.impl.TrainPassengerValidatorImpl;

public class FactoryValidator {
    private static final FactoryValidator instance = new FactoryValidator();

    private final TrainPassengerValidator trainPassengerValidator = new TrainPassengerValidatorImpl();


    private FactoryValidator() {
    }

    public static FactoryValidator getInstance() {
        return instance;
    }

    public TrainPassengerValidator getTrainPassengerCreator() {
        return trainPassengerValidator;
    }
}
