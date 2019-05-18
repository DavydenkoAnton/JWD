package by.javatr.transport.dao.impl.txt;

import by.javatr.transport.creator.TrainPassengerCreator;
import by.javatr.transport.creator.FactoryCreator;
import by.javatr.transport.dao.TrainPassengerDAO;
import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.exception.DaoException;
import by.javatr.transport.exception.TxtTrainPassengerDAOExeption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TxtTrainPassengerDAO  implements TrainPassengerDAO {
    private static final Logger log = LogManager.getLogger();
    FactoryCreator factoryCreator = FactoryCreator.getInstance();
    TrainPassengerCreator trainPassengerCreator = factoryCreator.getTrainPassengerCreator();

    @Override
    public String getTrainPassenger(String id) throws DaoException {
        String trainPassenger = "";
        String fileName = "src/main/java/files/txt/trainsDB.txt";
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (trainPassenger.length() == 0)
            throw new DaoException("There is no train passenger in data base with id:" + id);
        return trainPassenger;
    }


    @Override
    public List<TrainPassenger> read() throws TxtTrainPassengerDAOExeption {
        List<TrainPassenger> trainsPassenger = new ArrayList<>();
        TrainPassenger trainPassenger;
        List<String> trainsPassengerList = new ArrayList<>();
        Charset charset = Charset.forName("UTF-8");
        Path path = Paths.get("src/main/java/files/txt/trainsDB.txt");
        try {
            trainsPassengerList = Files.readAllLines(path, charset);
        } catch (IOException e) {
            log.error(e);
            throw new TxtTrainPassengerDAOExeption();
        }


        for (String train : trainsPassengerList) {
            trainPassenger = trainPassengerCreator.create(train);
            trainsPassenger.add(trainPassenger);
        }
        return trainsPassenger;
    }

    @Override
    public void update(List<TrainPassenger> trainsPassenger) throws DaoException {
        List<String> trains = new ArrayList<>();

        for (TrainPassenger trainPassenger : trainsPassenger) {
            trains.add(trainPassenger.toString());
        }

        Path file = Paths.get("src/main/java/files/txt/trainsDB.txt");
        try {
            Files.write(file, trains, Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new DaoException("IO exception", e);
        }
    }


    private String getID(String request) {
        String id = "";
        String[] resultID = request.split(" ");
        id = resultID[0];
        return id;
    }
}
