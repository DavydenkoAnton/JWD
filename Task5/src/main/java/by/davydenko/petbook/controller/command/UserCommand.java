package by.davydenko.petbook.controller.command;

import by.davydenko.petbook.entity.User;

import java.util.List;

public interface UserCommand {
    List<User> getUsers(String request);
    
}
