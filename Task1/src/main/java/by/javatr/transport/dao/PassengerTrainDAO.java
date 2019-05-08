package by.javatr.transport.dao;

public interface PassengerTrainDAO extends TrainDAO {
    void addPassenger(String name,int baggageCount,int trainID,int trainCarID,int seat);
}
