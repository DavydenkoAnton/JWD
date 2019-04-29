package by.javatr.transport.entity;

import java.util.ArrayList;
import java.util.List;

public class TrainPassenger {

    List<TrainCarPassenger> trainCarsPassenger = new ArrayList<>();

    public TrainPassenger(List<TrainCarPassenger> trainCarPassengers) {
        this.trainCarsPassenger = trainCarPassengers;
    }

    public List<TrainCarPassenger> getTrainCarsPassenger() {
        return trainCarsPassenger;
    }

    public void setTrainCarsPassenger(List<TrainCarPassenger> trainCarsPassenger) {
        this.trainCarsPassenger=trainCarsPassenger;
    }


}
