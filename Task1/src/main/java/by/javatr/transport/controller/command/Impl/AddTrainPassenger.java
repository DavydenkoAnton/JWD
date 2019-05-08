package by.javatr.transport.controller.command.Impl;

import by.javatr.transport.controller.command.Command;
import by.javatr.transport.exception.DaoException;
import by.javatr.transport.exception.TrainPassengerException;
import by.javatr.transport.service.TrainPassengerService;
import by.javatr.transport.service.factory.FactoryService;

import java.io.IOException;

public class AddTrainPassenger implements Command {

    public String execute(String request) throws  TrainPassengerException {

        String response = null;
// get parameters from request and initialize the variables login and password
        FactoryService factoryService =  FactoryService.getInstance();
        TrainPassengerService trainPassengerService = factoryService.getTrainPassengerService();

        try {
            trainPassengerService.addTrainPassenger(request);
            response = "passenger train added";
        } catch (DaoException | IOException e) {
            response = "Error duiring login procedure";
        }
        return response;
    }

}
