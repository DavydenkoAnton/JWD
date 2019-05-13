package by.javatr.transport.entity;

import java.util.UUID;

public class Baggage {
    private UUID id = UUID.randomUUID();
    private int weigth;

    public Baggage() {

    }


    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    public double getWeigth() {
        return weigth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Baggage baggage = (Baggage) o;
        if (Double.compare(baggage.weigth, weigth) != 0) {
            return false;
        }
        if (id == null) {
            if (baggage.id != null) {
                return id.equals(baggage.id);
            } else {
                if (!id.equals(baggage.id)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        int prime = 17;
        return prime * (id == null ? 0 : id.hashCode()) + prime * weigth;
    }

    @Override
    public String toString() {
        return Integer.toString(weigth);
    }
}
