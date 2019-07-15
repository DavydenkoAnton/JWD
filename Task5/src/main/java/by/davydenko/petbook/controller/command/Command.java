package by.davydenko.petbook.controller.command;

import by.davydenko.petbook.dao.DaoMySqlException;
import by.davydenko.petbook.entity.Entity;
import by.davydenko.petbook.entity.User;

import java.util.List;

public interface Command <T extends Entity> {

    T get();

    List<T> getList() throws DaoMySqlException;

    void put( T entity);

    void putList(List<T> entity);

}