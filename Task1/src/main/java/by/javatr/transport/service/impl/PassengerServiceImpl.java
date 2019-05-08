package by.javatr.transport.service.impl;

import by.javatr.transport.entity.Baggage;
import by.javatr.transport.entity.Passenger;
import by.javatr.transport.service.PassengerService;

public class PassengerServiceImpl implements PassengerService {

    Baggage baggage=new Baggage(4.3);
    Passenger passenger = new Passenger("Bob",baggage);

    public void setPassenger(Passenger p) {
        passenger=p;
    }

}
