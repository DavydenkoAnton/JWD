package by.javatr.transport.entity;

import java.util.ArrayList;
import java.util.List;

public class TrainCarPassenger {
    private int passengerCountMax;
    private int id;
    private int passengerCount;

    private List<Passenger> passengers = new ArrayList/*<>*/();

    public TrainCarPassenger(int id, int passengerCountMax) {
        this.id = id;
        this.passengerCountMax = passengerCountMax;
    }

    public int getNumber() {
        return id;
    }

    public void setNumber(int number) {
        this.id = number;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public int getPassengerCountMax() {
        return passengerCountMax;
    }

    public void setPassengerCountMax(int passengerCountMax) {
        this.passengerCountMax = passengerCountMax;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        if (passengers.size() < passengerCountMax) {
            this.passengers = passengers;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainCarPassenger that = (TrainCarPassenger) o;
        if (passengerCountMax != that.passengerCountMax) {
            return false;
        }
        if (id != that.id) {
            return false;
        }
        if (passengerCount != that.passengerCount) {
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
        return prime * passengerCountMax + prime * id + prime * passengerCount;
    }
}
