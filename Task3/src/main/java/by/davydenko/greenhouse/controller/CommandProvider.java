package by.davydenko.greenhouse.controller;

import by.davydenko.greenhouse.controller.command.Command;
import by.davydenko.greenhouse.controller.command.CommandName;
import by.davydenko.greenhouse.controller.command.Impl.FlowerParseDOM;
import by.davydenko.greenhouse.controller.command.Impl.FlowerParseSAX;
import by.davydenko.greenhouse.controller.command.Impl.FlowerParseSTAX;


import java.util.HashMap;
import java.util.Map;

final class CommandProvider {

    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.FLOWER_PARSE_DOM, new FlowerParseDOM());
        repository.put(CommandName.FLOWER_PARSE_SAX, new FlowerParseSAX());
        repository.put(CommandName.FLOWER_PARSE_STAX, new FlowerParseSTAX());
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
