package by.javatr.transport.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TrainCarPassenger {
    private int passengerCountMax;
    private UUID id = UUID.randomUUID();

    private List<Passenger> passengers = new ArrayList<>();

    public TrainCarPassenger(int passengerCountMax) {
        this.passengerCountMax = passengerCountMax;
    }

    public UUID getID() {
        return id;
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
        if (passengers.size() <= passengerCountMax) {
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
        return prime * passengerCountMax + prime * id.hashCode() + prime * (passengers == null ? 0 : passengers.hashCode());
    }

    @Override
    public String toString(){
        StringBuilder trainCarPassenger=new StringBuilder();
        for(Passenger passenger:passengers){
            trainCarPassenger.append(passenger.toString());
        }
        return trainCarPassenger.toString();
    }
}
