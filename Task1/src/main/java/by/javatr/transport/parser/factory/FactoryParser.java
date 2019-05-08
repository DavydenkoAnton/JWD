package by.javatr.transport.parser.factory;

import by.javatr.transport.parser.TrainPassengerParser;
import by.javatr.transport.parser.impl.TrainPassengerParserTxtImpl;
import by.javatr.transport.service.TrainPassengerService;

public class FactoryParser {
    private static final FactoryParser instance = new FactoryParser();

    private final TrainPassengerParser trainPassengerParser = new TrainPassengerParserTxtImpl();


    private FactoryParser() {
    }

    public static FactoryParser getInstance() {
        return instance;
    }

    public TrainPassengerParser getTrainPassengerParser() {
        return trainPassengerParser;
    }

}
