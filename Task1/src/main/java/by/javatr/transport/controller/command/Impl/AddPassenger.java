package by.javatr.transport.controller.command.Impl;

import by.javatr.transport.controller.command.Command;
import by.javatr.transport.service.factory.ServiceFactory;
import by.javatr.transport.service.impl.ServicePassengerImpl;

public class AddPassenger implements Command {

    public String execute(String request) {
        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        return "passenger is added";
    }
}
