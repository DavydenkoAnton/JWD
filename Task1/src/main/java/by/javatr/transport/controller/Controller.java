package by.javatr.transport.controller;

import by.javatr.transport.controller.command.Command;
import by.javatr.transport.exception.TrainPassengerException;
import by.javatr.transport.logger.LoggerClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;

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
        } catch (NullPointerException e) {
            log.error("null request", e);
        }

        return response;
    }
}
