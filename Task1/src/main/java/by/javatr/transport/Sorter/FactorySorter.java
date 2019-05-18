package by.javatr.transport.Sorter;

import by.javatr.transport.Sorter.impl.TrainPassengerSorterImpl;

public class FactorySorter {
    private final static FactorySorter factorySorter = new FactorySorter();
    TrainPassengerSorter trainPassengerSorter = new TrainPassengerSorterImpl();

    private FactorySorter() {
    }

    public static FactorySorter getInstance() {
        return factorySorter;
    }

    public TrainPassengerSorter getTrainPassengerSorter() {
        return trainPassengerSorter;
    }
}
