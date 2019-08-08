package by.davydenko.petbook.controller;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

final class CommandProvider {

    private static final Logger logger = LogManager.getLogger(CommandProvider.class);

    private final static CommandProvider instance = new CommandProvider();
    private final Map<String, Command> commands = new ConcurrentHashMap<>();

    private CommandProvider() {
        commands.put("main", new MainPageCommand());
        commands.put("locale", new ChangeLocaleCommand());
        commands.put("login", new LoginPageCommand());
        commands.put("loginUser", new LoginCommand());
        commands.put("deleteUser", new DeleteUserCommand());
        commands.put("user", new UserPageCommand());
        commands.put("logout", new LogoutUserCommand());
        commands.put("registration", new RegisterPageCommand());
        commands.put("register", new RegisterUserCommand());
        commands.put("admin", new UserPageCommand());
        commands.put("message", new MessagePageCommand());
        commands.put("sendMessage", new SendMessageCommand());
        commands.put("pagingUsersNext", new PaggingUsersNext());
        commands.put("pagingUsersPrev", new PaggingUsersPrev());
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


}
