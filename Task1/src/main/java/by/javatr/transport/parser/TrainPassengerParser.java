package by.javatr.transport.parser;

import by.javatr.transport.exception.DaoException;
import by.javatr.transport.exception.ParseException;

import java.util.List;
import java.util.UUID;

public interface TrainPassengerParser {
    List<String> parse(String request) throws ParseException, DaoException;

    UUID parseID(String request);
}
