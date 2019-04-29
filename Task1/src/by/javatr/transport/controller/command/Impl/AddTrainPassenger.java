package by.javatr.transport.controller.command.Impl;

import by.javatr.transport.controller.command.Command;
import by.javatr.transport.service.TrainPassengerService;
import by.javatr.transport.service.factory.ServiceFactory;

public class AddTrainPassenger implements Command {
    @Override
    public String execute(String request) {
        int id = 0;
        String response = null;
// get parameters from request and initialize the variables login and password
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        TrainPassengerService trainPassengerService = serviceFactory.getTrainPassengerService();

        //try {
            trainPassengerService.addTrainCarPassenger(id);
            response = "passenger train added";
        ///} catch (ServiceException e) {
           // response = "Error duiring login procedure";
        //}
        return response;
    }

}
