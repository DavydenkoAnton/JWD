package by.javatr.transport.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Passenger {
    private String name;
    private UUID id = UUID.randomUUID();
    private List<Baggage> baggage = new ArrayList<>();

    public Passenger(String name, Baggage baggage) {
        this.name = name;
        this.baggage.add(baggage);
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public List getBaggage() {
        return  baggage;
    }

    public void addBaggage(Baggage baggage) {
        this.baggage.add(baggage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        if (this.baggage == null) {
            if (passenger.baggage != null) return false;
        } else if (!this.baggage.equals(passenger.baggage)) {
            return false;
        }
        if (this.name == null) {
            if (passenger.name != null) return false;
        } else if (!this.name.equals(passenger.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 17;
        return prime * (name == null ? 0 : name.hashCode()) + prime * (baggage == null ? 0 : baggage.hashCode());
    }

    @Override
    public String toString(){
        StringBuilder passenger=new StringBuilder();
        passenger.append(name).append(" ");
        for(Baggage b:baggage){
            passenger.append(b.getWeigth()).append(" ");
        }
        return passenger.toString();
    }
}
