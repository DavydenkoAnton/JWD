package by.javatr.transport.dao;

import by.javatr.transport.entity.Passenger;
import by.javatr.transport.entity.TrainCarPassenger;

public interface TrainCarPassengerDAO {
    void addPassenger(Passenger passenger);
    TrainCarPassenger getTrainCarPassenger();
}
