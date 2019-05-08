package by.javatr.transport.dao.impl.txt;

import by.javatr.transport.dao.TrainPassengerDAO;
import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.exception.DaoException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxtTrainPassengerDAO implements TrainPassengerDAO {


    public void addTrainPassenger(String request) throws IOException {
        List<String> lines = Arrays.asList("The first line", "The second line");
        Path file = Paths.get("src/main/java/files/txt/trains.txt");
        //Files.write(file, lines, Charset.forName("UTF-8"));
Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
    }

    @Override
    public void create() {

    }

    @Override
    public void read() {

    }

    @Override
    public void update() throws DaoException {
        List<String> lines=new ArrayList<>();
        Path file = Paths.get("src/main/java/files/txt/trains.txt");
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new DaoException("IO exception",e);
        }
    }

    @Override
    public void delete() {

    }
}
