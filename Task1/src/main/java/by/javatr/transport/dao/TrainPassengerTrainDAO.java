package by.javatr.transport.dao;

import by.javatr.transport.entity.TrainPassenger;

import java.io.IOException;

public interface TrainPassengerDAO extends DAO {
    void addTrainPassenger(String request) throws IOException;
}
