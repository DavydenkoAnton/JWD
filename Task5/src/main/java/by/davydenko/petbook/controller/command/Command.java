package by.davydenko.petbook.controller.command;

import by.davydenko.petbook.entity.User;

import java.util.List;

public interface Command {
    List<User> getUsers(String request);
}

