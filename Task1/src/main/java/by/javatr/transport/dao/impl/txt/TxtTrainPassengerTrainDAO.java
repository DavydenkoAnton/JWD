package by.javatr.transport.dao.impl.txt;

import by.javatr.transport.dao.TrainPassengerTrainDAO;
import by.javatr.transport.entity.TrainCarPassenger;
import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.exception.DaoException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxtTrainPassengerTrainDAO implements TrainPassengerTrainDAO {

    private List<String> trains = new ArrayList<>();

    public void addTrainPassenger(String request) throws DaoException {
        read();
        if (!containID(request)) {
            add(request);
        }
        update();
    }

    @Override
    public void create() {

    }

    @Override
    public void read() throws DaoException {
        try {
            trains.addAll(Files.readAllLines(Paths.get("src/main/java/files/txt/trainsDB.txt")));
        } catch (IOException e) {
            throw new DaoException("IOException", e);
        }
    }

    @Override
    public void update() throws DaoException {


        Path file = Paths.get("src/main/java/files/txt/trainsDB.txt");
        try {
            Files.write(file, trains, Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new DaoException("IO exception", e);
        }
    }


    @Override
    public void delete() {

    }

    private void add(String request) {
        trains.add(getID(request));
    }


    private boolean containID(String request) {
        boolean result = false;
        for (String train : trains) {
            if (getID(train).equals(getID(request))) {
                result = true;
                break;
            }
        }
        return result;
    }

    private String getID(String request) {
        String id = "";
        String[] resultID = request.split(" ");
        id = resultID[0];
        return id;
    }
}
