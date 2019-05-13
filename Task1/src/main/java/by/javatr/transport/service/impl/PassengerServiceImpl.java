package by.javatr.transport.service.impl;

import by.javatr.transport.entity.Baggage;
import by.javatr.transport.entity.Passenger;
import by.javatr.transport.service.PassengerService;

public class PassengerServiceImpl implements PassengerService {

    Baggage baggage=new Baggage();
    Passenger passenger = new Passenger();

    public void setPassenger(Passenger p) {
        passenger=p;
    }

}
