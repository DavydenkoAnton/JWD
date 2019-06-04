package by.javatr.transport.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TrainCarPassenger {
    private int id;

    private List<Passenger> passengers = new ArrayList<>();


    public TrainCarPassenger() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainCarPassenger that = (TrainCarPassenger) o;

        if (id != that.id) {
            return false;
        }
        if (this.passengers == null) {
            if (that.passengers != null) return false;
        } else if (!this.passengers.equals(that.passengers)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 17;
        return prime * id + prime * (passengers == null ? 0 : passengers.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder trainCarPassenger = new StringBuilder();
        for (Passenger passenger : passengers) {
            trainCarPassenger.append(passenger.toString());
        }
        return trainCarPassenger.toString();
    }


}
