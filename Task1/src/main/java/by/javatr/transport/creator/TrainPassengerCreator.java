package by.javatr.transport.creator;

import by.javatr.transport.entity.TrainPassenger;

import java.util.List;

public interface TrainPassengerCreator {
    List<TrainPassenger> create(List<String> trainsPassenger);
}
