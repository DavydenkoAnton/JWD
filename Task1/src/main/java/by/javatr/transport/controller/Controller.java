package by.javatr.transport.controller;

import by.javatr.transport.controller.command.Command;
import by.javatr.transport.exception.TrainPassengerException;

import java.io.IOException;

public final class Controller {

    private final CommandProvider provider = new CommandProvider();
    private final char paramDelimeter = ' ';

    public String executeTask(String request) throws IOException, TrainPassengerException {
        if(request==null)throw new NullPointerException("null request executeTask");
        String commandName;
        Command executionCommand;
        commandName = request.substring(0, request.indexOf(paramDelimeter));
        executionCommand = provider.getCommand(commandName);
        String response;
        response = executionCommand.execute(request);
        return response;
    }
}
