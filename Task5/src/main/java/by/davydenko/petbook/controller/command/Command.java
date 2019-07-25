package by.davydenko.petbook.controller.command;

import by.davydenko.petbook.dao.DaoMySqlException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    void execute(HttpServletRequest request, HttpServletResponse response);

}