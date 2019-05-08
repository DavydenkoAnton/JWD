package by.javatr.transport.controller.command.Impl;

import by.javatr.transport.controller.command.Command;
import by.javatr.transport.service.factory.FactoryService;

public class AddPassenger implements Command {

    public String execute(String request) {
        FactoryService factoryService = FactoryService.getInstance();
        return "passenger is added";
    }
}
