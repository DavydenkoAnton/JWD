package by.javatr.transport.repository.impl;

import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.repository.Specification;

import java.util.UUID;

public class AllTrainPassengerSpecification implements Specification<TrainPassenger> {



    public AllTrainPassengerSpecification() {

    }

    public boolean match(TrainPassenger bean) {
        return true;
    }
}