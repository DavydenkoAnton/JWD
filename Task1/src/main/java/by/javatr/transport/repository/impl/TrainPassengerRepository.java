package by.javatr.transport.repository.impl;

import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.repository.Repository;
import by.javatr.transport.repository.Specification;

import java.util.ArrayList;
import java.util.List;

public class TrainPassengerRepository implements Repository<TrainPassenger> {


    private List<TrainPassenger> trainPassengers = new ArrayList<>();

    public void addTrainPassenger(List<TrainPassenger> trainPassenger) {
        for (TrainPassenger trainPass : trainPassenger) {
            trainPassengers.add(trainPass);
        }
    }

    public List<TrainPassenger> find(Specification<TrainPassenger> spec) {

        List<TrainPassenger> result = new ArrayList<>();

        for (TrainPassenger trainPassenger : trainPassengers) {
            if (spec.match(trainPassenger)) {
                result.add(trainPassenger);
            }
        }
        return result;
    }
}
