package by.davydenko.greenhouse.controller;


import by.davydenko.greenhouse.controller.command.Command;
import by.davydenko.greenhouse.controller.command.CommandName;
import by.davydenko.greenhouse.service.parser.FlowerXMLParserDOMException;
import by.davydenko.greenhouse.service.parser.FlowerXMLParserSAXException;
import by.davydenko.greenhouse.service.parser.FlowerXMLParserSTAXException;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;


public class Controller {

    private static final Logger log = Logger.getLogger(Controller.class);
    private final CommandProvider provider = new CommandProvider();
    private final char paramDelimeter = ' ';
    private String commandName;
    private Command executionCommand;
    private String response;

    public String executeTask(String request) {
        try {
            commandName = request.substring(0, request.indexOf(paramDelimeter));
            executionCommand = provider.getCommand(commandName);
            response = executionCommand.execute(request);
        } catch (NullPointerException | FileNotFoundException | FlowerXMLParserDOMException | FlowerXMLParserSAXException | FlowerXMLParserSTAXException e) {
            response = CommandName.WRONG_REQUEST.name();
            log.error(e.toString());
        }

        return response;
    }
}
