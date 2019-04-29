package by.javatr.transport.service;

import by.javatr.transport.entity.TrainCarPassenger;

public interface TrainPassengerService {
    void addTrainCarPassenger(int id, int passengerCountMax);

    void getTrainCarPassenger();
}
