package by.javatr.transport.parser.impl;

import by.javatr.transport.dao.TrainPassengerDAO;
import by.javatr.transport.dao.DAOFactory;
import by.javatr.transport.exception.DaoException;
import by.javatr.transport.parser.TrainPassengerParser;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TrainPassengerParserImpl implements TrainPassengerParser {

    String path = "src/main/java/files/txt/trainsDB.txt";

    @Override
    public List<String> parse(String request) {
        List<String> trainPassenger = null;
        trainPassenger = Arrays.asList(request.split(" "));
        return trainPassenger;
    }

    @Override
    public UUID parseID(String request) {
        return null;
    }
}
