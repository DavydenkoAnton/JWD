package by.javatr.transport.controller;

import by.javatr.transport.controller.command.Command;
import by.javatr.transport.controller.command.Impl.AddPassenger;
import by.javatr.transport.controller.command.CommandName;
import by.javatr.transport.controller.command.Impl.AddTrainPassenger;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {

    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.ADD_PASSENGER, new AddPassenger());
        repository.put(CommandName.ADD_TRAIN_PASSENGER, new AddTrainPassenger());
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
