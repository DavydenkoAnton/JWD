package by.javatr.transport.controller.command.Impl;

import by.javatr.transport.controller.command.Command;
import by.javatr.transport.service.factory.ServiceFactory;

public class AddTrainPassenger implements Command {
    @Override
    public String execute(String request) {
        ServiceFactory serviceFactory=ServiceFactory.getInstance();

        return null;
    }
}
