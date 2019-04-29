package by.javatr.transport.service.impl;

import by.javatr.transport.entity.Passenger;
import by.javatr.transport.service.PassengerService;

public class ServicePassengerImpl implements PassengerService {

    Passenger passenger = new Passenger();

    @Override
    public void setName(String name) {
        passenger.setName(name);
    }

    @Override
    public void setBaggageCount(int baggageCount) {
        passenger.setBaggageCount(baggageCount);
    }
}
