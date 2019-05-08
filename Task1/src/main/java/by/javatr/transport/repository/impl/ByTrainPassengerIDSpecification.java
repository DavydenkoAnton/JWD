package by.javatr.transport.repository.impl;

import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.repository.Specification;

import java.util.UUID;

public class ByTrainPassengerIDSpecification implements Specification<TrainPassenger> {

    private UUID searchID;

    public ByTrainPassengerIDSpecification(UUID searchID) {
        this.searchID = searchID;
    }

    public boolean match(TrainPassenger bean) {
        return this.searchID.equals(bean.getId());
    }
}
