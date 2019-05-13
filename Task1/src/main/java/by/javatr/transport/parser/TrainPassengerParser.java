package by.javatr.transport.parser;

import by.javatr.transport.exception.DaoException;
import by.javatr.transport.exception.ParseException;

import java.util.List;

public interface TrainPassengerParser {
    List<String> parse(String request) throws ParseException, DaoException;
}
