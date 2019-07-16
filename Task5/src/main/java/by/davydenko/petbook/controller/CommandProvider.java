package by.davydenko.petbook.controller;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.CommandName;
import by.davydenko.petbook.controller.command.impl.StartPageCommand;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {

    private final static CommandProvider instance = new CommandProvider();
    private final Map<CommandName, Command> commands = new HashMap<>();


    private CommandProvider() {
        commands.put(CommandName.START_PAGE, new StartPageCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    Command getCommand(String name) {
        CommandName commandName = null;
        Command command = null;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = commands.get(commandName);
        } catch (NullPointerException | IllegalArgumentException e) {
            command = commands.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
