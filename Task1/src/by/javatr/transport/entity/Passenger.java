package by.javatr.transport.entity;

public class Passenger {
    private String name;
    private int baggageCount;

    public Passenger(){}

    public Passenger(String name, int baggageCount) {
        this.name = name;
        this.baggageCount = baggageCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaggageCount() {
        return baggageCount;
    }

    public void setBaggageCount(int baggageCount) {
        this.baggageCount = baggageCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        if (baggageCount != passenger.baggageCount) ;
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
        return prime * name.hashCode() + prime * baggageCount;
    }

}
