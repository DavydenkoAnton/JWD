package by.javatr.transport.controller;

import by.javatr.transport.controller.command.Command;
import by.javatr.transport.controller.command.Impl.AddPassenger;
import by.javatr.transport.controller.command.CommandName;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {

    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.ADD_PASSENGER, new AddPassenger());
    }

    Command getCommand(String name) {
        CommandName commandName = null;
        Command command = null;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
