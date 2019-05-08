package by.javatr.transport.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TrainPassenger {
    private UUID id = UUID.randomUUID();
    private List<TrainCarPassenger> trainCarsPassengers = new ArrayList<>();

    public TrainPassenger() {
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainPassenger that = (TrainPassenger) o;
        if (id == null) {
            if (that.id != null) {
                return id.equals(that.id);
            } else {
                if (!id.equals(that.id)) {
                    return false;
                }
            }
        }
        if (trainCarsPassengers == null) {
            if (that.trainCarsPassengers != null) {
                return trainCarsPassengers.equals(that.trainCarsPassengers);
            } else {
                if (!trainCarsPassengers.equals(that.trainCarsPassengers)) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public int hashCode() {
        int prime = 17;
        return prime * (id == null ? 00 : id.hashCode()) + prime * (trainCarsPassengers == null ? 0 : trainCarsPassengers.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder train = new StringBuilder();
        train.append(id).append(" ");
        for (TrainCarPassenger trainCarPassenger : trainCarsPassengers) {
            train.append(trainCarPassenger.toString());
        }
        return train.toString();
    }
}
