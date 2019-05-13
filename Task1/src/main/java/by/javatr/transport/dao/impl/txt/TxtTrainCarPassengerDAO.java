package by.javatr.transport.dao.impl.txt;

import by.javatr.transport.dao.TrainCarPassengerDAO;
import by.javatr.transport.entity.Passenger;
import by.javatr.transport.entity.TrainCarPassenger;

public class TxtTrainCarPassengerDAO implements TrainCarPassengerDAO {
    @Override
    public TrainCarPassenger getTrainCarPassenger() {
        return new TrainCarPassenger();
    }
}
