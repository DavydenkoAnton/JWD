package by.javatr.transport.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TrainPassenger {
    private UUID id = UUID.randomUUID();
    List<TrainCarPassenger> trainCarsPassenger = new ArrayList/*<>*/();

    public TrainPassenger(List<TrainCarPassenger> trainCarPassengers) {
        this.trainCarsPassenger = trainCarPassengers;
    }

    public List<TrainCarPassenger> getTrainCarsPassenger() {
        return trainCarsPassenger;
    }

    public void setTrainCarsPassenger(List<TrainCarPassenger> trainCarsPassenger) {
        this.trainCarsPassenger = trainCarsPassenger;
    }


}
