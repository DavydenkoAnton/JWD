package by.javatr.transport.controller;

import by.javatr.transport.controller.command.Command;
import by.javatr.transport.exception.ParseException;
import by.javatr.transport.exception.TrainPassengerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {

    private static final Logger log = LogManager.getLogger();
    private final CommandProvider provider = new CommandProvider();
    private final char paramDelimeter = ' ';
    private String commandName;
    private Command executionCommand;
    private String response;

    public String executeTask(String request) throws TrainPassengerException {
        try {
            commandName = request.substring(0, request.indexOf(paramDelimeter));
            executionCommand = provider.getCommand(commandName);
            response = executionCommand.execute(request);
        } catch (NullPointerException | ParseException e) {
            log.error(e.getMessage());
        }

        return response;
    }
}
