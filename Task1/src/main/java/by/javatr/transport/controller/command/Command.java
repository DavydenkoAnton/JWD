package by.javatr.transport.controller.command;

import by.javatr.transport.exception.TrainPassengerException;

import java.io.IOException;

public interface Command {
    public String execute(String request) throws  TrainPassengerException;
}

