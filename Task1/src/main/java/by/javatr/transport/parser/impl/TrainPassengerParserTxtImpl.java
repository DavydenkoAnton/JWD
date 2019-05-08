package by.javatr.transport.parser.impl;

import by.javatr.transport.exception.ParseException;
import by.javatr.transport.parser.TrainPassengerParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TrainPassengerParserTxtImpl implements TrainPassengerParser {

    String path="src/main/java/files/txt/trainsDB.txt";

    @Override
    public List<String> parse() throws ParseException {
        List<String> lines = new ArrayList<>();
        try {
            lines.addAll(Files.readAllLines(Paths.get(path)));
        }catch (IOException e){
            throw new ParseException("IOException",e);
        }
        return lines;
    }
}
