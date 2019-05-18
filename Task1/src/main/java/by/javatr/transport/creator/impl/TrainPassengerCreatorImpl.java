package by.javatr.transport.creator.impl;

import by.javatr.transport.creator.TrainPassengerCreator;
import by.javatr.transport.entity.Baggage;
import by.javatr.transport.entity.Passenger;
import by.javatr.transport.entity.TrainCarPassenger;
import by.javatr.transport.entity.TrainPassenger;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TrainPassengerCreatorImpl implements TrainPassengerCreator {
    @Override
    public TrainPassenger create(String trainPassenger) {
        String[] train = trainPassenger.split(" ");
        TrainPassenger trainPassengerTemp = new TrainPassenger();
        TrainCarPassenger trainCarPassenger;
        Passenger passenger;
        Baggage baggage;

        UUID id = UUID.fromString(train[0]);
        trainPassengerTemp.setId(id);

        for (int i = 1; i < train.length; i += 2) {
            baggage=new Baggage();
            baggage.setWeigth(Integer.parseInt(train[i + 1]));

            passenger=new Passenger();
            passenger.setName(train[i]);
            passenger.addBaggage(baggage);

            trainCarPassenger=new TrainCarPassenger();
            trainCarPassenger.addPassenger(passenger);
            trainPassengerTemp.add(trainCarPassenger);
        }
        return trainPassengerTemp;
    }


}
