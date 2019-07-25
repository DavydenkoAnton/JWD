package by.davydenko.petbook.controller;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.CommandName;
import by.davydenko.petbook.controller.command.impl.*;
import by.davydenko.petbook.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

final class CommandProvider {

    private static final Logger logger = LogManager.getLogger(CommandProvider.class);

    private final static CommandProvider instance = new CommandProvider();
    private final Map<String, Command> commands = new ConcurrentHashMap<>();

    private CommandProvider() {

        commands.put("main", new MainPageCommand());
        commands.put("locale", new ChangeLocaleCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }


    // TODO change uri
    public Command getCommand(String commandName) {

        Command command = null;

        try {
            command = commands.get(commandName);
        } catch (NullPointerException e) {
            logger.error("NullPointerException", e);
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgumentException", e);
        }

        return command;
    }

    public String getCommandNameFromUri(HttpServletRequest httpRequest) {
        String command = null;
        // Раскладываем адрес на составляющие
        String[] list = httpRequest.getRequestURI().split("/");

        if (list[list.length - 1].indexOf(".html") > 0) {
            command = list[list.length - 1];
        }
        command = command.replace(".html", "");
        return command;
    }
}
