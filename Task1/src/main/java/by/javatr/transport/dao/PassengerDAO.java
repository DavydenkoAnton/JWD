package by.javatr.transport.dao;

public interface PassengerDAO extends DAO {
    void addPassenger(String name,int baggageCount,int trainID,int trainCarID,int seat);
}
