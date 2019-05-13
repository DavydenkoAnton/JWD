package by.javatr.transport.controller.command.Impl;

import by.javatr.transport.controller.command.Command;
import by.javatr.transport.exception.DaoException;
import by.javatr.transport.exception.ParseException;
import by.javatr.transport.exception.RepositoryException;
import by.javatr.transport.exception.TrainPassengerException;
import by.javatr.transport.service.TrainPassengerService;
import by.javatr.transport.service.factory.FactoryService;

import java.io.IOException;


public class GetAllTrainPassenger implements Command {

    public String execute(String request) {

        String response = null;
// get parameters from request and initialize the variables login and password
        FactoryService factoryService = FactoryService.getInstance();
        TrainPassengerService trainPassengerService = factoryService.getTrainPassengerService();

        try {
            response = trainPassengerService.getAllTrainPassenger();
        } catch (DaoException | ParseException | RepositoryException e) {

        }
        return response;
    }

}
