package by.javatr.transport.entity;

import java.util.List;

public class Passenger {
    private String name;
    private List<Baggage> baggage;

    public Passenger(String name, Baggage baggage) {
        this.name = name;
        this.baggage.add(baggage);
    }

    public String getName() {
        return name;
    }

    public List getBaggage() {
        return baggage;
    }

    public void setBaggage(List baggage) {
        this.baggage = baggage;
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
        return prime * (name==null?0:name.hashCode()) + prime * (baggage==null?0:baggage.hashCode());
    }

}
