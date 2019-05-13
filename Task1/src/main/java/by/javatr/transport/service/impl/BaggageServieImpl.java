package by.javatr.transport.service.impl;

import by.javatr.transport.entity.Baggage;
import by.javatr.transport.entity.Passenger;
import by.javatr.transport.service.BaggageService;

public class BaggageServieImpl implements BaggageService {

    @Override
    public double getWeigth(Baggage baggage) {
        return baggage.getWeigth();
    }
}
