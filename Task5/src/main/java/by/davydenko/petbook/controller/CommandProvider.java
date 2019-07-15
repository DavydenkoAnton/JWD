package by.davydenko.petbook.controller;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.CommandName;
import by.davydenko.petbook.controller.command.UserCommandImpl;


import java.util.HashMap;
import java.util.Map;

final class CommandProvider {

    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.GET_USERS, new UserCommandImpl());
        repository.put(CommandName.GET_USER, new UserCommandImpl());
    }

    Command getCommand(String name) {
        CommandName commandName = null;
        Command command = null;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch ( NullPointerException | IllegalArgumentException e) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
